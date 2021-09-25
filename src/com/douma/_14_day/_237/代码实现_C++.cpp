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
    // 时间复杂度：O(n)
    void deleteNode1(ListNode* node) {
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

    // 代码优化
    // 时间复杂度：O(1)
    void deleteNode(ListNode* node) {
        // 实际上，我们只需要将下一个节点的值覆盖掉要删除的节点的值就可以
        node->val = node->next->val;
        // 然后将下一个节点从链表中断开
        ListNode* next = node->next;
        node->next = next->next;
        next->next = nullptr;
    }
};