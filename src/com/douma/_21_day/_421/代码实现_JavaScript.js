/**
 * @param {number[]} nums
 * @return {number}
 */
class Node {
    constructor() {
        this.zero = null
        this.one = null
    }
}

class Trie {
    constructor() {
        this.root = new Node()
    }

    add(num) {
        let curr = this.root;
        for (let k = 30; k >= 0; k--) {
            const bit = (num >> k) & 1
            if (bit == 0) {
                if (!curr.zero) curr.zero = new Node()
                curr = curr.zero
            } else {
                if (!curr.one) curr.one = new Node()
                curr = curr.one
            }
        }
    }

    maxXor(num) {
        let curr = this.root;
        let x = 0
        for (let k = 30; k >= 0; k--) {
            const bit = (num >> k) & 1
            if (bit == 0) {
                if (curr.one) {
                    curr = curr.one;
                    x = 2 * x + 1
                } else {
                    curr = curr.zero
                    x = 2 * x
                }
            } else {
                if (curr.zero) {
                    curr = curr.zero;
                    x = 2 * x + 1
                } else {
                    curr = curr.one
                    x = 2 * x
                }
            }
        }
        return x
    }
}

var findMaximumXOR = function(nums) {
    const trie = new Trie()
    let res = 0
    for (let i = 1; i < nums.length; i++) {
        trie.add(nums[i - 1])
        res = Math.max(res, trie.maxXor(nums[i]))
    }
    return res
};