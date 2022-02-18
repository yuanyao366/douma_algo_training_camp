var singleNumber = function(nums) {
    let single = 0;
    for (const num of nums) {
        single ^= num
    }
    return single
};