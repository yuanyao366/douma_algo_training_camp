var addTwoNumbers = function(l1, l2) {
    let dummy = new ListNode()
    let curr = dummy
    let carry = 0
    while (l1 || l2) {
        const x = l1 ? l1.val : 0
        const y = l2 ? l2.val : 0

        const total = x + y + carry
        curr.next = new ListNode(total % 10)
        curr = curr.next
        carry = Math.floor(total / 10)

        if (l1) l1 = l1.next
        if (l2) l2 = l2.next
    }
    if (carry) curr.next = new ListNode(carry)
    return dummy.next
};