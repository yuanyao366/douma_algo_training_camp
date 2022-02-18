public:
    string intToRoman(int num) {
        int nums[13] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        string romans[13] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        string res;
        int index = 0;
        while (index < 13) {
            while (num >= nums[index]) {
                res += romans[index];
                num -= nums[index];
            }
            index++;
        }

        return res;
    }