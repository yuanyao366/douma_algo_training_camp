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
    int count = 0, maxCount = 0, prevNum = 0;
    vector<int> ans;

public:
    vector<int> findMode(TreeNode* root) {
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* node) {
        if (!node) return;
        dfs(node->left);
        update(node->val);
        dfs(node->right);
    }

    void update(int val) {
        if (val == prevNum) {
            count++;
        } else {
            prevNum = val;
            count = 1;
        }

        if (count == maxCount) {
            ans.push_back(val);
        } else if (count > maxCount) {
            ans = vector<int>{val};
            maxCount = count;
        }
    }
};