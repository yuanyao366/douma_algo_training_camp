var repeatedSubstringPattern = function(s) {
    /*
    字符串 s 拼接的目的：可以在 s + s 中找到 s 的所有旋转后的字符串
    拼接后的字符串的头部是旋转了一圈的字符串，而尾部是没有旋转的字符串，所以需要去掉头部和尾部

    从 1 开始匹配的作用就是去掉头部

    不等于 s.length 的作用就是去掉尾部
    */
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