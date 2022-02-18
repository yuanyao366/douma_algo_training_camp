func removeVowels(s string) string {
    res := ""
    for i := 0; i < len(s); i++ {
        if !isVowel(s[i]) {
            res += string(s[i])
        }
    }
    return res
}

func isVowel(c byte) bool {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
}