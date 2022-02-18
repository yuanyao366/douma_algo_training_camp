class SentenceInfo:
    def __init__(self, content, time):
        self.content = content
        self.time = time

class TrieNode:
    def __init__(self):
        self.children = {}
        self.times = 0

class CompareKey(SentenceInfo):
    def __lt__(x, y):
        return x.content < y.content if x.time == y.time else x.time > y.time

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.currSentence = ""
        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])

    def insert(self, s: str, time: int) -> None:
        curr = self.root
        for c in s:
            if c not in curr.children:
                curr.children[c] = TrieNode()
            curr = curr.children[c]
        curr.times += time

    def lookup(self, s: str) -> List[SentenceInfo]:
        res, curr = [], self.root
        for c in s:
            if c not in curr.children:
                return res
            curr = curr.children[c]

        def dfs(curr, s):
            if curr.times > 0:
                res.append(SentenceInfo(s, curr.times))
            for [c, node] in curr.children.items():
                dfs(node, s + c)

        dfs(curr, s)
        return res

    def input(self, c: str) -> List[str]:
        res = []
        if c == '#':
            self.insert(self.currSentence, 1)
            self.currSentence = ""
        else:
            self.currSentence += c
            sentences = self.lookup(self.currSentence)
            sentences.sort(key = lambda x: (-x.time, x.content))
            for i in range(min(3, len(sentences))):
                res.append(sentences[i].content)
        return res



# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)