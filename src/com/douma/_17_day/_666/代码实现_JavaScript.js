/**
 * @param {number[]} nums
 * @return {number}
 */
var pathSum = function(nums) {
    const tree = new Array(16).fill(-1)
    for (const num of nums) {
        const bai = Math.floor(num / 100)
        const shi = Math.floor(num % 100 / 10)
        const ge = num % 10
        const index = (1 << (bai - 1)) - 1 + shi - 1
        tree[index] = ge;
    }

    let ans = 0
    const dfs = (i, sum) => {
        if (tree[i] == -1) return
        sum += tree[i]
        if ((i + 1 >= 8) || (tree[2 * i + 1] == -1 && tree[2 * i + 2] == -1)) {
            ans += sum
            return
        }
        dfs(2 * i + 1, sum)
        dfs(2 * i + 2, sum)
    }

    dfs(0, 0)
    return ans
};