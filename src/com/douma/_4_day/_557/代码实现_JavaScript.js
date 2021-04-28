var reverseWords = function(s) {
    const n = s.length
    const arr = s.split("")
    let left = 0
    while (left < n) {
        if (arr[left] != ' ') {
            let right = left
            while (right + 1 < n && arr[right + 1] != ' ') right++
            reverseWord(arr, left, right)
            left = right + 1
        } else {
            left++
        }
    }

    return arr.join("")
};

var reverseWord = function(arr, left, right) {
    while (left < right) {
        [arr[left], arr[right]] = [arr[right], arr[left]]
        left++
        right--
    }
}

// 函数式编程
var reverseWords2 = function(s) {
    return s.split(" ").map(word => word.split("").reverse().join("")).join(" ")
}