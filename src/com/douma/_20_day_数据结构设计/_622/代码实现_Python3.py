class MyCircularQueue:

    def __init__(self, k: int):
        self.data = [0] * (k + 1)
        self.head = self.tail = 0

    def enQueue(self, value: int) -> bool:
        if self.isFull(): return False
        self.data[self.tail] = value
        self.tail = (self.tail + 1) % len(self.data)
        return True

    def deQueue(self) -> bool:
        if self.isEmpty(): return False
        self.head = (self.head + 1) % len(self.data)
        return True

    def Front(self) -> int:
        if self.isEmpty(): return -1
        return self.data[self.head]

    def Rear(self) -> int:
        if self.isEmpty(): return -1
        index = self.tail - 1 if self.tail > 0 else len(self.data) - 1
        return self.data[index % len(self.data) ]

    def isEmpty(self) -> bool:
        return self.head == self.tail

    def isFull(self) -> bool:
        return (self.tail + 1) % len(self.data) == self.head


# Your MyCircularQueue object will be instantiated and called as such:
# obj = MyCircularQueue(k)
# param_1 = obj.enQueue(value)
# param_2 = obj.deQueue()
# param_3 = obj.Front()
# param_4 = obj.Rear()
# param_5 = obj.isEmpty()
# param_6 = obj.isFull()