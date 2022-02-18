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
    TreeNode *x, *y, *prev;
public:
    void recoverTree(TreeNode* root) {
        dfs(root);

        int tmp = x->val;
        x->val = y->val;
        y->val = tmp;
    }

    void dfs(TreeNode* node) {
        if (!node) return;

        dfs(node->left);
        if (prev && node->val < prev->val) {
            y = node;
            if (x == nullptr) {
                x = prev;
            } else {
                return;
            }
        }
        prev = node;
        dfs(node->right);
    }
};