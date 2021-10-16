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
    ListNode* insertionSortList(ListNode* head) {
        if (!head || !head->next) return head;

        ListNode* dummyNode = new ListNode(-1);
        dummyNode->next = head;

        ListNode *prev = head;
        ListNode *curr = head->next;
        while (curr != nullptr) {
            if (curr->val >= prev->val) {
                prev = curr;
                curr = curr->next;
            } else {
                // 找到小于 curr.val 的最大的节点
                ListNode *p = dummyNode;
                // 说明：这里的 p.next 不可能为空
                // 因为 p 从头开始，最远可以到达的节点是 curr 的前一个节点
                // 所以 p.next 不可能为 nullptr，我这里加上 p.next 的判空，是我个人的习惯哟~
                while (p->next != nullptr && p->next->val < curr->val) {
                    p = p->next;
                }

                // 将 curr 插入到 p 和 p.next 之间
                prev->next = curr->next;
                curr->next = p->next;
                p->next = curr;

                curr = prev->next;
            }
        }
        return dummyNode->next;
    }
};