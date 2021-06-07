/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
// 反转解法 ，空间复杂度 O(1)
var addTwoNumbers1 = function(l1, l2) {
    if (!l1) return l2
    if (!l2) return l1

    l1 = reverseList(l1)
    l2 = reverseList(l2)

    const retNode = addTwoNumbersHelp(l1, l2)

    return reverseList(retNode)
};

var addTwoNumbersHelp = function(l1, l2) {
    let dummy = new ListNode()
    let curr = dummy
    let carry = 0
    while (l1 || l2) {
        const x = l1 ? l1.val : 0
        const y = l2 ? l2.val : 0

        const sum = x + y + carry
        curr.next = new ListNode(sum % 10)
        curr = curr.next
        carry = Math.floor(sum / 10)

        if (l1) l1 = l1.next
        if (l2) l2 = l2.next
    }
    if (carry) curr.next = new ListNode(carry)
    return dummy.next
};

var reverseList = function(head) {
    let prev = null, curr = head
    while (curr) {
        const next = curr.next
        curr.next = prev
        prev = curr
        curr = next
    }
    return prev
};

// 使用栈
var addTwoNumbers = function(l1, l2) {
    if (!l1) return l2
    if (!l2) return l1

    const stack1 = [], stack2 = []
    while (l1) {
        stack1.push(l1)
        l1 = l1.next
    }
    while (l2) {
        stack2.push(l2)
        l2 = l2.next
    }

    let ans = null, carry = 0
    while (stack1.length > 0 || stack2.length > 0) {
        const x = stack1.length == 0 ? 0 : stack1.pop().val
        const y = stack2.length == 0 ? 0 : stack2.pop().val

        const sum = x + y + carry

        const curr = new ListNode(sum % 10)
        curr.next = ans
        ans = curr

        carry = Math.floor(sum / 10)
    }

    if (carry) {
        const curr = new ListNode(carry)
        curr.next = ans
        ans = curr
    }

    return ans
};