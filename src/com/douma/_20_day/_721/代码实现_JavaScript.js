/**
 * @param {string[][]} accounts
 * @return {string[][]}
 */

class UnionFind {
    constructor(capacity) {
        this.parent = new Array(capacity).fill(0)
        this.rank = new Array(capacity).fill(0)
        for (let i = 0; i < capacity; i++) {
            this.parent[i] = i
            this.rank[i] = 1
        }
    }

    find(p) {
        if (p < 0 && p >= this.parent.length) {
            throw new Error("p 超出了范围");
        }
        while (p != this.parent[p]) {
            this.parent[p] = this.parent[this.parent[p]]
            p = this.parent[p]
        }
        return p
    }

    isConnected(p, q) {
        return this.find(p) == this.find(q)
    }

    unionElement(p, q) {
        const pRoot = this.find(p)
        const qRoot = this.find(q)
        if (pRoot == qRoot) return

        if (this.rank[pRoot] < this.rank[qRoot]) {
            this.parent[pRoot] = qRoot
        } else if (this.rank[pRoot] > this.rank[qRoot]) {
            this.parent[qRoot] = pRoot
        } else {
            this.parent[qRoot] = pRoot
            this.rank[pRoot] += 1
        }
    }
}

/*
    accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                ["John", "johnnybravo@mail.com"],
                ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                ["Mary", "mary@mail.com"]]
*/
var accountsMerge = function(accounts) {
    /*
        emailToIndex = {"johnsmith@mail.com" -> 0, "john00@mail.com" -> 1, "johnnybravo@mail.com" -> 2
                        "john_newyork@mail.com" -> 3, "mary@mail.com" -> 4}
    */
    const emailToIndex = new Map()
    /*
        emailToName = {"johnsmith@mail.com" -> "John", "john00@mail.com" -> "John", "johnnybravo@mail.com" -> "John"
                        "john_newyork@mail.com" -> "John", "mary@mail.com" -> "Mary"}
    */
    const emailToName = new Map()
    let emailsCount = 0
    for (const account of accounts) {
        const name = account[0]
        const size = account.length
        for (let i = 1; i < size; i++) {
            const email = account[i]
            if (!emailToIndex.has(email)) {
                emailToIndex.set(email, emailsCount++);
                emailToName.set(email, name);
            }
        }
    }

    /*
        将同一个人的邮箱对应的 index 进行合并
        [0, 1, 3]、[2]、[4]
    */
    const uf = new UnionFind(emailsCount);
    for (const account of accounts) {
        const firstEmail = account[1];
        const firstIndex = emailToIndex.get(firstEmail);
        const size = account.length;
        for (let i = 2; i < size; i++) {
            const nextEmail = account[i];
            const nextIndex = emailToIndex.get(nextEmail);
            uf.unionElement(firstIndex, nextIndex);
        }
    }

    /*
        indexToEmails = {1 -> ["johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                            2 - > ["johnnybravo@mail.com"], 4 -> ["mary@mail.com"]]}
    */
    const indexToEmails = new Map();
    for (const email of emailToIndex.keys()) {
        const index = emailToIndex.get(email);
        const indexRoot = uf.find(index);
        if (!indexToEmails.has(indexRoot)) {
            indexToEmails.set(indexRoot, [])
        }
        indexToEmails.get(indexRoot).push(email);
    }

    /*
        merged = [["John", "johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                    ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    */
    const merged = [];
    for (const emails of indexToEmails.values()) {
       emails.sort()
        const name = emailToName.get(emails[0]);
        const account = [];
        account.push(name);
        account.push(...emails);
        merged.push(account);
    }

    return merged;
};