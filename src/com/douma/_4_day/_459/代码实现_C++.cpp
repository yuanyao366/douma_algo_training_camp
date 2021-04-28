public:
    bool repeatedSubstringPattern(string s) {
        return (s + s).find(s, 1) != s.size();
    }

    bool repeatedSubstringPattern1(string s) {
        int n = s.size();
        for (int i = 1; i * 2 <= n; i++) {
            if (n % i == 0) {
                bool matched = true;
                for (int j = i; j < n; j++) {
                    if (s[j] != s[j - i]) {
                        matched = false;
                        break;
                    }
                }
                if (matched) return true;
            }
        }
        return false;
    }