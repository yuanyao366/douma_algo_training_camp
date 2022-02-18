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