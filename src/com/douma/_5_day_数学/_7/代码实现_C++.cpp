public:
    int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // MAX_VALUE = 2^31 - 1 = 2147483647
            if (res > INT_MAX / 10
                || (res == INT_MAX / 10 && pop > 7)) return 0;
            // MIN_VALUE = -2^31 = -2147483648
            if (res < INT_MIN / 10
                || (res == INT_MIN / 10 && pop < -8)) return 0;
            res = res * 10 + pop;
        }
        return res;
    }