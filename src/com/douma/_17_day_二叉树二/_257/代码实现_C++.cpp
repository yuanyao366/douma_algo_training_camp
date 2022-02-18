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
    vector<string> binaryTreePaths(TreeNode* root) {
        vector<string> res;
        dfs(root, "", res);
        return res;
    }

    void dfs(TreeNode* node, string path, vector<string>& res) {
        if (node == nullptr) return;
        if (node->left == nullptr && node->right == nullptr) {
            res.push_back(path + to_string(node->val));
            return;
        }

        dfs(node->left, path + to_string(node->val) + "->", res);
        dfs(node->right, path + to_string(node->val) + "->", res);
    }
};