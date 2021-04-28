var reverseStr = function(s, k) {
    const arr = s.split("")

    for (let start = 0; start < arr.length; start += 2 * k) {
        let left = start;
        let right = Math.min(left + k - 1, arr.length - 1);
        while (left < right) {
            [arr[left], arr[right]] = [arr[right], arr[left]]
            left++
            right--
        }
    }

    return arr.join("")
};