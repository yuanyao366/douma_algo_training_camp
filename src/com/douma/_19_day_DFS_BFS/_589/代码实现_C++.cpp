/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    // 迭代
    vector<int> preorder1(Node* root) {
        if (!root) return {};

        vector<int> res;
        stack<Node*> s;
        s.push(root);
        while (!s.empty()) {
            Node *curr = s.top();
            s.pop();

            res.push_back(curr->val);

            for (int i = curr->children.size() - 1; i >= 0; i--) {
                s.push(curr->children[i]);
            }
        }

        return res;
    }

    // 递归
    vector<int> preorder(Node* root) {
        vector<int> res;
        dfs(root, res);
        return res;
    }

    void dfs(Node* node, vector<int>& res) {
        if (!node) return;

        res.push_back(node->val);

        for (int i = 0; i < node->children.size(); i++) {
            dfs(node->children[i], res);
        }
    }
};