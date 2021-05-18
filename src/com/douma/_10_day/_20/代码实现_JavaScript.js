/**
 * @param {string} s
 * @return {boolean}
 */
var isValid1 = function(s) {
    if (s.length % 2 == 1) return false
    const st = []
    for (const c of s) {
        if (c == '(' || c == '[' || c == '{') {
            st.push(c)
        } else {
            if (!st.length) return false
            const topChar = st[st.length - 1]
            if (c == ')' && topChar != '(') return false;
            if (c == ']' && topChar != '[') return false;
            if (c == '}' && topChar != '{') return false;
            st.pop();
        }
    }
    return !st.length
};

var isValid = function(s) {
    if (s.length % 2 == 1) return false
    const pairs = new Map([
        ['(', ')'],
        ['[', ']'],
        ['{', '}']
    ]);

    const st = []
    for (const c of s) {
        if (pairs.has(c)) {
            st.push(c)
        } else {
            if (!st.length) return false
            const topChar = st[st.length - 1]
            if (c != pairs.get(topChar)) return false;
            st.pop();
        }
    }
    return !st.length
};

var isValid = function(s) {
    if (s.length % 2 == 1) return false
    const stack = []
    for (const c of s) {
        if (c == '(') {
            stack.push(c)
        } else {
            if (!stack.length) return false
            stack.pop();
        }
    }
    return !stack.length
};