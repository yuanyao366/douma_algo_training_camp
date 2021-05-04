public:
    bool repeatedSubstringPattern(string s) {
        return (s + s).find(s, 1) != s.size();
    }

    bool repeatedSubstringPattern1(string s) {
        int n = s.size();
        for (int len = 1; len * 2 <= n; len++) {
            if (n % len == 0) {
                bool matched = true;
                int i = 0;
                for (int j = len; j < n; j++, i++) {
                    if (s[i] != s[j]) {
                        matched = false;
                        break;
                    }
                }
                if (matched) return true;
            }
        }
        return false;
    }