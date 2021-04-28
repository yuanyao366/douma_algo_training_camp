var repeatedSubstringPattern = function(s) {
    return s.repeat(2).slice(1, -1).includes(s);
};

var repeatedSubstringPattern1 = function(s) {
    const n = s.length
    for (let i = 1; i * 2 <= n; i++) {
        if (n % i == 0) {
            if (s.slice(0, i).repeat(n / i) == s) return true
        }
    }
    return false
};