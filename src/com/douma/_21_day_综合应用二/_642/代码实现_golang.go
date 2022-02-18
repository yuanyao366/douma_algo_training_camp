type SentenceInfo struct {
    content string
    time int
}

type TrieNode struct {
    children map[byte]*TrieNode
    // times == 0 说明，这个节点不是字符串的结尾节点
    // times > 0 说明是结尾字符节点
    times int
}

func newNode() *TrieNode {
    return &TrieNode{children:make(map[byte]*TrieNode), times: 0}
}

type AutocompleteSystem struct {
    root *TrieNode
    // 记录用户当前的输入
    currSentence string
}

func Constructor(sentences []string, times []int) AutocompleteSystem {
    var acs = AutocompleteSystem{root:newNode(), currSentence:""}
    for i := range sentences {
        acs.insert(sentences[i], times[i])
    }
    return acs
}

func (this *AutocompleteSystem) insert(s string, times int)  {
    var curr = this.root
    for i := range s {
        var c = s[i]
        // 1. 先从子节点中查找是否包含当前字符 c
        if _, has := curr.children[c]; !has {
            curr.children[c] = newNode()
        }
        curr =curr.children[c]
    }
    curr.times += times
}


func (this *AutocompleteSystem) Input(c byte) []string {
    var res = make([]string, 0)
    if c == '#' {
        // 更新这个句子总的被搜索次数
        this.insert(this.currSentence, 1)
        // 清除用户当前输入
        this.currSentence = ""
    } else {
        // 1. 将当前输入字符拼接到当前句子
        this.currSentence += string(c)

        // 2. 找到所有以当前输入字符串开头的句子
        // 从字典树中找到包含 currSentence 为前缀的所有句子
        var list = this.lookup(this.currSentence)

        // 3. 拿到被搜索次数排名前 3 的句子
        sort.Slice(list, func(i, j int) bool {
            var o1 = list[i]
            var o2 = list[j]
            if o1.time == o2.time {
                return o1.content < o2.content
            } else {
                return o1.time > o2.time
            }
        })
        for i := 0; i < min(3, len(list)); i++ {
            res = append(res, list[i].content)
        }
    }

    return res
}

func min(a, b int) int {
    if a < b {
        return a 
    }
    return b
}

// 从当前前缀树中搜索以 s 开头的所有的句子
func (this *AutocompleteSystem) lookup(s string) []SentenceInfo {
    var list = make([]SentenceInfo, 0)
    // 1. 找到前缀
    var curr = this.root
    for i := range s {
        var c = s[i]
        // 1. 先从子节点中查找是否包含当前字符 c
        if _, has := curr.children[c]; !has {
            return list
        }
        curr =curr.children[c]
    }

    // 2. 找到以前缀结尾的所有的字符串
    this.dfs(curr, s, &list)
    return list
}

func (this *AutocompleteSystem) dfs(curr *TrieNode, s string, list *[]SentenceInfo) {
    if curr.times > 0 {
        *list = append(*list, SentenceInfo{content:s, time:curr.times})
    }

    for c := range curr.children {
        this.dfs(curr.children[c], s + string(c), list)
    }
}


/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * obj := Constructor(sentences, times);
 * param_1 := obj.Input(c);
 */