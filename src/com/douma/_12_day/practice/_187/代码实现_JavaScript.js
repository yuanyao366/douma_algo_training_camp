/**
 * @param {string} s
 * @return {string[]}
 */
// 参考字符串匹配 RK 算法，使用滚动哈希方法
// 时间复杂度：O(n)
var findRepeatedDnaSequences = function(s) {
    if (s.length <= 10) return []

    const k = 10;

    // 将 ACGT 看成 4 进制
    const toInt = {
        A: 0,
        C: 1,
        G: 2,
        T: 3,
    }

    const base = 4
    const shiftLeft = Math.pow(base, k - 1)

    const seen = new Set()
    const output = new Set()

    let currWindowHash = 0
    // 计算第一个窗口对应的 hash 值
    for (let i = 0; i < k; i++) {
        currWindowHash = currWindowHash * base + toInt[s[i]]
    }
    seen.add(currWindowHash)

    // 从第二个窗口开始
    let left = 1, right = k
    while (right < s.length) {
        currWindowHash = currWindowHash - toInt[s[left - 1]] * shiftLeft
        currWindowHash = currWindowHash * base + toInt[s[right]]

        if (seen.has(currWindowHash)) output.add(s.substr(left, right - left + 1))
        else seen.add(currWindowHash)

        left++
        right++
    }

    return [...output]
};