class Solution {
public:
    // DFS
    bool isUgly1(int n) {
        if (n == 0) return false;
        return dfs(n);
    }

    bool dfs(int n) {
        if (n == 1) return true;

        vector<int> factors = {2, 3, 5};
        for (int factor : factors) {
            if (n % factor == 0 && dfs(n / factor)) {
                return true;
            }
        }
        return false;
    }

    // 迭代
    bool isUgly(int n) {
        if (n == 0) return false;
        vector<int> factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) n /= factor;
        }
        return n == 1;
    }
};