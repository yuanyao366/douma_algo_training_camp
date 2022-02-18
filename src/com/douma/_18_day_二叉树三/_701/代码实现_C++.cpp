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
    // 1. 迭代
    TreeNode* insertIntoBST1(TreeNode* root, int val) {
        if (root == nullptr) return new TreeNode(val);

        TreeNode *curr = root;
        while (curr) {
            if (val < curr->val && curr->left == nullptr) {
                curr->left = new TreeNode(val);
                return root;
            } else if (val > curr->val && curr->right == nullptr) {
                curr->right = new TreeNode(val);
                return root;
            }

            if (val < curr->val) {
                curr = curr->left;
            } else {
                curr = curr->right;
            }
        }

        return root;
    }

    // 2. 递归
    TreeNode* insertIntoBST(TreeNode* root, int val) {
        if (root == nullptr) return new TreeNode(val);

        if (val < root->val) {
            root->left = insertIntoBST(root->left, val);
        } else {
            root->right = insertIntoBST(root->right, val);
        }

        return root;
    }
};