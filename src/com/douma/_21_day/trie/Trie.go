
type Node struct {
    // 每个节点包含：
    // 1. 一个字符
    // 2. 若干个子节点
    children map[byte]*Node
    // 标识，这个节点是否是一个单词最后一个字符
    isWord bool
}

type Trie struct {
    root *Node
}


/** Initialize your data structure here. */
func Constructor() Trie {
    return Trie{root:&Node{children:make(map[byte]*Node), isWord:false}}
}


/** Inserts a word into the trie. */
func (this *Trie) add(word string)  {
    var curr = this.root
    for i := range word {
        var c = word[i]
        // 1. 先从子节点中查找是否包含当前字符 c
        if _, has := curr.children[c]; !has {
            curr.children[c] = &Node{children:make(map[byte]*Node), isWord:false}
        }
        curr =curr.children[c]
    }
    curr.isWord = true
}


/** Returns if the word is in the trie. */
func (this *Trie) contains(word string) bool {
    var curr = this.root
    for i := range word {
        var c = word[i]
        // 1. 先从子节点中查找是否包含当前字符 c
        if _, has := curr.children[c]; !has {
            return false
        }
        curr =curr.children[c]
    }
    return curr.isWord
}
