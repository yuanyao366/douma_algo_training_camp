var totalHammingDistance = function(nums) {
    const n = nums.length
    const cnt = new Array(32).fill(0)
    for (let num of nums) {
        let i = 0
        while (num) {
            cnt[i] += (num & 1)
            num >>= 1
            i++
        }
    }
    let res = 0
    for (const k of cnt) res += k * (n - k)
    return res
};