# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def middleNode(self, head: ListNode) -> ListNode:
        if not head and not head.next:
            return head
        slow = fast = head
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next
        return slow