var singleNumber = function(nums) {
    let seenOnce = 0, seenTwice = 0
    for (const num of nums) {
        seenOnce = ~seenTwice & (seenOnce ^ num)
        seenTwice = ~seenOnce & (seenTwice ^ num)
    }
    return seenOnce
};

var singleNumber = function(nums) {
    let res = 0;
    for (let i = 0; i < 32; ++i) {
        let oneCount = 0;
        for (const num of nums) {
            oneCount += ((num >> i) & 1);
        }
        if (oneCount % 3 != 0) {
            res |= (1 << i);
        }
    }
    return res;
};