// 抖码算法，让算法学习变的简单有趣
// 作者：老汤


class Node {
    constructor() {
        // 每个节点包含：
        // 1. 一个字符
        // 2. 若干个子节点
        this.children = new Map()
        // 标识，这个节点是否是一个单词最后一个字符
        this.isWord = false
    }
}

class Trie {
    constructor() {
        this.root = new Node()
    }

    // 添加单词
    // O(n)
    add(word) {
        let curr = this.root
        for (const c of word) {
            // 1. 先从子节点中查找是否包含当前字符 c
            if (!curr.children.has(c)) {
                curr.children.set(c, new Node())
            }
            curr = curr.children.get(c)
        }
        curr.isWord = true
    }

    // 判断是否包含指定的单词
    // O(n)
    contains(word) {
        let curr = this.root
        for (const c of word) {
            // 1. 先从子节点中查找是否包含当前字符 c
            if (!curr.children.has(c)) {
                return false
            }
            curr = curr.children.get(c)
        }
        return curr.isWord
    }
}

const trie = new Trie()
trie.add("big")
trie.add("pat")
trie.add("bigger")
trie.add("dog")
trie.add("door")

console.log(trie.contains("dog"))