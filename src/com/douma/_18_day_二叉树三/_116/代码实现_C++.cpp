/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};
*/

class Solution {
public:
    // 1. BFS 层序遍历
    Node* connect1(Node* root) {
        if (root == NULL) return NULL;
        queue<Node*> q;
        q.push(root);
        while (!q.empty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node *curr = q.front();
                q.pop();
                if (i != size - 1) curr->next = q.front();
                if (curr->left) q.push(curr->left);
                if (curr->right) q.push(curr->right);
            }
        }
        return root;
    }

    // 2. 双指针
    Node* connect2(Node* root) {
        if (root == NULL) return NULL;
        Node *left = root;
        while (left->left) {
            Node *curr = left;
            while (curr) {
                curr->left->next = curr->right;
                if (curr->next) {
                    curr->right->next = curr->next->left;
                }
                curr = curr->next;
            }
            left = left->left;
        }
        return root;
    }

    // 3. DFS
    Node* connect(Node* root) {
        if (root == NULL) return NULL;
        dfs(root);
        return root;
    }

    void dfs(Node* node) {
        if (node == NULL) return;

        Node *left = node->left;
        Node *right = node->right;
        while (left) {
            left->next = right;
            left = left->right;
            right = right->left;
        }
        dfs(node->left);
        dfs(node->right);
    }
};