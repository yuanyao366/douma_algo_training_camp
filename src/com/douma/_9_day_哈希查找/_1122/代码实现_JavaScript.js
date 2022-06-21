/**
 * @param {number[]} arr1
 * @param {number[]} arr2
 * @return {number[]}
 */
var relativeSortArray1 = function(arr1, arr2) {
    const map = new Map()

    /*
    以下一行代码相当于：
    for (let i = 0; i < arr2.length; i++) {
        map.set(arr2[i], i)
    }
    */
    // 这个是函数式编程。forEach 中，可以接收一个函数，这个函数的输入参数有两个，一个是数组中每个元素值，第二个是这个元素对应的索引
    arr2.forEach((num, i) => map.set(num, i))

    arr1.sort((x, y) => {
        if (map.has(x)) {
            // 如果 x 和 y 都出现在两个数组中，那么返回 x 和 y 在 arr2 索引的差
            //      如果差等于 0，那么 x 和 y 在 arr1 的顺序不变
            //      如果差等于负数，那么说明在 arr2 中， x 在 y 的前面，那么在 arr1 中， x 和 y 就升序排列(即 x 在 y 前面)
            //      如果差等于正数，那么说明在 arr2 中， x 在 y 的后面，那么在 arr1 中， x 和 y 就降序排列(即 y 在 x 的前面)
            // 如果 x 在 arr2 中，但是 y 不在 arr2 中，那么返回负数 (-1)，那么 x 和 y 在 arr1 中升序排列(即 x 在 y 前面)
            return map.has(y) ? map.get(x) - map.get(y) : -1
        } else {
            // 如果 x 不在 arr2 中，但是 y 在 arr2 中，那么返回正数(即 1)，那么 x 和 y 在 arr1 中降序排列(即 y 在 x 的前面)
            // 如果 x 和 y 都在 arr2 中，那么 x 和 y 就按照 x 和 y 的大小进行升序排列
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

    // 这里是对只出现在 arr1 而没有出现在 arr2 中的元素，进行计数排序
    for (let num = 0; num < count.length; num++) {
        for (let i = 0; i < count[num]; i++) arr1[index++] = num
    }

    return arr1
};