/**
 * Initialize your data structure here.
 */

class Node {
    constructor() {
        this.children = new Array(26).fill(null)
        this.isWord = false
    }
}

var Trie = function() {
    this.root = new Node()
};

/**
 * Inserts a word into the trie.
 * @param {string} word
 * @return {void}
 */
Trie.prototype.insert = function(word) {
    let curr = this.root
    for (const c of word) {
        const index = c.charCodeAt() - 'a'.charCodeAt()
        if (!curr.children[index]) {
            curr.children[index] = new Node()
        }
        curr = curr.children[index]
    }
    curr.isWord = true
};

/**
 * Returns if the word is in the trie.
 * @param {string} word
 * @return {boolean}
 */
Trie.prototype.search = function(word) {
    let curr = this.root
    for (const c of word) {
        const index = c.charCodeAt() - 'a'.charCodeAt()
        if (!curr.children[index]) {
            return false
        }
        curr = curr.children[index]
    }
    return curr.isWord
};

/**
 * Returns if there is any word in the trie that starts with the given prefix.
 * @param {string} prefix
 * @return {boolean}
 */
Trie.prototype.startsWith = function(prefix) {
    let curr = this.root
    for (const c of prefix) {
        const index = c.charCodeAt() - 'a'.charCodeAt()
        if (!curr.children[index]) {
            return false
        }
        curr = curr.children[index]
    }
    return true
};

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = new Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */