class Solution {
public:
    // 参考【课程 A 中应用篇】的字符串匹配 RK 算法，使用滚动哈希方法
    // 时间复杂度：O(n)
    vector<string> findRepeatedDnaSequences(string s) {
        int n = s.length();
        if (n <= 10) return {};

        int k = 10;
        // 将 ACGT 看成 4 进制
        unordered_map<char, int> toInt{{'A', 0}, {'C' , 1}, {'G', 2}, {'T', 3}};
        int base = 4;
        int shiftLeft = pow(base, k - 1);

        unordered_set<int> seen;
        unordered_set<string> output;

        int currWindowHash = 0;
        // 计算第一个窗口对应的 hash 值
        for (int i = 0; i < k; ++i) {
            currWindowHash = currWindowHash * base + toInt[s[i]];
        }
        seen.insert(currWindowHash);

        int left = 1, right = k;
        while (right < n) {
            // 删除 s.charAt(left - 1)
            currWindowHash = currWindowHash - toInt[s[left - 1]] * shiftLeft;
            // 添加 s.charAt(right)
            currWindowHash = currWindowHash * base + toInt[s[right]];
            if (seen.count(currWindowHash)) output.insert(s.substr(left, right - left + 1));
            else seen.insert(currWindowHash);

            left++;
            right++;
        }

        return vector<string>(output.begin(), output.end());
    }
};