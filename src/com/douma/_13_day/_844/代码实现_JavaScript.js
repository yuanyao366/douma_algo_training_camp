/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var backspaceCompare1 = function(s, t) {
    return build(s) == build(t)
};

var build = function(s) {
    res = []
    for (const c of s) {
        if (c != '#') {
            res.push(c)
        } else if (res.length) {
            res.pop()
        }
    }
    return res.join('')
}

var backspaceCompare = function(s, t) {
    let i = s.length - 1, j = t.length - 1
    let skipS = 0, skipT = 0
    while (i >= 0 || j >= 0) {
        while (i >= 0) {
            if (s[i] == '#') {
                skipS++
                i--
            } else if (skipS > 0) {
                skipS--
                i--
            } else {
                break
            }
        }

        while (j >= 0) {
            if (t[j] == '#') {
                skipT++
                j--
            } else if (skipT > 0) {
                skipT--
                j--
            } else {
                break
            }
        }

        if (i >= 0 && j >= 0 && s[i] != t[j]) {
            return false
        }
        if ((i >= 0) != (j >= 0)) {
            return false
        }
        i--
        j--
    }
    return true
};