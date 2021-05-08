class Solution {
public:
    double myPow(double x, int n) {
        long long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double res = 1.0;
        while (b != 0) {
            if (b & 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

    double myPow1(double x, int n) {
        long long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        return quickMul(x, b);
    }

    double quickMul(double x, long long n) {
        if (n == 0) return 1.0;
        double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }
};