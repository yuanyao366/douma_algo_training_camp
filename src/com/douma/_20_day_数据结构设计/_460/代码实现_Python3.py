class Node:
    def __init__(self, key = None, val = None, count = None):
        self.key = key
        self.val = val
        self.count = count
        self.next = None
        self.prev = None

class DoubleLinkedList:
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, node) -> Node:
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None
        return node

    # 将 node 拼接到表尾
    def append(self, node):
        node.prev = self.tail.prev
        self.tail.prev.next = node
        node.next = self.tail
        self.tail.prev = node

    def pop_first(self) -> Node:
        if self.head.next == self.tail:
            return None
        return self.remove(self.head.next)

    def is_empty(self) -> bool:
        return self.head.next == self.tail

class LFUCache:

    def __init__(self, capacity: int):
        self.key_to_node = {}
        self.used_count_to_keys = collections.defaultdict(DoubleLinkedList)
        self.capacity = capacity
        self.min_used_count = 0

    def get(self, key: int) -> int:
        if self.capacity == 0: return -1
        if key in self.key_to_node:
            node = self.key_to_node[key]

            # 维护这个 key 对应的 count
            used_count = node.count

            # 1. 从这个 key 目前对应的 count 的集合中删除掉这个 key
            self.used_count_to_keys[used_count].remove(node)

            # 2. 更新最小使用的次数
            # 如果当前的 usedCount 等于最小次数，
            # 并且当前的 usedCount 没有的 key，那么将最小次数加 1
            if used_count == self.minUsedCount and self.used_count_to_keys[used_count].is_empty():
                self.minUsedCount += 1

            # 3. 将 key 记录到 usedCount + 1 中的集合中
            node.count = used_count + 1
            self.used_count_to_keys[used_count + 1].append(node)

            return node.val
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if self.capacity == 0: return -1
        if key in self.key_to_node:
            node = self.key_to_node[key]
            node.val = value
            # 更新 key 对应的 value 值
            self.key_to_node[key] = node
            # 更新 key 对应的 count 值
            self.get(key)
            return
        if len(self.key_to_node) == self.capacity:
            # 删除最少使用的 key
            remove_node = self.used_count_to_keys[self.minUsedCount].pop_first()
            del self.key_to_node[remove_node.key]

        # 新增一个缓存中不存在的 key
        node = Node(key, value, 1)
        self.key_to_node[key] = node

        # 将 key 记录到 minUsedCount 中的集合中
        self.minUsedCount = 1
        self.used_count_to_keys[self.minUsedCount].append(node)


# Your LFUCache object will be instantiated and called as such:
# obj = LFUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)