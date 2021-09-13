func reverseVowels(s string) string {
    t := []byte(s)
    left, right := 0, len(s) - 1
    for left < right {
        for left < right && !isVowel(t[left]) {
            left++
        }
        for left < right && !isVowel(t[right]) {
            right--
        }

        t[left], t[right] = t[right], t[left]
        left++
        right--
    }
    return string(t)
}

func isVowel(c byte) bool {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
}