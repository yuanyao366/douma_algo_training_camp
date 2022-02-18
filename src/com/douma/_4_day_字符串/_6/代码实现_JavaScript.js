var convert = function(s, numRows) {
    if (numRows == 1) return s
    const n = s.length
    const ret = new Array(Math.min(n, numRows)).fill("")

    let currRow = 0, goingDown = false;
    for (let i = 0; i < n; i++) {
        ret[currRow] += s[i]
        if (currRow == 0 || currRow == numRows - 1) goingDown = !goingDown
        if (goingDown) currRow++
        else currRow--
    }

    for (let i = 1; i < ret.length; i++) {
        ret[0] += ret[i]
    }

    return ret[0]
};