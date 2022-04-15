public:
    string toHex(int num) {
        vector<char> hexChars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if (num == 0) return "0";
        // c++ 没有无符号右移，所以先将整数转成无符号整数，然后在右移
        unsigned n = num;
        string res = "";
        while (n != 0) {
            res = hexChars[(n & 15)] + res;
            n >>= 4;
        }
        return res;
    }