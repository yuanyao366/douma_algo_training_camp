class Solution {
public:
    int divide(int a, int b) {
        if (a == INT_MIN && b == -1) return INT_MAX;

        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        if (a > 0) a = -a;
        if (b > 0) b = -b;
        
        unsigned int res = 0;
        while (a <= b) {
            int value = b;
            // 如果不用 unsigned 的话，那么当 a = -2147483648, b = 1 的时候，k 会越界
            unsigned int k = 1;
            // 0xc0000000 是十进制 -2^30 的十六进制的表示
            // 判断 value >= 0xc0000000 的原因：保证 value + value 不会溢出
            // 可以这样判断的原因是：0xc0000000 是最小值 -2^31 的一半，
            // 而 a 的值不可能比 -2^31 还要小，所以 value 不可能比 0xc0000000 小
            while (value >= 0xc0000000 && a <= value + value) {
                k += k;
                value += value;
            }
            a -= value;
            res += k;
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }

    int divide(int a, int b) {
        if (a == INT_MIN && b == -1) return INT_MAX;

        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        if (a > 0) a = -a;
        if (b > 0) b = -b;
        
        unsigned int res = 0;
        while (a <= b) {
            a -= b;
            res++;
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }

    int divide2(int a, int b) {
        if (a == INT_MIN && b == -1) return INT_MAX;

        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        unsigned int ua = abs(a);
        unsigned int ub = abs(b);
        unsigned int res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((ua >> i) >= ub) {
                ua = ua - (ub << i);
                res += 1 << i;
            }
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }
};