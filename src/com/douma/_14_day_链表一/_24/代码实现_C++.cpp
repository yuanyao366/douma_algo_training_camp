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
    ListNode* swapPairs(ListNode* head) {
        if (!head || !head->next) return head;

        ListNode* dummyNode = new ListNode(-1);
        dummyNode->next = head;

        ListNode *prev = dummyNode;
        ListNode *first = head;
        ListNode *second = head->next;
        while (second) {
            ListNode *next = second->next;
            prev->next = second;
            second->next = first;
            first->next = next;

            prev = first;
            first = next;
            if (!first) break;
            second = first->next;
        }
        return dummyNode->next;
    }
};