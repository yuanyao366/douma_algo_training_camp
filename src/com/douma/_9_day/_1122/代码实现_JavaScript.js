/**
 * @param {number[]} arr1
 * @param {number[]} arr2
 * @return {number[]}
 */
var relativeSortArray1 = function(arr1, arr2) {
    const map = new Map()
    arr2.forEach((num, i) => map.set(num, i))

    arr1.sort((x, y) => {
        if (map.has(x)) {
            return map.has(y) ? map.get(x) - map.get(y) : -1
        } else {
            return map.has(y) ? 1 : x - y
        }
    })

    return arr1
};

var relativeSortArray = function(arr1, arr2) {

    const count = new Array(1001).fill(0)
    arr1.forEach(num => count[num]++)

    let index = 0
    for (const num of arr2) {
        for (let i = 0; i < count[num]; i++) {
            arr1[index++] = num
        }
        count[num] = 0
    }

    for (let num = 0; num < count.length; num++) {
        for (let i = 0; i < count[num]; i++) arr1[index++] = num
    }

    return arr1
};