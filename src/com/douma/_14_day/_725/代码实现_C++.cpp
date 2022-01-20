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
    vector<ListNode*> splitListToParts(ListNode* root, int k) {
        int len = 0;
        ListNode *curr = root;
        while (curr != nullptr) {
            len++;
            curr = curr->next;
        }

        int width = len / k;
        int remainder = len % k; // 余数

        vector<ListNode*> res(k);
        curr = root;
        for (int i = 0; i < k; i++) {
            ListNode *head = curr;
            // 这里 -1 的原因：每一段 curr 需要走的步数比这一段的节点数少 1 个
            // 比如链表：1 -> 2 -> 3 -> 4
            // 链表的长度为 4 ，也就是 4 个节点
            // 但是从第一个节点开始，只需要走 3 步就可以到达最后一个节点
            int realWidth = width + (i < remainder ? 1 : 0) - 1;
            for (int j = 0; j < realWidth; j++) {
                if (curr != nullptr) curr = curr->next;
            }
            if (curr != nullptr) {
                ListNode *next = curr->next;
                curr->next = nullptr;
                curr = next;
            }
            res[i] = head;
        }
        return res;
    }
};