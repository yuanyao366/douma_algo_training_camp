def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
    dummy = curr = ListNode()
    carry = 0
    while l1 or l2:
        x = l1.val if l1 else 0
        y = l2.val if l2 else 0

        total = x + y + carry
        curr.next = ListNode(total % 10)
        curr = curr.next
        carry = total // 10

        if l1: l1 = l1.next
        if l2: l2 = l2.next
    if carry: curr.next = ListNode(carry)
    return dummy.next
