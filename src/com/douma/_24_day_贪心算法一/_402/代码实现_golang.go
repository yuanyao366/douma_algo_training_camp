// 贪心策略：每次删除前面比较大的数字
func removeKdigits(num string, k int) string {
    var deque = list.New()
    for i := range num {
        var c = num[i]
        for deque.Len() > 0 && k > 0 && deque.Front().Value.(byte) > c {
            deque.Remove(deque.Front())
            k--
        }
        deque.PushFront(c)
    }

    for i := 0; i < k; i++ {
        deque.Remove(deque.Front())
    }

    var res, isFirst = "", true
    for deque.Len() > 0 {
        var c = deque.Remove(deque.Back()).(byte)
        if c == '0' && isFirst {
            continue
        }
        res += string(c)
        isFirst = false
    }

    if len(res) == 0 {
        return "0"
    } else {
        return res
    }
}