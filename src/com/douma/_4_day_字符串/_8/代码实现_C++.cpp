public:
    int myAtoi(string s) {
        int n = s.length();
        if (n == 0) return 0;

        int i = 0;
        while (i < n && s[i] == ' ') i++;

        int sign = 1;
        if (i < n && (s[i] == '+' || s[i] == '-')) {
            sign = s[i] == '-' ? -1 : 1;
            i++;
        }

        int base = 0;
        while (i < n && s[i] >= '0' && s[i] <= '9') {
            if (base > INT_MAX / 10
                    || (base == INT_MAX / 10 && s[i] - '0' > INT_MAX % 10)) {
                if (sign > 0) return INT_MAX;
                else return INT_MIN;
            }
            base = base * 10 + (s[i++] - '0');
        }

        return sign * base;
    }