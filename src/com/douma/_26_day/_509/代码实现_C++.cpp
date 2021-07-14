class Solution {
public:
    // DFS
    // 时间复杂度：O(2^n)
    // 因为 n 最大为 30，比较小，所以不会超时
    // 空间复杂度：O(logn) 递归系统栈需要的空间开销
    int fib1(int n) {
        return dfs(n);
    }

    int dfs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);

        return leftFib + rightFib;
    }

    // 记忆化搜索
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    int fib2(int n) {
        vector<int> memo(n + 1, -1);
        return dfs(n, memo);
    }

    int dfs(int n, vector<int>& memo) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        if (memo[n] != -1) return memo[n];

        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);

        memo[n] = leftFib + rightFib;

        return memo[n];
    }

    // 动态规划的四个步骤
    int fib3(int n) {
        if (n <= 1) return n;
        // 1. 定义状态数组，dp[i] 表示的是数字 i 的斐波那契数
        vector<int> dp(n + 1);

        // 2. 状态初始化
        dp[0] = 0;
        dp[1] = 1;

        // 3. 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. 返回最终需要的状态值
        return dp[n];
    }

    // 状态数组空间压缩
    int fib(int n) {
        if (n <= 1) return n;
        // 只存储前两个状态
        int prev = 0;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
};