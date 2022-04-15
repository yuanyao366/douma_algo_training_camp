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
        // 清 0 的原因：
        // 在 arr1 中等于 num 的所有的元素都放在一起了
        // 也就是 num 排好序了，为了可以区分出已经排好序，和还没排序的元素
        // 我们将排好序的元素出现的次数清 0
        count[num] = 0
        // 清 0 后，在下面的循环中就不用处理了，下面的循环只要处理在 arr2 中没出现的元素了
    }

    for (let num = 0; num < count.length; num++) {
        for (let i = 0; i < count[num]; i++) arr1[index++] = num
    }

    return arr1
};