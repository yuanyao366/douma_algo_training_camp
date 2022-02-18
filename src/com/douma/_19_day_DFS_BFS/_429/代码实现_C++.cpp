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
    // 迭代 BFS
    vector<vector<int>> levelOrder1(Node* root) {
        if (!root) return {};

        vector<vector<int>> res;
        queue<Node*> q;
        q.push(root);
        while (!q.empty()) {
            int size = q.size();
            vector<int> levelNodes;
            for (int i = 0; i < size; i++) {
                Node *curr = q.front();
                q.pop();
                levelNodes.push_back(curr->val);
                for (auto node : curr->children) {
                    q.push(node);
                }
            }
            res.push_back(levelNodes);
        }

        return res;
    }

    // 递归 DFS
    vector<vector<int>> levelOrder(Node* root) {
        vector<vector<int>> res;
        dfs(root, 0, res);
        return res;
    }

    void dfs(Node* node, int currLevel, vector<vector<int>>& res) {
        if (!node) return;

        if (res.size() == currLevel) {
            vector<int> levelNodes = {node->val};
            res.push_back(levelNodes);
        } else {
            res[currLevel].push_back(node->val);
        }

        for (auto curr : node->children) {
            dfs(curr, currLevel + 1, res);
        }
    }
};