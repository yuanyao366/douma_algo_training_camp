// 1. BFS
func removeInvalidParentheses1(s string) []string {
    var res = make([]string, 0)
    var queue = list.New()
    queue.PushBack(s)
    var visited = make(map[string]bool)
    visited[s] = true

    var found = false
    for queue.Len() > 0 {
        // 最优解一定在同一层
        var size = queue.Len()
        for i := 0; i < size; i++ {
            var currStr = queue.Remove(queue.Front()).(string)
            if isValid(currStr) {
                res = append(res, currStr)
                found = true
                continue
            }
            var currStrLen = len(currStr)
            for j := 0; j < currStrLen; j++ {
                if currStr[j] != '(' && currStr[j] != ')' {
                    continue
                }
                var leftStr = currStr[0:j]

                var rightStr = ""
                if j != currStrLen - 1 {
                    rightStr = currStr[j + 1:currStrLen]
                }
                var next = leftStr + rightStr
                if !visited[next] {
                    queue.PushBack(next)
                    visited[next] = true
                }
            }
        }
        if found {
            break
        }
    }
    return res
}

func isValid(s string) bool {
    var count = 0
    for _, c := range s {
        if c == '(' {
            count++
        } else if c == ')' {
            count--
        }
        if count < 0 {
            return false
        }
    }
    return count == 0
}




var path = make([]byte, 0)

// 2. DFS
func removeInvalidParentheses(s string) []string {
    var res = make(map[string]bool)

    // 第 1 步：遍历一次，计算多余的左右括号
    var leftRemove, rightRemove = 0, 0
    for i := range s {
        if s[i] == '(' {
            leftRemove++
        } else if s[i] == ')' {
            // 遇到右括号的时候，须要根据已经存在的左括号数量决定
            if leftRemove == 0 {
                rightRemove++
            }
            if leftRemove > 0 {
                // 关键：一个右括号出现可以抵销之前遇到的左括号
                leftRemove--
            }
        }
    }

    // 第 2 步：回溯算法，尝试每一种可能的删除操作
    dfs(0, leftRemove, rightRemove, 0, 0, res, s)

    var ans = make([]string, 0)
    for str := range res {
        if res[str] {
            ans = append(ans, str)
        }
    }
    return ans
}

func dfs(index int, leftRemove int, rightRemove int,
        leftCount int, rightCount int, res map[string]bool, s string) {
    if index == len(s) {
        if leftRemove == 0 && rightRemove == 0 {
            var tmp = make([]byte, len(path))
            copy(tmp, path)
            res[string(tmp)] = true
        }
        return
    }

    // 先处理当前的字符
    var c = s[index]

    // 可能的操作 1：删除当前遍历到的字符
    if c == '(' && leftRemove > 0 {
        dfs(index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, res, s)
    }
    if c == ')' && rightRemove > 0 {
        dfs(index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, res, s)
    }

    path = append(path, c)
    // 可能的操作 2：保留当前遍历到的字符
    if c != '(' && c != ')' {
        dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount, res, s)
    } else if c == '(' {
        dfs(index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, res, s)
    } else if rightCount < leftCount {
        dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, res, s)
    }
    path = path[:len(path) - 1]
}