class UnionFind {
private:
    vector<int> parent;
    vector<int> rank;

public:
    UnionFind(int capacity) {
        parent = vector<int>(capacity);
        rank = vector<int>(capacity);

        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    int find(int p) {
        if (p < 0 || p >= parent.size()) {
            throw "p 超出了范围";
        }
        while (p != parent[p]) {
            // 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    bool isConnected(int p, int q) {
        return find(p) == find(q);
    }

    void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

};


class Solution {
public:
    /*
        accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                    ["John", "johnnybravo@mail.com"],
                    ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                    ["Mary", "mary@mail.com"]]
    */
    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        /*
            emailToIndex = {"johnsmith@mail.com" -> 0, "john00@mail.com" -> 1, "johnnybravo@mail.com" -> 2
                            "john_newyork@mail.com" -> 3, "mary@mail.com" -> 4}
        */
        unordered_map<string, int> emailToIndex;
        /*
            emailToName = {"johnsmith@mail.com" -> "John", "john00@mail.com" -> "John", "johnnybravo@mail.com" -> "John"
                            "john_newyork@mail.com" -> "John", "mary@mail.com" -> "Mary"}
        */
        unordered_map<string, string> emailToName;
        int emailsCount = 0;
        for (auto& account : accounts) {
            string& name = account[0];
            int size = account.size();
            for (int i = 1; i < size; i++) {
                if (!emailToIndex.count(account[i])) {
                    emailToIndex[account[i]] = emailsCount++;
                    emailToName[account[i]] = name;
                }
            }
        }


        /*
            将同一个人的邮箱对应的 index 进行合并
            [0, 1, 3]、[2]、[4]
        */
        UnionFind uf(emailsCount);
        for (auto& account : accounts) {
            string& firstEmail = account[1];
            int firstIndex = emailToIndex[firstEmail];
            int size = account.size();
            for (int i = 2; i < size; i++) {
                string& nextEmail = account[i];
                int nextIndex = emailToIndex[nextEmail];
                uf.unionElement(firstIndex, nextIndex);
            }
        }

        /*
            indexToEmails = {0 -> ["johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                             2 - > ["johnnybravo@mail.com"], 4 -> ["mary@mail.com"]]}
        */
        unordered_map<int, vector<string>> indexToEmails;
        for (auto& [email, index] : emailToIndex) {
            int parent = uf.find(index);
            vector<string>& account = indexToEmails[parent];
            account.emplace_back(email);
            indexToEmails[parent] = account;
        }

        /*
            merged = {"John" -> ["johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                             "John" - > ["johnnybravo@mail.com"], "Mary" -> ["mary@mail.com"]]}
        */
        vector<vector<string>> merged;
        for (auto& [_, emails] : indexToEmails) {
            sort(emails.begin(), emails.end());
            string& name = emailToName[emails[0]];
            vector<string> account;
            account.emplace_back(name);
            for (auto& email : emails) {
                account.emplace_back(email);
            }
            merged.emplace_back(account);
        }

        return merged;
    }
};