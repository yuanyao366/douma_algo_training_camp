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
        // BFS
    vector<int> rightSideView(TreeNode* root) {
        if (!root) return {};

        vector<int> res;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode *curr = q.front();
                q.pop();
                if (i == size - 1) res.push_back(curr->val);
                if (curr->left) q.push(curr->left);
                if (curr->right) q.push(curr->right);
            }
        }

        return res;
    }

    // DFS
    vector<int> rightSideView1(TreeNode* root) {
        vector<int> res;
        preOrder(root, 0, res);
        return res;
    }

    void preOrder(TreeNode* node, int currLevel, vector<int>& res) {
        if (!node) return;

        if (res.size() == currLevel)
            res.push_back(node->val);

        preOrder(node->right, currLevel + 1, res);
        preOrder(node->left, currLevel + 1, res);
    }


};