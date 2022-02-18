/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 计算每个节点所有路径和
// O(nlogn)
func pathSum1(root *TreeNode, targetSum int) int {

    var dfs func(*TreeNode, []int) int
    dfs = func(node *TreeNode, parentPathSumList []int) int {
        if node == nil {
            return 0
        }

        var cnt, tmp = 0, make([]int, 0)
        for i := range parentPathSumList {
            var num = parentPathSumList[i] + node.Val
            tmp = append(tmp, num)
            if num == targetSum {
                cnt++
            }
        }

        tmp = append(tmp, node.Val)
        if node.Val == targetSum {
            cnt++
        }

        var leftCnt = dfs(node.Left, tmp)
        var rightCnt = dfs(node.Right, tmp)

        return cnt + leftCnt + rightCnt
    }

    return dfs(root, make([]int, 0))
}


// DFS(前序遍历) + 前缀和
// O(n)
func pathSum2(root *TreeNode, targetSum int) int {
    var res, prefixSumMap = 0, make(map[int]int)
    prefixSumMap[0] = 1

    var dfs func(*TreeNode, int)
    dfs = func(node *TreeNode, currSum int) {
        if node == nil {
            return
        }

        currSum += node.Val
        res += prefixSumMap[currSum - targetSum]
        prefixSumMap[currSum] += 1

        dfs(node.Left, currSum)
        dfs(node.Right, currSum)

        prefixSumMap[currSum] -= 1
    }

    dfs(root, 0)
    return res
}

// DFS(前序遍历) + 前缀和
// O(n)
func pathSum(root *TreeNode, targetSum int) int {
    var prefixSumMap = make(map[int]int)
    prefixSumMap[0] = 1

    var dfs func(*TreeNode, int) int
    dfs = func(node *TreeNode, currSum int) int {
        if node == nil {
            return 0
        }

        currSum += node.Val
        var res = prefixSumMap[currSum - targetSum]
        prefixSumMap[currSum] += 1

        res += dfs(node.Left, currSum)
        res += dfs(node.Right, currSum)

        prefixSumMap[currSum] -= 1
        return res
    }

    return dfs(root, 0)
}
