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
            # 这里 -1 的原因：每一段 curr 需要走的步数比这一段的节点数少 1 个
            # 比如链表：1 -> 2 -> 3 -> 4
            # 链表的长度为 4 ，也就是 4 个节点
            # 但是从第一个节点开始，只需要走 3 步就可以到达最后一个节点
            real_width = width + (1 if i < remainder else 0) - 1
            for j in range(real_width):
                if curr: curr = curr.next
            if curr:
                next = curr.next
                curr.next = None
                curr = next
            res[i] = head

        return res