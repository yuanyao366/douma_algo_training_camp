public:
    // 字符串匹配算法：暴力解法、RK 算法、BM 算法、KMP 算法
    // 详细参见：课程 A：应用篇 -> 字符串匹配

    // 暴力
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(1)
    int strStr(string haystack, string needle) {
            int n = needle.size();
            if (n == 0) return 0;
            if (haystack.size() < n) return -1;
            for (int i = 0; i < haystack.size() - n + 1; i++) {
                // 如果等于 needle 的第一个字符，再进行 n 个字符的比较
                if (haystack[i] == needle[0]
                        && haystack.substr(i, n) == needle) {
                    return i;
                }
            }
            return -1;
        }

    // KMP
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(n)
    int strStr(string haystack, string needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return 0;
        if (m < n) return -1;

        vector<int> next = getNexts(needle);

        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j > 0 && haystack[i] != needle[j]) {
                j = next[j - 1] + 1;
            }
            if (haystack[i] == needle[j]) j++;
            if (j == n) return i - n + 1;
        }
        return -1;
    }

    vector<int> getNexts(string needle) {
        int n = needle.length();
        vector<int> next(n - 1);
        if (n == 1) return next;

        next[0] = -1;
        for (int j = 1; j < n - 1; j++) {
            int pre = next[j - 1];
            while (pre != -1 && needle[pre + 1] != needle[j]) {
                pre = next[pre];
            }
            if (needle[pre + 1] == needle[j]) {
                pre++;
            }
            next[j] = pre;
        }
        return next;
    }