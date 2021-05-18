/**
 * @param {string} s
 * @return {string}
 */
// 使用内置 API
var reverseWords1 = function(s) {
    return s.trim().split(/\s+/).reverse().join(' ')
};

// 不使用内置 API
var reverseWords2 = function(s) {
    const sWithoutSpace = trimSpaces(s)
    reverse(sWithoutSpace, 0, sWithoutSpace.length - 1)
    reversEachWord(sWithoutSpace)
    return sWithoutSpace.join('')
};

var trimSpaces = function(s) {
    let left = 0
    let right = s.length - 1
    while (left <= right && s[left] == ' ') left++
    while (left <= right && s[right] == ' ') right--

    output = []
    while (left <= right) {
        if (s[left] != ' ') {
            output.push(s[left])
        } else if (output[output.length - 1] != ' ') {
            output.push(' ')
        }
        left++
    }
    return output
}

var reverse = function(s, left, right) {
    while (left < right) {
        [s[left], s[right]] = [s[right], s[left]]
        left++
        right--
    }
}

var reversEachWord = function(s) {
    let left = 0
    while (left < s.length) {
        if (s[left] != ' ') {
            let right = left
            while (right + 1 < s.length && s[right + 1] != ' ') right++
            reverse(s, left, right)
            left = right + 1
        } else {
            left++
        }
    }
}

// 双端队列
var reverseWords = function(s) {
    let left = 0
    let right = s.length - 1
    while (left <= right && s[left] == ' ') left++
    while (left <= right && s[right] == ' ') right--

    const deque = []
    let word = ""
    while (left <= right) {
        if (s[left] != ' ') {
            word += s[left]
        } else if (word) {
            deque.unshift(word)
            word = ""
        }
        left++
    }
    deque.unshift(word)
    return deque.join(' ')
};