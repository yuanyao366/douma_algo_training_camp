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
    int rob(TreeNode* root) {
        vector<int> res = dfs(root);
        return max(res[0], res[1]);
    }

    vector<int> dfs(TreeNode* node) {
        if (!node) return {0, 0};

        vector<int> left = dfs(node->left);
        vector<int> right = dfs(node->right);

        vector<int> res(2);
        // 1. 选择不偷当前的节点，当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
        res[0] = max(left[0], left[1]) + max(right[0], right[1]);
        // 2. 选择偷当前的节点：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
        res[1] = left[0] + right[0] + node->val;
        return res;
    }
};