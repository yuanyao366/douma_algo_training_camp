func pathSum(nums []int) int {

    // 构建二叉树，使用数组存储
    var tree = [15]int{}
    for i := range tree {
        tree[i] = -1
    }

    for _, num := range nums {
        var bai = num / 100
        var shi = num % 100 / 10
        var ge = num % 10
        var index = ((1 << (bai - 1)) - 1) + shi - 1
        tree[index] = ge
    }

    var ans = 0
    var dfs func([15]int, int, int)
    dfs = func(tree [15]int, i int, currPathSum int) {
        if tree[i] == -1 {
            return
        }
        currPathSum += tree[i]
        if i >= 7 || (tree[2 * i + 1] == -1 && tree[2 * i + 2] == -1) {
            ans += currPathSum
            return
        }
        dfs(tree, 2 * i + 1, currPathSum)
        dfs(tree, 2 * i + 2, currPathSum)
    }

    dfs(tree, 0, 0)

    return ans
}