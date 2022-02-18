class Solution {
public:
    /*
    我们这样来看这个问题，公司首先将这 2N 个人全都安排飞往 B 市，
    再选出 N 个人改变它们的行程，让他们飞往 A 市。
    如果选择改变一个人的行程，那么公司将会额外付出 price_A - price_B 的费用，这个费用可正可负。

    因此最优的方案是，选出 price_A - price_B 最小的 N 个人，让他们飞往 A 市，其余人飞往 B 市。
     */
    int twoCitySchedCost(vector<vector<int>>& costs) {
        sort(begin(costs), end(costs),
                [](const vector<int> &o1, const vector<int> &o2) {
            return (o1[0] - o1[1] < o2[0] - o2[1]);
        });

        int total = 0;
        int n = costs.size() / 2;
        // send the first n persons to the city A
        // and the others to the city B
        for (int i = 0; i < n; i++) {
            total += costs[i][0] + costs[i + n][1];
        }

        return total;
    }
};