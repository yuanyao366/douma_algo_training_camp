/**
 * @param {string} s
 * @return {string[]}
 */

// BFS
var removeInvalidParentheses1 = function(s) {
    const isValid = (str) => {
        let count = 0
        for (const c of str) {
            if (c == '(') count++
            else if (c == ')') count--
            if (count < 0) return false
        }
        return count == 0
    }

    const res = [], queue = [], visited = new Set()
    queue.push(s)
    visited.add(s)
    let found = false;
    while (queue.length) {
        const size = queue.length
        for (let i = 0; i < size; i++) {
            const currStr = queue.shift()
            if (isValid(currStr)) {
                res.push(currStr)
                found = true;
                continue;
            }

            const currStrLen = currStr.length;
            for (let j = 0; j < currStrLen; j++) {
                if (currStr[j] != '(' && currStr[j] != ')') continue

                const leftStr = currStr.substring(0, j)
                const rightStr = (j == currStrLen - 1) ? "" : currStr.substring(j + 1, currStrLen)
                const next = leftStr + rightStr
                if (!visited.has(next)) {
                    queue.push(next)
                    visited.add(next)
                }
            }
        }
        if (found) break
    }

    return res
};



// DFS
var removeInvalidParentheses = function(s) {
    const res = new Set()
    const dfs = (index, leftRemove, rightRemove, leftCount, rightCount, path) => {
        if (index == s.length) {
            if (leftRemove == 0 && rightRemove == 0) {
                res.add(path)
            }
            return
        }

        const c = s[index]
        if (c == '(' && leftRemove > 0) {
            dfs(index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, path)
        }
        if (c == ')' && rightRemove > 0) {
            dfs(index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, path)
        }

        if (c != '(' && c != ')') {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount, path + c)
        } else if (c == '(') {
            dfs(index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, path + c)
        } else if (rightCount < leftCount) {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, path + c)
        }
    }

    let leftRemove = 0, rightRemove = 0
    for (const c of s) {
        if (c == '(') leftRemove++
        else if (c == ')') {
            if (leftRemove == 0) rightRemove++
            else if (leftRemove > 0) leftRemove--
        }
    }
    dfs(0, leftRemove, rightRemove, 0, 0, "")
    return Array.from(res)
}

