/**
 * @param {number[][]} envelopes
 * @return {number}
 */
var maxEnvelopes = function(envelopes) {
    envelopes.sort((e1, e2) => {
        if (e1[0] !== e2[0]) {
            return e1[0] - e2[0];
        } else {
            return e2[1] - e1[1];
        }
    })

    const n = envelopes.length;
    const dp = new Array(n).fill(1)

    let res = 1
    for (let j = 1; j < n; j++) {
        for (let i = 0; i < j; i++) {
            if (envelopes[j][1] > envelopes[i][1]) {
                dp[j] = Math.max(dp[i] + 1, dp[j])
                res = Math.max(res, dp[j])
            }
        }
    }

    return res
};


/**
 俄罗斯套娃这道题的输入改掉了，之前的输入数据规模是 5000 ，所以时间复杂度为 O(n^2) 的动态规划方案是可以的

 现在的输入数据规模改为 10^5 ，所以，时间复杂度为 O(n^2) 会超时了
 现在题目的输入数据规模为：
 1 <= envelopes.length <= 10^5
 envelopes[i].length == 2
 1 <= wi, hi <= 10^5
 */
// 所以需要使用二分解法
// 时间复杂度 O(nlogn)
// 空间复杂度 O(n)
var maxEnvelopes = function(envelopes) {
    envelopes.sort((e1, e2) => {
        if (e1[0] !== e2[0]) {
            // 先按照宽度升序排列
            return e1[0] - e2[0]
        } else {
            // 宽度相同的话，按照高度降序排列
            return e2[1] - e1[1]
        }
    })

    const n = envelopes.length
    // res 用于存储所有可套娃信封的高度值
    // 保证 res 中的元素肯定是升序排列的
    const res = new Array()

    // 1. 将宽度最小的信封的高度值放在 res 中
    res.push(envelopes[0][1])

    // 2. 从第二个信封开始，遍历并处理每一个信封
    for (let i = 1; i < n; i++) {
       const currHeight = envelopes[i][1]
       // 2.1 如果当前信封的高度大于 res 中最后一个信封的高度
       if (currHeight > res[res.length - 1]) {
           // 那么，可以将当前的信封放入到 res 中
           // (当前信封的宽度肯定大于等于果集中最后一个信封的宽度，因为是按照宽度升序排列的)
           res.push(currHeight)
       } else { // 2.2 当前信封的高度小于等于 res 中最后一个信封的高度
           // 将当前信封插入到 res 合适的位置上
           // 先使用二分查找，在 res 中查找 currHeight 合适的位置
           const index = binarySearch(res, currHeight)
           // 将当前信封替换之前这个位置上的信封
            // 之所以可以将当前信封替换掉第 index 处的信封，是因为：
            // ① 当前信封的宽度大于等于第 index - 1 处的信封的宽度
            // ② 当前信封的高度大于第 index - 1 处的信封的高度
            res[index] = currHeight;
       }
    }

    return res.length
};

const binarySearch = (res, target) => {
    let low = 0, high = res.length - 1;
    while (low < high) {
        const mid = Math.floor((high - low) / 2) + low;
        if (res[mid] < target) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    return low;
}