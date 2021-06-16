/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
private:
    unordered_map<int, TreeNode*> parents;
    unordered_set<TreeNode*> visited;
public:
    TreeNode* lowestCommonAncestor1(TreeNode* root, TreeNode* p, TreeNode* q) {
        dfs(root);

        while (p) {
            visited.insert(p);
            p = parents[p->val];
        }

        while (q) {
            if (visited.count(q)) return q;
            q = parents[q->val];
        }

        return nullptr;
    }

    void dfs(TreeNode* node) {
        if (node == nullptr) return;

        if (node->left) parents[node->left->val] = node;
        if (node->right) parents[node->right->val] = node;

        dfs(node->left);
        dfs(node->right);
    }

    // DFS 后序遍历
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (root == nullptr) return nullptr;
        if (root == p || root == q) return root;

        TreeNode *left = lowestCommonAncestor(root->left, p, q);
        TreeNode *right = lowestCommonAncestor(root->right, p, q);

        if (left == nullptr) return right;
        if (right == nullptr) return left;
        return root;
    }
};