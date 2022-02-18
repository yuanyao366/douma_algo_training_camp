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
    // 反转解法，空间复杂度 O(1)
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        if (!l1) return l2;
        if (!l2) return l1;

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode *retNode = addTwoNumbersHelp(l1, l2);
        return reverseList(retNode);
    }

    ListNode* addTwoNumbersHelp(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode();
        ListNode* curr = dummy;
        int carry = 0;
        while (l1 || l2) {
            int x = l1 ? l1->val : 0;
            int y = l2 ? l2->val : 0;

            int sum = x + y + carry;
            curr->next = new ListNode(sum % 10);
            curr = curr->next;
            carry = sum / 10;

            if (l1) l1 = l1->next;
            if (l2) l2 = l2->next;
        }
        if (carry != 0) curr->next = new ListNode(carry);
        return dummy->next;
    }

    ListNode* reverseList(ListNode* head) {
        ListNode* prev = nullptr;
        ListNode* curr = head;
        while (curr) {
            ListNode* next = curr->next;
            curr->next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // 使用栈
    ListNode* addTwoNumbers2(ListNode* l1, ListNode* l2) {
        if (!l1) return l2;
        if (!l2) return l1;

        stack<ListNode*> stack1;
        while (l1) {
            stack1.push(l1);
            l1 = l1->next;
        }

        stack<ListNode*> stack2;
        while (l2) {
            stack2.push(l2);
            l2 = l2->next;
        }

        ListNode *ans = nullptr;
        int carry = 0;
        while (!stack1.empty() || !stack2.empty()) {
            int x = stack1.empty() ? 0 : stack1.top()->val;
            int y = stack2.empty() ? 0 : stack2.top()->val;
            if (!stack1.empty()) stack1.pop();
            if (!stack2.empty()) stack2.pop();

            int sum = x + y + carry;
            ListNode *curr = new ListNode(sum % 10);
            curr->next = ans;
            ans = curr;

            carry = sum / 10;
        }

        if (carry) {
            ListNode *curr = new ListNode(carry);
            curr->next = ans;
            ans = curr;
        }

        return ans;
    }
};