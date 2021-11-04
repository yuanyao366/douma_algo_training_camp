// 深度优先遍历
func isBipartite1(graph [][]int) bool {
    var n = len(graph)
    var visited = make([]bool, n)
    var colors = make([]int, n)
    for i := 0; i < n; i++ {
        colors[i] = -1
    }

    var dfs func(int, int) bool
    dfs = func(v int, color int) bool {
        visited[v] = true
        colors[v] = color
        for _, w := range graph[v] {
            if !visited[w] {
                // 如果 v 的颜色是 1，那么 w 的颜色就是 0
                // 如果 v 的颜色是 0，那么 w 的颜色就是 1
                if !dfs(w, 1 - color) {
                    return false
                }
            } else if colors[w] == colors[v] {
                // 如果相邻顶点的颜色一样的话，则不是二分图
                return false
            }
        }
        return true
    }

    for v := 0; v < n; v++ {
        if !visited[v] && !dfs(v, 0) {
            return false
        }
    }

    return true
}


// 广度优先遍历
func isBipartite(graph [][]int) bool {
    var n = len(graph)
    var visited = make([]bool, n)
    var colors = make([]int, n)
    for i := 0; i < n; i++ {
        colors[i] = -1
    }

    var bfs func(int) bool
    bfs = func(v int) bool {
        // 用双向链表实现单向队列的功能
        var queue = list.New()
        queue.PushBack(v) // 入队(往链表表尾添加元素)
        visited[v] = true
        colors[v] = 0

        for queue.Len() > 0 {
            var curr = queue.Remove(queue.Front()).(int)
            for _, w := range graph[curr] {
                if !visited[w] {
                    queue.PushBack(w)
                    visited[w] = true
                    // 给顶点 w 染色，和 curr 的颜色不一样
                    colors[w] = 1 - colors[curr]
                } else if colors[w] == colors[curr] {
                    // 如果相邻顶点的颜色一样的话，则不是二分图
                    return false
                }
            }
        }

        return true
    }

    for v := 0; v < n; v++ {
        if !visited[v] && !bfs(v) {
            return false
        }
    }

    return true
}