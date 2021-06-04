/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* removeElements(ListNode* head, int val) {
        ListNode* dummyNode = new ListNode(-1);
        dummyNode->next = head;

        ListNode *curr = head;
        ListNode *prev = dummyNode;
        while (curr != nullptr) {
            if (curr->val == val) {
                prev->next = curr->next;
                curr->next = nullptr;
            } else {
                prev = curr;
            }
            curr = prev->next;
        }

        return dummyNode->next;
    }
};