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
private:
    int currSum = 0;

    void dfs(TreeNode* node) {
        if (!node) return;
        dfs(node->right);
        currSum += node->val;
        node->val = currSum;
        dfs(node->left);
    }

public:
    TreeNode* convertBST(TreeNode* root) {
        dfs(root);
        return root;
    }
};