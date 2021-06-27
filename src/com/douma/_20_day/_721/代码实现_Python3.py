class UnionFind:
    def __init__(self, capacity):
        self.parent = [0] * capacity
        self.rank = [0] * capacity
        for i in range(capacity):
            self.parent[i] = i
            self.rank[i] = 1

    def find(self, p):
        if p < 0 or p >= len(self.parent):
            raise Exception("p 超出了范围")
        while p != self.parent[p]:
            self.parent[p] = self.parent[self.parent[p]]
            p = self.parent[p]
        return p

    def is_connected(self, p, q):
        return self.find(p) == self.find(q)

    def union_element(self, p, q):
        p_root, q_root = self.find(p), self.find(q)
        if p_root == q_root:
            return
        if self.rank[p_root] < self.rank[q_root]:
            self.parent[p_root] = q_root
        elif self.rank[p_root] > self.rank[q_root]:
            self.parent[q_root] = p_root
        else:
            self.parent[q_root] = p_root
            self.rank[p_root] += 1

class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        email_to_index, email_to_name, email_counts = {}, {}, 0
        for account in accounts:
            name, size = account[0], len(account)
            for i in range(1, size):
                if account[i] not in email_to_index:
                    email_to_index[account[i]] = email_counts
                    email_counts += 1
                    email_to_name[account[i]] = name

        uf = UnionFind(email_counts)
        for account in accounts:
            first_email, size = account[1], len(account)
            first_index = email_to_index[first_email]
            for i in range(2, size):
                next_index = email_to_index[account[i]]
                uf.union_element(first_index, next_index)

        index_to_emails = collections.defaultdict(list)
        for email, index in email_to_index.items():
            rootIndex = uf.find(index)
            index_to_emails[rootIndex].append(email)

        merged = list()
        for emails in index_to_emails.values():
            merged.append([email_to_name[emails[0]]] + sorted(emails))
        return merged



