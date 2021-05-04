var repeatedSubstringPattern = function(s) {
    return s.repeat(2).slice(1, -1).includes(s);
};

var repeatedSubstringPattern1 = function(s) {
    const n = s.length
    for (let len = 1; len * 2 <= n; len++) {
        if (n % len == 0) {
            if (s.slice(0, len).repeat(n / len) == s) return true
        }
    }
    return false
};