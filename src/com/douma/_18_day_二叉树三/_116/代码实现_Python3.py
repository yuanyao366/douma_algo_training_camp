"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""

class Solution:
    # 1. BFS 层序遍历
    def connect1(self, root: 'Node') -> 'Node':
        if not root: return None
        queue = deque()
        queue.append(root)
        while len(queue):
            size = len(queue)
            for i in range(size):
                curr = queue.popleft()
                if i != size - 1:
                    curr.next = queue[0]
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
        return root

    # 2. 双指针
    def connect2(self, root: 'Node') -> 'Node':
        if not root: return None
        left = root
        while left.left:
            curr = left
            while curr:
                curr.left.next = curr.right
                if curr.next: curr.right.next = curr.next.left
                curr = curr.next
            left = left.left
        return root

    # 3. DFS
    def connect(self, root: 'Node') -> 'Node':
        if not root: return None

        def dfs(node: 'Node') -> None:
            if not node: return
            left, right = node.left, node.right
            while left:
                left.next = right
                left, right = left.right, right.left
            dfs(node.left)
            dfs(node.right)

        dfs(root)
        return root