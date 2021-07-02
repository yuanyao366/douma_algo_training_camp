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
    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
         vector<vector<int>> res;
         vector<int> path;
         dfs(root, path, res, targetSum);
         return res;
    }

    void dfs(TreeNode* node, vector<int>& path, vector<vector<int>>& res, int targetSum) {
        if (node == nullptr) return;
        path.push_back(node->val);

        targetSum -= node->val;
        if (node->left == nullptr && node->right == nullptr && targetSum == 0) {
            res.push_back(path);
        }

        dfs(node->left, path, res, targetSum);
        dfs(node->right, path, res, targetSum);

        path.pop_back();
    }
};