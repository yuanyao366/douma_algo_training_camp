var countAndSay = function(n) {
    let curr = "1"
    for (let i = 1; i < n; i++) {
        const prev = curr
        curr = ""

        let say = prev[0], count = 1
        for (let j = 1; j < prev.length; j++) {
            if (prev[j] == say) {
                count++
            } else {
                curr += count
                curr += say

                say = prev[j]
                count = 1
            }
        }
        curr += count
        curr += say
    }
    return curr
};