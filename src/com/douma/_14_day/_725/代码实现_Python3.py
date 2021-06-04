# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def splitListToParts(self, root: ListNode, k: int) -> List[ListNode]:
        length, curr = 0, root
        while curr:
            length, curr = length + 1, curr.next

        width, remainder = divmod(length, k)

        res = [ListNode(-1)] * k
        curr = root
        for i in range(k):
            head = curr
            real_width = width + (1 if i < remainder else 0) - 1
            for j in range(real_width):
                if curr: curr = curr.next
            if curr:
                next = curr.next
                curr.next = None
                curr = next
            res[i] = head

        return res