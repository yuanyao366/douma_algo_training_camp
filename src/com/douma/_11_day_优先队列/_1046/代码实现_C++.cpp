class Solution {
public:
    int lastStoneWeight1(vector<int>& stones) {
        int n = stones.size();
        if (n == 1) return stones[0];
        for (int i = 0; i < n - 1; i++) {
            sort(stones.begin(), stones.end());
            int y = stones[n - 1];
            int x = stones[n - 2];
            if (x == 0) break;
            stones[n - 1] = y - x;
            stones[n - 2] = 0;
        }
        return stones[n - 1];
    }

    int lastStoneWeight(vector<int>& stones) {
        int n = stones.size();
        if (n == 1) return stones[0];
        priority_queue<int> pq(stones.begin(), stones.end());

        while (pq.size() > 1) {
            int y = pq.top();
            pq.pop();
            int x = pq.top();
            pq.pop();
            int diff = y - x;
            if (diff > 0) pq.push(diff);
        }

        return pq.empty() ? 0 : pq.top();
    }
};