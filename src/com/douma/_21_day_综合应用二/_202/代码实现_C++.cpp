class Solution {
public:
    // 哈希表
    bool isHappy1(int n) {
        unordered_set<int> visited;
        while (true) {
            if (n == 1) return true;
            if (visited.count(n)) return false;

            visited.insert(n);
            n = squareSum(n);
        }
    }

    // 快慢指针
    bool isHappy(int n) {
        if (n == 1) return true;
        int slow = n;
        int fast = n;
        while (true) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
            if (slow == 1 || fast == 1) return true;
            if (slow == fast) return false;
        }
    }

    int squareSum(int n) {
        int sum = 0;
        while (n != 0) {
            // bug 修复： % 10 是为了拿到个位数
            int num = n % 10;
            sum += num * num;
            n /= 10;
        }
        return sum;
    }
};