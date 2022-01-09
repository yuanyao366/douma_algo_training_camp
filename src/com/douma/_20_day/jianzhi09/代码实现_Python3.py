# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


class CQueue:

    def __init__(self):
        self.stack1 = []
        self.stack2 = []

    def appendTail(self, value: int) -> None:
        self.stack1.append(value)

    def deleteHead(self) -> int:
        if not len(self.stack2):
            while len(self.stack1):
                self.stack2.append(self.stack1.pop())
        if not len(self.stack2):
            return -1
        else:
            return self.stack2.pop()