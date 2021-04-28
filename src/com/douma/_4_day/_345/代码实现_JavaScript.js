var reverseVowels = function(s) {
    let arr = s.split("")
    let left = 0, right = s.length - 1
    while (left < right) {
        while (left < right && !isVowel(arr[left])) left++
        while (left < right && !isVowel(arr[right])) right--

        [arr[left], arr[right]] = [arr[right], arr[left]]

        left++
        right--
    }
    return arr.join("")
};

var isVowel = function(c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
}