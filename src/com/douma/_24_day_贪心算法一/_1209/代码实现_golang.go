type Pair struct {
    ch byte
    cnt int
}

// 栈
// 时间复杂度：O(n)
// 时间复杂度：O(n)
func removeDuplicates1(s string, k int) string {
    var stack = make([]Pair, 0)
    for i := range s {
        if len(stack) == 0 || s[i] != stack[len(stack) - 1].ch {
            stack = append(stack, Pair{ch:s[i], cnt:1})
        } else {
            stack[len(stack) - 1].cnt++
            if stack[len(stack) - 1].cnt == k {
                stack = stack[:len(stack) - 1]
            }
        }
    }

    var res = ""
    for len(stack) > 0 {
        var p = stack[0]
        stack = stack[1:len(stack)]
        for i := 0; i < p.cnt; i++ {
            res += string(p.ch)
        }
    }

    return res
}

// 另一种思路讲解
// 快慢指针
func removeDuplicates(s string, k int) string {
    var chars = []byte(s)
    var count = make([]int, 0)
    var slow = 0
    for fast := 0; fast < len(chars); fast++ {
        if slow != fast {
            chars[slow] = chars[fast]
        }
        if slow == 0 || chars[slow] != chars[slow - 1] {
            count = append(count, 1)
        } else {
            var incremented = count[len(count) - 1] + 1
            count = count[:len(count) - 1]
            if incremented == k {
                slow = slow - k
            } else {
                count = append(count, incremented)
            }
        }
        slow++
    }
    return string(chars[0:slow])
}