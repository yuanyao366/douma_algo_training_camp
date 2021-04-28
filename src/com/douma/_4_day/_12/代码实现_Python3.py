def intToRoman(self, num: int) -> str:
    nums = [1000,900,500,400,100,90,50,40,10,9,5,4,1]
    romans = ["M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"]

    res = ''
    for index in range(13):
        while num >= nums[index]:
            res += romans[index]
            num -= nums[index]

    return res