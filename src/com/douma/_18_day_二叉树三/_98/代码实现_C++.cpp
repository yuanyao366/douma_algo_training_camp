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
    // 1. 中序遍历 (边遍历边验证顺序性)
    bool isBST = true;
    TreeNode* prev = nullptr;
    bool isValidBST1(TreeNode* root) {
        dfs(root);
        return isBST;
    }

    void dfs(TreeNode* node) {
        if (node == nullptr) return;

        dfs(node->left);
        if (prev && node->val <= prev->val) {
            isBST = false;
            return;
        }
        prev = node;
        dfs(node->right);
    }

    // 2. 后序遍历
    bool isValidBST(TreeNode* root) {
        return isValidBST(root, LONG_MIN, LONG_MAX);
    }

    bool isValidBST(TreeNode* node, long long lower, long long upper) {
        if (!node) return true;
        if (node->val <= lower || node->val >= upper) {
            return false;
        }

        return isValidBST(node->left, lower, node->val) && isValidBST(node->right, node->val, upper);
    }

};