class Solution {
public:

    // 贪心策略：每次把最小的饼干分配给胃口最小的小孩，这样才能满足最多的小孩
    // g[i] 表示第 i 个小孩的胃口值
    // s[i] 表示第 i 个饼干的尺寸
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(), g.end());
        sort(s.begin(), s.end());

        int i = 0, j = 0;
        while (i < g.size() && j < s.size()) {
            if (s[j] >= g[i]) i++;
            j++;
        }

        return i;
    }

};