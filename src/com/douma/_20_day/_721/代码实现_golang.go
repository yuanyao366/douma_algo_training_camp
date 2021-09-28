/*
       accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                   ["John", "johnnybravo@mail.com"],
                   ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                   ["Mary", "mary@mail.com"]]
   */
func accountsMerge(accounts [][]string) [][]string {
    /*
        emailToIndex = {"johnsmith@mail.com" -> 0, "john00@mail.com" -> 1, "johnnybravo@mail.com" -> 2
                        "john_newyork@mail.com" -> 3, "mary@mail.com" -> 4}
    */
    var emailToIndex = make(map[string]int)
    /*
        emailToName = {"johnsmith@mail.com" -> "John", "john00@mail.com" -> "John", "johnnybravo@mail.com" -> "John"
                        "john_newyork@mail.com" -> "John", "mary@mail.com" -> "Mary"}
    */
    var emailToName = make(map[string]string)
    var emailsCount = 0
    for _, account := range accounts {
        var name, size = account[0], len(account)
        for i := 1; i < size; i++ {
            var email = account[i]
            if _, has := emailToIndex[email]; !has {
                emailToIndex[email] = emailsCount
                emailsCount++
                emailToName[email] = name
            }
        }
    }

    /*
        将同一个人的邮箱对应的 index 进行合并
        [0, 1, 3]、[2]、[4]
    */
    var uf = newUnionFind(emailsCount)
    for _, account := range accounts {
        var firstEmail = account[1]
        var firstIndex = emailToIndex[firstEmail]
        var size = len(account)
        for i := 2; i < size; i++ {
            var nextEmail = account[i]
            var nextIndex = emailToIndex[nextEmail]
            uf.unionElement(firstIndex, nextIndex)
        }
    }

    /*
        indexToEmails = {1 -> ["johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                            2 - > ["johnnybravo@mail.com"], 4 -> ["mary@mail.com"]]}
    */
    var indexToEmails = make(map[int][]string)
    for email := range emailToIndex {
        var index = emailToIndex[email]
        var indexRoot = uf.find(index)
        var account = make([]string, 0)
        if indexToEmails[indexRoot] != nil {
            account = indexToEmails[indexRoot]
        }
        account = append(account, email)
        // bug 修复：这里的 key 应该是 indexRoot
        indexToEmails[indexRoot] = account
    }

    /*
        merged = [["John", "johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                    ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    */
    var merged = make([][]string, 0)
    for _, emails := range indexToEmails {
        sort.Sort(sort.StringSlice(emails))
        var name = emailToName[emails[0]]
        var account = []string{name}
        account = append(account, emails...)
        merged = append(merged, account)
    }

    return merged
}


type UnionFind struct {
    parent []int
    rank []int
}

func newUnionFind(capacity int) UnionFind {
    var uf = UnionFind{
        parent:make([]int, capacity),
        rank:make([]int, capacity),
    }

    for i := 0; i < capacity; i++ {
        uf.parent[i] = i
        uf.rank[i] = 1
    }
    return uf
}

func (this *UnionFind) find(p int) int {
    if p < 0 || p >= len(this.parent) {
        panic("p 超出了范围")
    }

    for p != this.parent[p] {
        // 路径压缩
        this.parent[p] = this.parent[this.parent[p]]
        p = this.parent[p]
    }

    return p
}

func (this *UnionFind) isConnected(p int, q int) bool {
    return this.find(p) == this.find(q)
}

func (this *UnionFind) unionElement(p int, q int) {
    var pRoot, qRoot = this.find(p), this.find(q)
    if pRoot == qRoot {
        return
    }

    if this.rank[pRoot] < this.rank[qRoot] {
        this.parent[pRoot] = qRoot
    } else if this.rank[pRoot] > this.rank[qRoot] {
        this.parent[qRoot] = pRoot
    } else {
        this.parent[qRoot] = pRoot
        this.rank[pRoot] += 1
    }
}