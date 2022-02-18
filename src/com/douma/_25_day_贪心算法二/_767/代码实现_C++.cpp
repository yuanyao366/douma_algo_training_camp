class Solution {
public:
    string reorganizeString(string s) {
        int n = s.length();
        // 1. 统计每个字符出现的次数
        vector<int> count(26);
        for (char c : s) {
            int index = c - 'a';
            count[index]++;
            if (count[index] > (n + 1) / 2) return "";
        }

        // 2. 拿到出现次数最多的
        int maxCountIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > count[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        // 3. 先将出现次数最多的字符放在偶数索引上
        vector<char> res(n);
        int idx = 0;
        while (count[maxCountIndex] > 0) {
            res[idx] = (char)(maxCountIndex + 'a');
            idx += 2;
            count[maxCountIndex]--;
        }

        // 4. 将其他的字符放到其他的位置
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                if (idx >= n) idx = 1;
                res[idx] = (char)(i + 'a');
                idx += 2;
                count[i]--;
            }
        }

        string ans = "";
        for (char c : res) {
            ans += c;
        }

        return ans;
    }
};