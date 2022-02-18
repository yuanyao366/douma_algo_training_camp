func lemonadeChange(bills []int) bool {
    five, ten := 0, 0
    for _, bill := range bills {
        if bill == 5 {
            five++
        } else if bill == 10 {
            if five == 0 {
                return false
            }
            five--
            ten++
        } else {
            if ten > 0 && five > 0 {
                ten--
                five--
            } else if five >= 3 {
                five -= 3
            } else {
                return false
            }
        }
    }
    return true
}