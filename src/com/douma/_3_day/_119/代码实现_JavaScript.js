var getRow = function(rowIndex) {
    const oneRow = new Array(rowIndex + 1).fill(0)
    oneRow[0] = 1
    for (let row = 1; row <= rowIndex; row++) {
        for (let col = row; col > 0; col--) {
            oneRow[col] += oneRow[col - 1]
        }
    }
    return oneRow
};