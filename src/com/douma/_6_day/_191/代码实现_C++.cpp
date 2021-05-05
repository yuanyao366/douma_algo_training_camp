public:
    int hammingWeight(uint32_t n) {
        int res = 0;
        while (n) {
            n &= (n - 1);
            res++;
        }
        return res;
    }
    int hammingWeight2(uint32_t n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            n >>= 1;
        }
        return res;
    }
    int hammingWeight1(uint32_t n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) res++;
        }
        return res;
    }