public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode();
        ListNode* curr = dummy;
        int carry = 0;
        while (l1 || l2) {
            int x = l1 ? l1->val : 0;
            int y = l2 ? l2->val : 0;

            int total = x + y + carry;
            curr->next = new ListNode(total % 10);
            curr = curr->next;
            carry = total / 10;

            if (l1) l1 = l1->next;
            if (l2) l2 = l2->next;
        }
        if (carry != 0) curr->next = new ListNode(carry);
        return dummy->next;
    }