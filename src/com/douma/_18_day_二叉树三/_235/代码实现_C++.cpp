/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Solution {
public:
    // 1. 递归
    TreeNode* lowestCommonAncestor1(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root) return NULL;
        if (root == p || root == q) return root;
        TreeNode *left = lowestCommonAncestor1(root->left, p, q);
        TreeNode *right = lowestCommonAncestor1(root->right, p, q);
        if (!left) return right;
        if (!right) return left;
        return root;
    }

    // 2. 迭代
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root) return NULL;
        if (root == p || root == q) return root;
        TreeNode *ancestor = root;
        while (ancestor) {
            if (p->val > ancestor->val && q->val > ancestor->val) {
                ancestor = ancestor->right;
            } else if (p->val < ancestor->val && q->val < ancestor->val) {
                ancestor = ancestor->left;
            } else {
                break;
            }
        }
        return ancestor;
    }
};