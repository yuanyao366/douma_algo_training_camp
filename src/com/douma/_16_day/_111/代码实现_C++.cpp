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
    // DFS 前序遍历 迭代
    int minDepth1(TreeNode* root) {
        if (!root) return 0;

        int res = INT_MAX;
        stack<pair<TreeNode*, int>> s;
        s.push(make_pair(root, 1));
        while (!s.empty()) {
            pair<TreeNode*, int> curr = s.top();
            s.pop();
            TreeNode *currNode = curr.first;
            int currLevel = curr.second;

            // 如果是叶子节点，则更新最小深度值
            if (!currNode->left && !currNode->right) {
                res = min(res, currLevel);
            }

            if (currNode->right) s.push(make_pair(currNode->right, currLevel + 1));
            if (currNode->left) s.push(make_pair(currNode->left, currLevel + 1));
        }

        return res;
    }

    // DFS 前序遍历 - 递归
    int minDepth2(TreeNode* root) {
        if (!root) return 0;
        preOrder(root, 1);
        return res;
    }

private:
    int res = INT_MAX;
    void preOrder(TreeNode* node, int currLevel) {
        if (!node) return;

        if (!node->left && !node->right) {
            res = min(res, currLevel);
        }

        preOrder(node->left, currLevel + 1);
        preOrder(node->right, currLevel + 1);
    }

public:
    // DFS 后序遍历 - 递归
    int minDepth3(TreeNode* root) {
        if (!root) return 0;
        if (!root->left && !root->right) return 1;

        int leftMinDepth = minDepth3(root->left);
        int rightMinDepth = minDepth3(root->right);

        if (!root->left) {
            return rightMinDepth + 1;
        } else if (!root->right) {
            return leftMinDepth + 1;
        } else {
            return min(leftMinDepth, rightMinDepth) + 1;
        }
    }

    // BFS
    int minDepth(TreeNode* root) {
        if (!root) return 0;

        queue<TreeNode*> q;
        q.push(root);

        int res = 0;
        while (!q.empty()) {
            int size = q.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode *curr = q.front();
                q.pop();
                if (!curr->left && !curr->right) return res;
                if (curr->left) q.push(curr->left);
                if (curr->right) q.push(curr->right);
            }
        }

        return res;
    }

};