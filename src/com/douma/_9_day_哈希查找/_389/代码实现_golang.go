func findTheDifference1(s string, t string) byte {
    var countS = [26]int{}
    for _, c := range s {
        countS[c - 'a']++
    }
    for _, c := range t {
        countS[c - 'a']--
        if countS[c - 'a'] < 0 {
            return byte(c)
        }
    }
    return ' '
}

func findTheDifference2(s string, t string) byte {
    var as, at = rune(0), rune(0)
    for _, c := range s {
        as += c
    }
    for _, c := range t {
        at += c
    }
    return byte(at - as)
}

func findTheDifference(s string, t string) byte {
    var ret = rune(0)
    for _, c := range s {
        ret ^= c
    }
    for _, c := range t {
        ret ^= c
    }
    return byte(ret)
}