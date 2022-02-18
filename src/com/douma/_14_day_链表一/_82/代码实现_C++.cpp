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
    ListNode* deleteDuplicates(ListNode* head) {
        if (head == nullptr || head->next == nullptr) return head;

        ListNode* dummyNode = new ListNode(-1);
        dummyNode->next = head;

        ListNode *prev = dummyNode;
        ListNode *curr = head;
        while (curr != nullptr) {
            if (curr->next != nullptr && curr->val == curr->next->val) {
                do {
                    curr = curr->next;
                } while (curr->next != nullptr && curr->val == curr->next->val);
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