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
    int sum = 0;
public:
    // DFS 前序遍历
    int sumOfLeftLeaves1(TreeNode* root) {
        preOrder(root, root);
        return sum;
    }

    void preOrder(TreeNode* node, TreeNode* parent) {
        if (!node) return;

        if (!node->left && !node->right && parent->left == node) {
            sum += node->val;
        }

        preOrder(node->left, node);
        preOrder(node->right, node);
    }

    // DFS 后序遍历
    int sumOfLeftLeaves(TreeNode* root) {
        return postOrder(root, root);
    }

    int postOrder(TreeNode* node, TreeNode* parent) {
        if (!node) return 0;

        if (!node->left && !node->right && parent->left == node) {
            return node->val;
        }

        int leftLeavesSum = postOrder(node->left, node);
        int rightLeavesSum = postOrder(node->right, node);

        return leftLeavesSum + rightLeavesSum;
    }

    // BFS
    int sumOfLeftLeaves3(TreeNode* root) {
        if (!root) return 0;

        int sum;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode *curr = q.front();
                q.pop();

                if (curr->left) {
                    if (isLeafNode(curr->left)) {
                        sum += curr->left->val;
                    } else {
                        q.push(curr->left);
                    }
                }
                if (curr->right && !isLeafNode(curr->right)) q.push(curr->right);
            }
        }

        return sum;
    }

    bool isLeafNode(TreeNode* node) {
        return !node->left && !node->right;
    }
};