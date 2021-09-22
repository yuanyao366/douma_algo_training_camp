func isIsomorphic(s string, t string) bool {
    var s2tMap, t2sMap = make(map[byte]byte), make(map[byte]byte)
    for i := range s {
        sc, ok1 := s2tMap[s[i]]
        tc, ok2 := t2sMap[t[i]]
        if (ok1 && sc != t[i]) || (ok2 && tc != s[i]) {
            return false
        }
        s2tMap[s[i]] = t[i]
        t2sMap[t[i]] = s[i]
    }
    return true
}