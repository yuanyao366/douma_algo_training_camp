# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


"""
ACM 模式下，如果输入的是链表，
需要自己根据输入数据，在内存中构建链表
比如有道题目输入是链表。然后会给出下面一行数据：
23 22 1 5 6 3
"""

# 1. 首先需要定义一个节点类
class Node:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next


# 2. 然后构建一个单链表
data = str(input()).split(" ")
dummyNode = Node(-1)
curr = dummyNode
for i in range(len(data)):
    curr.next = Node(int(data[i]))
    curr = curr.next

# 对 dummyNode 操作

# 最后打印的时候，是要遍历操作后的链表
