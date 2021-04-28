var intToRoman = function(num) {
    const nums = [1000,900,500,400,100,90,50,40,10,9,5,4,1]
    const romans = ["M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"]
    let res = ""
    for (let index = 0; index < 13; index++) {
        while (num >= nums[index]) {
            res += romans[index]
            num -= nums[index]
        }
    }
    return res
};