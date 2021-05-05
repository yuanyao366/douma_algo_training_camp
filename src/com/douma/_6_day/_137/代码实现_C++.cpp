public:
    int singleNumber(vector<int>& nums) {
        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }

    int singleNumber(vector<int>& nums) {
        int res = 0;
        //int类型有32位，统计每一位1的个数
        for (int i = 0; i < 32; i++) {
            //统计第 i 位中 1 的个数
            int oneCount = 0;
            for (int num : nums) {
                oneCount += (num >> i) & 1;
            }
            //如果1的个数不是3的倍数，说明那个只出现一次的数字
            //的二进制位中在这一位是1
            if (oneCount % 3 == 1) {
                res |= 1 << i;
            }
        }
        return res;
    }