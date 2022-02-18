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
    // 递归
    vector<int> postorder;
    unordered_map<int, int> idxMap;
    int rootIndex;
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        this->postorder = postorder;
        rootIndex = postorder.size() - 1;
        for (int i = 0; i < inorder.size(); i++) {
            idxMap[inorder[i]] = i;
        }
        return buildTree(0, inorder.size() - 1);
    }

    TreeNode* buildTree(int left, int right) {
        if (left > right) return nullptr;

        int rootVal = postorder[rootIndex];
        TreeNode* root = new TreeNode(rootVal);
        rootIndex--;

        int mid = idxMap[rootVal];

        root->right = buildTree(mid + 1, right);
        root->left = buildTree(left, mid - 1);

        return root;
    }
};