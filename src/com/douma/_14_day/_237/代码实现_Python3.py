# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # 时间复杂度：O(n)
    def deleteNode1(self, node):
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

    # 代码优化
    # 时间复杂度：O(1)
    def deleteNode(self, node):
        """
        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """
        #  实际上，我们只需要将下一个节点的值覆盖掉要删除的节点的值就可以
        node.val = node.next.val
        # 然后将下一个节点从链表中断开
        n = node.next
        node.next = n.next
        n.next = None