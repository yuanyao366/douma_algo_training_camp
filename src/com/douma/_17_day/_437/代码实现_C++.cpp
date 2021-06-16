/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    // DFS 计算每个节点所有路径和
    int pathSum1(TreeNode* root, int sum) {
        vector<int> sumList;
        return dfs(root, sum, sumList);
    }

    int dfs(TreeNode* node, int sum, vector<int>& sumList) {
        if(node == NULL) return 0;
        int res=0;
        vector<int> tmp;
        for(int i = 0; i < sumList.size(); i++) {
            tmp.push_back(sumList[i] + node->val);
            if(tmp.back()==sum) res++;
        }
        tmp.push_back(node->val);
        if(tmp.back() == sum) res++;
        return res + dfs(node->left, sum, tmp) + dfs(node->right, sum, tmp);
    }

    // DFS + 前缀和
    int pathSum(TreeNode* root, int sum) {
            unordered_map<int, int> prefixSumMap;
            prefixSumMap[0] = 1;
            return dfs1(root, 0, sum, prefixSumMap);
    }

    int dfs1(TreeNode* node, int currSum, int target, unordered_map<int, int>& prefixSumMap) {
        if (node == nullptr) return 0;

        currSum += node->val;
        int res = prefixSumMap[currSum - target];
        prefixSumMap[currSum] = prefixSumMap[currSum] + 1;

        res += dfs1(node->left, currSum, target, prefixSumMap);
        res += dfs1(node->right, currSum, target, prefixSumMap);

        prefixSumMap[currSum] = prefixSumMap[currSum] - 1;
        return res;
    }
};