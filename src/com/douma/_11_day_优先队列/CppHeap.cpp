class Solution {
public:
    void test() {
        // 默认是大顶堆
        priority_queue<int> pq;

        // 小顶堆
        priority_queue<int, vector<int>, greater<int>> pq;

        // 自定义比较
        priority_queue<vector<int>, vector<vector<int>>, decltype(&cmp)> pq(cmp);

        // 往堆中添加元素
        pq.push(3);
        pq.emplace(5)

        // 删除堆顶元素
        pq.pop();
        // 拿到堆顶元素
        pq.top();
    }

    static bool cmp(vector<int>& point1, vector<int>& point2) {
        return (point1[0] * point1[0] + point1[1] * point1[1]) < (point2[0] * point2[0] + point2[1] * point2[1]);
    }

};