/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    void deleteNode(ListNode* node) {
        ListNode *prev = nullptr;
        while (node != nullptr) {
            ListNode *next = node->next;
            if (next != nullptr) {
                node->val = next->val;
            } else {
                prev->next = nullptr;
            }
            prev = node;
            node = node->next;
        }
    }
};