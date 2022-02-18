class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& T) {
        int n = T.size();
        if (n == 1) return {0};

        vector<int> res(n);
        stack<int> s;
        for (int i = 0; i < n; i++) {
            while (!s.empty() && T[i] > T[s.top()]) {
                int prevIndex = s.top();
                res[prevIndex] = i - prevIndex;
                s.pop();
            }
            s.push(i);
        }

        return res;
    }
};