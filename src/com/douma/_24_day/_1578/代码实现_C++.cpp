class Solution {
public:
    // 贪心策略：碰到相同字母的时候，我们贪心的删除删除成本最小的字符，也可以说保留删除成本最大的字母
    int minCost(string s, vector<int>& cost) {
        int res = 0, len = s.length();
        int i = 0;
        while (i < len) {
            char c = s[i];
            int maxCost = 0, sumCost = 0;
            while (i < len && s[i] == c) {
                maxCost = max(maxCost, cost[i]);
                sumCost += cost[i];
                i++;
            }
            res += (sumCost - maxCost);
        }
        return res;
    }
};