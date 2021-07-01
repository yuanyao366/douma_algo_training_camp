class Solution {
public:
    // 1. 纵向扫描
    // 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    string longestCommonPrefix1(vector<string>& strs) {
        if (strs.size() == 0) return "";

        int rows = strs.size();
        int cols = strs[0].length();
        for (int i = 0; i < cols; i++) {
            char firstChar = strs[0][i];
            for (int j = 1; j < rows; j++) {
                if (strs[j].length() == i || strs[j][i] != firstChar) {
                    return strs[0].substr(0, i);
                }
            }
        }
        return strs[0];
    }

    // 2. 横向扫描
    // 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    string longestCommonPrefix2(vector<string>& strs) {
        if (strs.size() == 0) return "";

        string prefix = strs[0];
        for (int i = 1; i < strs.size(); i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) return "";
        }
        return prefix;
    }

    string longestCommonPrefix(string str1, string str2) {
        int len = min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1[index] == str2[index]) {
            index++;
        }
        return str1.substr(0, index);
    }

    // 3. 分治思想
    // 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(mlogn) 空间复杂度主要取决于递归调用的层数，层数最大为 logn，每层需要 m 的空间存储返回结果。
    string longestCommonPrefix(vector<string>& strs) {
        if (strs.size() == 0) return "";
        return lcp(strs, 0, strs.size() - 1);
    }

    string lcp(vector<string>& strs, int left, int right) {
        if (left == right) return strs[left];

        int mid = left + (right - left) / 2;
        string leftLcp = lcp(strs, left, mid);
        string rightLcp = lcp(strs, mid + 1, right);

        return longestCommonPrefix(leftLcp, rightLcp);
    }

};