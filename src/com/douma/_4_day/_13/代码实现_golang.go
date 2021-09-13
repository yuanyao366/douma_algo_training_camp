

func romanToInt(s string) int {
    symbolValues := map[byte]int{'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
    sum, pre := 0, symbolValues[s[0]]
    for i := 1; i < len(s); i++ {
        curr := symbolValues[s[i]]
        if pre < curr {
            sum -= pre
        } else {
            sum += pre
        }
        pre = curr
    }
    sum += pre
    return sum
}
