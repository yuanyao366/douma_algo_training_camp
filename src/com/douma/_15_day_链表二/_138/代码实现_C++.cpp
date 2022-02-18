/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/

class Solution {
private:
    unordered_map<Node*, Node*> visited;

public:
    // 1. 递归解法
    Node* copyRandomList1(Node* head) {
        if (!head) return head;

        Node* newNode = new Node(head->val);
        visited[head] = newNode;

        newNode->next = copyRandomList1(head->next);
        newNode->random = visited[head->random];

        return newNode;
    }

    // 2. 迭代解法
    Node* copyRandomList2(Node* head) {
        if (!head) return nullptr;

        Node *oldNode = head;
        Node *newNode = new Node(head->val);
        visited[oldNode] = newNode;

        while (oldNode) {
            newNode->next = getCloneNode(oldNode->next);
            newNode->random = getCloneNode(oldNode->random);
            oldNode = oldNode->next;
            newNode = newNode->next;
        }

        return visited[head];
    }

    Node* getCloneNode(Node* node) {
        if (!node) return nullptr;
        if (visited.count(node)) {
            return visited[node];
        } else {
            Node* newNode = new Node(node->val);
            visited[node] = newNode;
            return newNode;
        }
    }

    // 3. 用新旧节点交替的方式，模拟 map 的功能
    Node* copyRandomList(Node* head) {
        if (!head) return nullptr;

        Node *curr = head;
        // 创建新节点，并且放在旧节点的后面
        // 假设原先链表是这样：A->B->C，那么创建新节点后，链表变成：A->A'->B->B'->C->C'
        // 其中 A' 是 A 的克隆节点
        while (curr) {
            Node *newNode = new Node(curr->val);
            newNode->next = curr->next;
            curr->next = newNode;
            curr = newNode->next;
        }



        // 设置正确的 random
        curr = head;
        while (curr) {
            if (curr->random) {
                curr->next->random = curr->random->next;
            } else {
                curr->next->random = nullptr;
            }
            curr = curr->next->next;
        }



        // 将 A->A'->B->B'->C->C' 切割成：A->B->C 和 A'->B'->C'
        Node *currOldNode = head;
        Node *currNewNode = head->next;
        Node *newHead = head->next;

        while (currOldNode) {
            currOldNode->next = currOldNode->next->next;
            if (currNewNode->next) {
                currNewNode->next = currNewNode->next->next;
            }  else {
                currNewNode->next = nullptr;
            }
            currOldNode = currOldNode->next;
            currNewNode = currNewNode->next;
        }

        return newHead;
    }
};