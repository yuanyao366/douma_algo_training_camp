class Node:
    def __init__(self):
        self.key = self.val = 0
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}
        self.capacity = capacity
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head


    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        node = self.cache[key]
        self.move_node_to_head(node)
        return node.val

    def move_node_to_head(self, node) -> None:
        self.remove_node(node)
        self.add_node_to_head(node)

    def remove_node(self, node) -> None:
        prev_node, next_node = node.prev, node.next
        prev_node.next = next_node
        next_node.prev = prev_node
        node.prev, node.next = None, None

    def add_node_to_head(self, node) -> None:
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node
        node.prev = self.head

    def put(self, key: int, value: int) -> None:
        if key not in self.cache:
            if len(self.cache) == self.capacity:
                del_node = self.remove_tail_node()
                self.cache.pop(del_node.key)
            node = Node()
            node.key, node.val = key, value
            self.cache[key] = node
            self.add_node_to_head(node)
        else:
            node = self.cache[key]
            node.val = value
            self.move_node_to_head(node)

    def remove_tail_node(self) -> Node:
        del_node = self.tail.prev
        self.remove_node(del_node)
        return del_node



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)