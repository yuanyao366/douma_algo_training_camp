class Solution {
private:
    int ans = 0;
public:
    int pathSum(vector<int>& nums) {
        vector<int> tree(16, -1);
        for (int num : nums) {
            int bai = num / 100;
            int shi = num % 100 / 10;
            int ge = num % 10;

            int index = (1 << (bai - 1)) - 1 + shi - 1;
            tree[index] = ge;
        }

        dfs(0, tree, 0);
        return ans;
    }

    void dfs(int i, vector<int>& tree, int sum) {
        if (tree[i] == -1) return;

        sum += tree[i];
        if ((i + 1 >= 8) || (tree[2 * i + 1] == -1 && tree[2 * i + 2] == -1)) {
            ans += sum;
            return;
        }

        dfs(2 * i + 1, tree, sum);
        dfs(2 * i + 2, tree, sum);
    }
};