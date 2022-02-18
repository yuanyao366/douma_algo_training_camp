/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} targetSum
 * @return {number}
 */
// DFS + 计算每个节点所有路径和
var pathSum1 = function(root, targetSum) {

    const dfs = (node, parentPathSumList, targetSum) => {
        if (node == null) return 0

        let cnt = 0
        const tmp = []
        for (let i = 0; i < parentPathSumList.length; i++) {
            const num = parentPathSumList[i] + node.val
            tmp.push(num)
            if (num == targetSum) cnt++
        }
        tmp.push(node.val)
        if (node.val == targetSum) cnt++;

        const leftCnt = dfs(node.left, tmp, targetSum)
        const rightCnt = dfs(node.right, tmp, targetSum)

        return cnt + leftCnt + rightCnt
    }

    return dfs(root, [], targetSum)
};

// DFS + 前缀和
var pathSum = function(root, targetSum) {

    const dfs = (node, currSum, targetSum, prefixSumMap) => {
        if (node == null) return 0

        currSum += node.val
        let res = prefixSumMap.has(currSum - targetSum) ? prefixSumMap.get(currSum - targetSum) : 0
        if (prefixSumMap.has(currSum)) {
            prefixSumMap.set(currSum, prefixSumMap.get(currSum) + 1)
        } else {
            prefixSumMap.set(currSum, 1)
        }

        res += dfs(node.left, currSum, targetSum, prefixSumMap)
        res += dfs(node.right, currSum, targetSum, prefixSumMap)

        prefixSumMap.set(currSum, prefixSumMap.get(currSum) - 1)

        return res
    }
    const prefixSumMap = new Map()
    prefixSumMap.set(0, 1)
    return dfs(root, 0, targetSum, prefixSumMap)
};