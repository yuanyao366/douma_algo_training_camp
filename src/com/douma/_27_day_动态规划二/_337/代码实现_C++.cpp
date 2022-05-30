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
        /*
        选择不偷当前的节点的话，那么有四种情况：
            ① 偷了左子节点，偷了右子节点  --》 left[1] + right[1]
            ② 偷了左子节点，不偷右子节点  --》 left[1] + right[0]
            ③ 不偷左子节点，偷了右子节点  --》 left[0] + right[1]
            ④ 不偷左子节点，不偷右子节点  --》 left[0] + right[0]
         */
        res[0] = max(left[0], left[1]) + max(right[0], right[1]);
        // 2. 选择偷当前的节点：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
        res[1] = left[0] + right[0] + node->val;
        return res;
    }
};