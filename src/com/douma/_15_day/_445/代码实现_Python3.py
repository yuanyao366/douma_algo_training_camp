# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    # 反转解法，空间复杂度 O(1)
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        if not l1: return l2
        if not l2: return l1

        l1, l2 = self.reverseList(l1), self.reverseList(l2)
        ret_node = self.addTwoNumbersHelp(l1, l2)
        return self.reverseList(ret_node)

    def addTwoNumbersHelp(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = curr = ListNode()
        carry = 0
        while l1 or l2:
            x = l1.val if l1 else 0
            y = l2.val if l2 else 0

            sum = x + y + carry
            curr.next = ListNode(sum % 10)
            curr = curr.next
            carry = sum // 10

            if l1: l1 = l1.next
            if l2: l2 = l2.next
        if carry: curr.next = ListNode(carry)
        return dummy.next

    def reverseList(self, head: ListNode) -> ListNode:
        prev, curr = None, head
        while curr is not None:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        return prev

    # 使用栈
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        if not l1: return l2
        if not l2: return l1

        stack1, stack2 = [], []
        while l1:
            stack1.append(l1)
            l1 = l1.next
        while l2:
            stack2.append(l2)
            l2 = l2.next

        ans, carry = None, 0
        while len(stack1) > 0 or len(stack2) > 0:
            x = 0 if len(stack1) == 0 else stack1.pop().val
            y = 0 if len(stack2) == 0 else stack2.pop().val

            sum = x + y + carry

            curr = ListNode(sum % 10)
            curr.next = ans
            ans = curr

            carry = sum // 10

        if carry:
            curr = ListNode(carry)
            curr.next = ans
            ans = curr

        return ans