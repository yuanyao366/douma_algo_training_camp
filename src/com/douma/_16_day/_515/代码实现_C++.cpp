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
    vector<int> largestValues1(TreeNode* root) {
        if (!root) return {};

        vector<int> res;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            int size = q.size();
            int maxValue = INT_MIN;
            for (int i = 0; i < size; i++) {
                TreeNode *curr = q.front();
                q.pop();
                maxValue = max(maxValue, curr->val);
                if (curr->left) q.push(curr->left);
                if (curr->right) q.push(curr->right);
            }
            res.push_back(maxValue);
        }

        return res;
    }

    // DFS
    vector<int> largestValues(TreeNode* root) {
        vector<int> res;
        preOrder(root, 0, res);
        return res;
    }

    void preOrder(TreeNode* node, int currLevel, vector<int>& res) {
        if (!node) return;

        if (res.size() == currLevel) {
            res.push_back(node->val);
        } else {
            int maxValue = max(res[currLevel], node->val);
            res[currLevel] = maxValue;
        }

        preOrder(node->left, currLevel + 1, res);
        preOrder(node->right, currLevel + 1, res);
    }
};