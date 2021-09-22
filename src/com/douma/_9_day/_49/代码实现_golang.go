func groupAnagrams(strs []string) [][]string {
    var m = make(map[string][]string)
    for _, s := range strs {
        var cnt = make([]int, 26)
        for _, c := range s {
            cnt[c - 'a']++
        }

        var key = ""
        for _, c := range cnt {
            key += strconv.Itoa(c) + ","
        }

        _, ok := m[key]
        if !ok {
            m[key] = make([]string, 0)
        }
        m[key] = append(m[key], s)
    }

    var res = make([][] string, 0)
    for _, value := range m {
        res = append(res, value)
    }
    return res
}