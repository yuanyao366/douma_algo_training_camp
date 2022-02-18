"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""

class Solution:
    def __init__(self):
        self.visited = {}

    # 1. 递归解法
    def copyRandomList1(self, head: 'Node') -> 'Node':
        if not head:
            return head

        new_node = Node(head.val)
        self.visited[head] = new_node

        new_node.next = self.copyRandomList1(head.next)
        if head.random:
            new_node.random = self.visited[head.random]

        return new_node

    # 2. 迭代解法
    def copyRandomList2(self, head: 'Node') -> 'Node':
        if not head:
            return head

        old_node, new_node = head, Node(head.val)
        self.visited[old_node] = new_node
        new_head = new_node
        while old_node:
            new_node.next = self.get_clone_node(old_node.next)
            new_node.random = self.get_clone_node(old_node.random)
            new_node, old_node = new_node.next, old_node.next

        return new_head

    def get_clone_node(self, node: 'Node') -> 'Node':
        if not node:
            return None
        if node not in self.visited:
            self.visited[node] = Node(node.val)
        return self.visited[node]

    # 2. 用新旧节点交替的方式，模拟 map 的功能
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head:
            return head

        # 1. 新旧节点交替
        curr = head
        while curr:
            new_node = Node(curr.val)
            new_node.next = curr.next
            curr.next = new_node
            curr = new_node.next

        # 2. 设置正确的 random
        curr = head
        while curr:
            if curr.random:
                curr.next.random = curr.random.next
            else:
                curr.next.random = None
            curr = curr.next.next

        # 3. 分割新旧链表
        curr_old, curr_new, new_node = head, head.next, head.next
        while curr_old:
            curr_old.next = curr_old.next.next
            if curr_new.next:
                curr_new.next = curr_new.next.next
            else:
                curr_new.next = None
            curr_old, curr_new = curr_old.next, curr_new.next
        return new_node