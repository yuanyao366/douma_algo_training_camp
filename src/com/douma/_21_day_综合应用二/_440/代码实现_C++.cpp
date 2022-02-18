class Solution {
public:
    int findKthNumber(int n, int k) {
        int curr = 1;
        k = k - 1;
        while (k > 0) {
            int nodes = calNodes(n, curr, curr + 1);
            if (nodes <= k) { // 不在当前的前缀中
                curr += 1;
                k -= nodes;
            } else { // 在当前的前缀中
                curr *= 10;
                k -= 1;
            }
        }
        return curr;
    }

    // 计算当前 curr 前缀树中小于 n 的节点数
    int calNodes(long n, long curr, long next) {
        int nodes = 0;
        while (curr <= n) {
            /*
            你可能会问:咦？怎么是 n+1 ,而不是 n  呢？不是说好了 n  是上界吗？

            我举个例子，假若现在上界 n 为 12，算出以 1 为前缀的子节点数，
            首先 1 本身是一个节点，接下来要算下面 10，11，12，一共有 4 个子节点。

            那么如果用 min(n, next)  - curr 会怎么样？

            这时候算出来会少一个，12 - 10 加上根节点，最后只有 3 个。因此我们务必要写 n+1。
            */
            nodes += min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        return nodes;
    }
};