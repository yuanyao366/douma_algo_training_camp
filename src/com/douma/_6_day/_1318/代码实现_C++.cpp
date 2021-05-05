public:
    int minFlips(int a, int b, int c) {
        int aOrb = a | b;
        int equal = aOrb ^ c;
        if (equal == 0) return 0;

        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int mask = 1 << i;
            if ((equal & mask) > 0) {
                if ((a & mask) == (b & mask) && (c & mask) == 0) {
                    ans += 2;
                } else {
                    ans += 1;
                }
            }
        }
        return ans;
    }