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
    vector<int> postorder1(Node* root) {
        if (!root) return {};

        vector<int> res;
        stack<Node*> s;
        s.push(root);
        while (!s.empty()) {
            Node *curr = s.top();
            res.push_back(curr->val);
            s.pop();
            for (auto node : curr->children) {
                s.push(node);
            }
        }
        reverse(res.begin(), res.end());
        return res;
    }

    // 递归
    vector<int> postorder(Node* root) {
        vector<int> res;
        dfs(root, res);
        return res;
    }

    void dfs(Node* node, vector<int>& res) {
        if (!node) return;
        for (auto curr : node->children) {
            dfs(curr, res);
        }
        res.push_back(node->val);
    }
};