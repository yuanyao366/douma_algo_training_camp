# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        if not head or not head.next:
            return head

        dummy_node = ListNode(-1)
        dummy_node.next = head

        prev, first, second = dummy_node, head, head.next
        while second:
            next = second.next
            prev.next = second
            second.next = first
            first.next = next

            prev, first = first, next
            if not first: break
            second = first.next
        return dummy_node.next