// 排序
// 超出时间限制
func findKthNumber1(n int, k int) int {
    // 1. 将每个数字转成字符串
    var list = make([]string, 0)
    for i := 1; i <= n; i++ {
        list = append(list, strconv.Itoa(i))
    }

    // 2. 对字符串进行排序
    sort.Strings(list)
    var res, _ = strconv.Atoi(list[k - 1])
    return res 
}

// 排序
// 超出时间限制
func findKthNumber(n int, k int) int {
    var curr = 1
    k = k - 1
    for k > 0 {
        var nodes = calNodes(n, curr, curr + 1)
        if nodes <= k {  // 不在当前的前缀中
            curr += 1
            k -= nodes
        } else { // 在当前的前缀中
            curr *= 10
            k -= 1
        }
    }
    return curr
}

func calNodes(n int, curr int, next int) int {
    var nodes = 0
    for curr <= n {
        /*
        你可能会问:咦？怎么是 n+1 ,而不是 n  呢？不是说好了 n  是上界吗？

        我举个例子，假若现在上界 n 为 12，算出以 1 为前缀的子节点数，
        首先 1 本身是一个节点，接下来要算下面 10，11，12，一共有 4 个子节点。

        那么如果用 Math.min(n, next)  - curr 会怎么样？

        这时候算出来会少一个，12 - 10 加上根节点，最后只有 3 个。因此我们务必要写 n+1。
        */
        nodes += min(n + 1, next) - curr
        curr *= 10
        next *= 10
    }
    return nodes
}

func min(a, b int) int {
    if a < b {
        return a 
    }
    return b
}
