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
    // DFS 后序遍历
    int countNodes(TreeNode* root) {
        if (root == nullptr) return 0;

        int leftNodeCount = countNodes(root->left);
        int rightNodeCount = countNodes(root->right);

        return leftNodeCount + rightNodeCount + 1;
    }

    // 二分查找
    int countNodes2(TreeNode* root) {
        if (root == nullptr) return 0;

        TreeNode *curr = root;
        int level = 0;
        while (curr->left) {
            level++;
            curr = curr->left;
        }

        // 完全二叉树的节点数的范围是：[2^level, 2^(level + 1) - 1]
        int low = (1 << level), high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    bool exists(TreeNode* root, int level, int k) {
        int mask = 1 << (level - 1);
        TreeNode *node = root;
        while (node != nullptr && mask > 0) {
            if ((mask & k) == 0) {
                node = node->left;
            } else {
                node = node->right;
            }
            mask >>= 1;
        }

        return node != nullptr;
    }
};