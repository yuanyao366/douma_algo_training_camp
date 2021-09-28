
// 二进制前缀树
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func findMaximumXOR(nums []int) int {
    var trie = Trie{root:&Node{}}
    var res = 0
    for i := 1; i < len(nums); i++ {
        trie.add(nums[i - 1])
        var xor = trie.maxXor(nums[i])
        if xor > res {
            res = xor
        }
    }
    return res
}

type Node struct {
    zero *Node
    one *Node
}

type Trie struct {
    root *Node
}

// 将 num 的二进制从高位到低位添加到前缀树中
func (this *Trie) add(num int) {
    var curr = this.root
    // 整数的最高一位是符号位，对于正数的话是 0
    //      0100 0000 0000 0000 0000 0000 0000 0000 >> 30
    // ->   0000 0000 0000 0000 0000 0000 0000 0001
    // & 1  0000 0000 0000 0000 0000 0000 0000 0001
    // bug 修复，是 k--
    for k := 30; k >= 0 ; k-- {
        var bit = (num >> k) & 1
        if bit == 0 {
            if curr.zero == nil {
                curr.zero = &Node{}
            }
            curr = curr.zero
        } else {
            if curr.one == nil {
                curr.one = &Node{}
            }
            curr = curr.one
        }
    }
}

// 在前缀树中查找和 num 进行异或，得到的最大的异或值
func (this *Trie) maxXor(num int) int {
    var curr, x = this.root, 0
    // bug 修复，是 k--
    for k := 30; k >= 0 ; k-- {
        var bit = (num >> k) & 1
        if bit == 0 {
            if curr.one != nil {
                curr = curr.one
                x = 2 * x + 1
            } else {
                curr = curr.zero
                x = 2 * x
            }
        } else {
            if curr.zero != nil {
                curr = curr.zero
                x = 2 * x + 1
            } else {
                curr = curr.one
                x = 2 * x
            }
        }
    }
    return x
}