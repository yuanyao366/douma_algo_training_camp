/**
 * @param {number[][]} graph
 * @return {boolean}
 */
// 深度优先遍历
var isBipartite1 = function(graph) {
    const n = graph.length
    const visited = new Array(n).fill(false)
    // -1 表示没有染颜色
    // 0 红色 1 蓝色
    const colors = new Array(n).fill(-1)

    const dfs = (v, color) => {
        visited[v] = true
        colors[v] = color
        for (const w of graph[v]) {
            if (!visited[w]) {
                // 如果 v 的颜色是 1，那么 w 的颜色就是 0
                // 如果 v 的颜色是 0，那么 w 的颜色就是 1
                if (!dfs(w, 1 - color)) return false
            } else if (colors[w] == colors[v]) {
                // 如果相邻顶点的颜色一样的话，则不是二分图
                return false
            }
        }
        return true
    }

    for (let v = 0; v < n; v++) {
        if (!visited[v] && !dfs(v, 0)) return false
    }
    return true;
};

// 广度优先遍历
var isBipartite = function(graph) {
    const n = graph.length
    const visited = new Array(n).fill(false)
    // -1 表示没有染颜色
    // 0 红色 1 蓝色
    const colors = new Array(n).fill(-1)

    const bfs = (v) => {
        const queue = []
        queue.push(v)
        visited[v] = true
        colors[v] = 0
        while (queue.length) {
            const curr = queue.shift()
            for (const w of graph[curr]) {
                if (!visited[w]) {
                    queue.push(w)
                    visited[w] = true
                    // 给顶点 w 染色，和 curr 的颜色不一样
                    colors[w] = 1 - colors[curr]
                } else if (colors[w] == colors[curr]) {
                    // 如果 w 被访问过，并且它的颜色和相邻点一样
                    // 那么可以判定不是二分图
                    return false
                }
            }
        }
        return true
    }

    for (let v = 0; v < n; v++) {
        if (!visited[v] && !bfs(v)) return false
    }
    return true;
};