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
    // 前序遍历 + 串接节点
    void flatten1(TreeNode* root) {
        vector<TreeNode*> res;
        dfs(root, res);

        TreeNode *curr = root;
        for (int i = 1; i < res.size(); i++) {
            curr->left = nullptr;
            curr->right = res[i];
            curr = curr->right;
        }

    }

    void dfs(TreeNode* node, vector<TreeNode*>& res) {
        if (node == nullptr) return;
        res.push_back(node);
        dfs(node->left, res);
        dfs(node->right, res);
    }

    // 边遍历边串接
    void flatten2(TreeNode* root) {
        if (root == nullptr) return;

        stack<TreeNode*> s;
        s.push(root);
        TreeNode *prev = nullptr;
        while (!s.empty()) {
            TreeNode *curr = s.top();
            s.pop();

            if (prev != nullptr) {
                prev->left = nullptr;
                prev->right = curr;
            }

            if (curr->right) {
                s.push(curr->right);
            }

            if (curr->left) {
                s.push(curr->left);
            }

            prev = curr;
        }
    }

    // 寻找前继节点
    void flatten(TreeNode* root) {
        if (root == nullptr) return;
        TreeNode* curr = root;
        while (curr) {
            if (curr->left != nullptr) {
                TreeNode *left = curr->left;
                TreeNode *pre = left;
                while (pre->right) {
                    pre = pre->right;
                }
                pre->right = curr->right;
                curr->left = nullptr;
                curr->right = left;
            }
            curr = curr->right;
        }
    }
};