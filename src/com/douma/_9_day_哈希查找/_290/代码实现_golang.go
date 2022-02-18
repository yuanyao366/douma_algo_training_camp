func wordPattern(pattern string, s string) bool {
    var words = strings.Split(s, " ")
    if len(pattern) != len(words) {
        return false
    }
    var char2Word, word2Char = make(map[byte]string), make(map[string]byte)
    for i := range pattern {
        word, ok1 := char2Word[pattern[i]]
        c, ok2 := word2Char[words[i]]
        if (ok1 && word != words[i]) || (ok2 && c != pattern[i]) {
            return false
        }
        char2Word[pattern[i]] = words[i]
        word2Char[words[i]] = pattern[i]
    }
    return true
}