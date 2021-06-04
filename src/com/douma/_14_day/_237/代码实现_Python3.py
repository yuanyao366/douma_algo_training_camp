# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteNode(self, node):
        """
        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """
        prev = node
        while node:
            next = node.next
            if next:
                node.val = next.val
            else:
                prev.next = None
            prev = node
            node = node.next