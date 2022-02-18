var lengthOfLastWord = function(s) {
    if (s.length == 0) return 0

    let end = s.length - 1
    while (end >= 0 && s[end] == ' ') end--
    if (end < 0) return 0

    let start = end
    while (start >= 0 && s[start] != ' ') start--

    return end - start
};