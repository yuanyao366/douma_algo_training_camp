class Solution {
public:
    vector<int> fairCandySwap(vector<int>& A, vector<int>& B) {
        int sumA = 0;
        for (int a : A) sumA += a;
        int sumB = 0;
        for (int b : B) sumB += b;

        int delta = (sumA - sumB) / 2;

        unordered_set<int> set;
        for (int num : A) set.insert(num);

        vector<int> ans(2);
        for (int y : B) {
            int x = y + delta;
            if (set.count(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }

        return ans;
    }
};