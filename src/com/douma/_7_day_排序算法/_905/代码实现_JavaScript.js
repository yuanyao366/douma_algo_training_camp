/**
 * @param {number[]} A
 * @return {number[]}
 */
var sortArrayByParity = function(A) {
    let less = 0, great = A.length - 1
    while (less < great) {
        if (A[less] % 2 > A[great] % 2) {
            [A[less], A[great]] = [A[great], A[less]]
        }
        if (A[less] % 2 == 0) less++
        if (A[great] % 2 != 0) great--
    }
    return A
};