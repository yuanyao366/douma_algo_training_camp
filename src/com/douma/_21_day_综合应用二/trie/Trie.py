# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


class Node:
    def __init__(self):
        # 每个节点包含：
        # 1. 一个字符
        # 2. 若干个子节点
        # key 是字符， value 是这个字符对应的节点
        self.children = dict()
        # 标识，这个节点是否是一个单词最后一个字符
        self.isWord = False


class Trie:
    def __init__(self):
        self.root = Node()

    def add(self, word):
        curr = self.root
        for c in word:
            # 1. 先从子节点中查找是否包含当前字符 c
            if c not in curr.children:
                curr.children[c] = Node()
            curr = curr.children[c]
        curr.isWord = True

    def contains(self, word):
        curr = self.root
        for c in word:
            # 1. 先从子节点中查找是否包含当前字符 c
            if c not in curr.children:
                return False
            curr = curr.children[c]
        return curr.isWord


if __name__ == '__main__':
    trie = Trie()
    trie.add("big")
    trie.add("pat")
    trie.add("bigger")
    trie.add("dog")
    trie.add("door")
    print(trie.contains("dog"))  # True


