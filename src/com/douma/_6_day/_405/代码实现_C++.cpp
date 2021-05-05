public:
    string toHex(int num) {
        vector<char> hexChars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if (num == 0) return "0";
        unsigned n = num;
        string res = "";
        while (n != 0) {
            res = hexChars[(n & 15)] + res;
            n >>= 4;
        }
        return res;
    }