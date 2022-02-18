public:
    int hammingDistance(int x, int y) {
        int diff =  x ^ y;
        int res = 0;
        while (diff) {
            diff &= (diff - 1);
            res++;
        }
        return res;
    }