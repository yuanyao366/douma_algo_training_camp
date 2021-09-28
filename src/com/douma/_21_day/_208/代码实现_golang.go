
type Node struct {
    // 每个节点包含：
    // 1. 一个字符
    // 2. 若干个子节点
    // a-z 26 个字符
    // a -> 0, b -> 1.... z -> 26
    // a - a = 0
    // b - a = 1
    children [26]*Node
    // 标识，这个节点是否是一个单词最后一个字符
    isWord bool
}

func newNode() *Node {
    return &Node{children:[26]*Node{}, isWord:false}
}

type Trie struct {
    root *Node
}


/** Initialize your data structure here. */
func Constructor() Trie {
    return Trie{root:newNode()}
}


/** Inserts a word into the trie. */
func (this *Trie) Insert(word string)  {
    var curr = this.root
    for i := range word {
        var c = word[i]
        // 1. 先从子节点中查找是否包含当前字符 c
        if curr.children[c - 'a'] == nil {
            curr.children[c - 'a'] = newNode()
        }
        curr = curr.children[c - 'a'] 
    }
    curr.isWord = true
}


/** Returns if the word is in the trie. */
func (this *Trie) Search(word string) bool {
    var curr = this.root
    for i := range word {
        var c = word[i]
        // 1. 先从子节点中查找是否包含当前字符 c
        if curr.children[c - 'a'] == nil {
            return false
        }
        curr = curr.children[c - 'a']
    }
    return curr.isWord
}


/** Returns if there is any word in the trie that starts with the given prefix. */
func (this *Trie) StartsWith(prefix string) bool {
    var curr = this.root
    for i := range prefix {
        var c = prefix[i]
        // 1. 先从子节点中查找是否包含当前字符 c
        if curr.children[c - 'a'] == nil {
            return false
        }
        curr = curr.children[c - 'a']
    }
    return true
}


/**
 * Your Trie object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.Search(word);
 * param_3 := obj.StartsWith(prefix);
 */