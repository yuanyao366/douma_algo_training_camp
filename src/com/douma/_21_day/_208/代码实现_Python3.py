class Node:
    def __init__(self):
        self.children = [None] * 26
        self.is_word = False


class Trie:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = Node()


    def insert(self, word: str) -> None:
        """
        Inserts a word into the trie.
        """
        curr = self.root
        for c in word:
            if not curr.children[ord(c) - ord('a')]:
                curr.children[ord(c) - ord('a')] = Node()
            curr = curr.children[ord(c) - ord('a')]
        curr.is_word = True


    def search(self, word: str) -> bool:
        """
        Returns if the word is in the trie.
        """
        curr = self.root
        for c in word:
            if not curr.children[ord(c) - ord('a')]:
                return False
            curr = curr.children[ord(c) - ord('a')]
        return curr.is_word


    def startsWith(self, prefix: str) -> bool:
        """
        Returns if there is any word in the trie that starts with the given prefix.
        """
        curr = self.root
        for c in prefix:
            if not curr.children[ord(c) - ord('a')]:
                return False
            curr = curr.children[ord(c) - ord('a')]
        return True



# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)