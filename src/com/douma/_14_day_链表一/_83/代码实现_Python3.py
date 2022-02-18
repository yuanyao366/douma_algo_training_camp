# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        if head == None or head.next == None:
            return head

        prev, curr = head, head.next
        while curr:
            if curr.val == prev.val:
                prev.next = curr.next
                curr.next = None
            else:
                prev = curr
            curr = prev.next
        return head