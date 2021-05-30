class Solution {
public:
    bool backspaceCompare1(string s, string t) {
        return build(s) == build(t);
    }

    // 重建字符串
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    string build(string str) {
        string ret;
        for (char c : str) {
            if (c != '#') {
                ret.push_back(c);
            } else if (!ret.empty()) {
                ret.pop_back();
            }
        }
        return ret;
    }

    // 双指针（从后往前遍历）
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    bool backspaceCompare(string s, string t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (i >= 0 || j >= 0) {
            // 回退 S 字符串的字符
            while (i >= 0) {
                if (s[i] == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            // 回退 T 字符串的字符
            while (j >= 0) {
                if (t[j] == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // 判断 S 和 T 是否相等
            // 如果当前的 i 和 j 对应的字符不相等
            if (i >= 0 && j >= 0 && s[i] != t[j]) return false;

            // 有一个指针到头了，还有一个不到头，则返回 false
            if ((i >= 0) != (j >= 0)) return false;

            i--;
            j--;
        }
        return true;

    }
};