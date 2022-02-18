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
    // 迭代
    TreeNode* buildTree1(vector<int>& preorder, vector<int>& inorder) {
        stack<TreeNode*> s;
        TreeNode *root = new TreeNode(preorder[0]);
        s.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.size(); i++) {
            int childVal = preorder[i];
            TreeNode *parentNode = s.top();
            if (parentNode->val != inorder[inorderIndex]) {
                parentNode->left = new TreeNode(childVal);
                s.push(parentNode->left);
            } else {
                while (!s.empty() && s.top()->val == inorder[inorderIndex]) {
                    parentNode = s.top();
                    s.pop();
                    inorderIndex++;
                }
                parentNode->right = new TreeNode(childVal);
                s.push(parentNode->right);
            }
        }
        return root;
    }

    // 递归
    vector<int> preorder;
    unordered_map<int, int> idxMap;
    int rootIndex = 0;
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        this->preorder = preorder;
        for (int i = 0; i < inorder.size(); i++) {
            idxMap[inorder[i]] = i;
        }
        return buildTree(0, inorder.size() - 1);
    }

    TreeNode* buildTree(int left, int right) {
        if (left > right) return nullptr;

        int rootVal = preorder[rootIndex];
        TreeNode* root = new TreeNode(rootVal);
        rootIndex++;

        int mid = idxMap[rootVal];

        root->left = buildTree(left, mid - 1);
        root->right = buildTree(mid + 1, right);

        return root;
    }
};