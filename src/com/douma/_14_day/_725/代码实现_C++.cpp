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