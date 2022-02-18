func convert1(s string, numRows int) string {

    temp := make([]string, numRows)

    i, n := 0, len(s)
    for i < n {
        // 垂直向下
        for idx := 0; idx < numRows && i < n; idx++ {
            temp[idx] += string(s[i])
            i++
        }

        // 右前向上
        for idx := numRows - 2; idx >= 1 && i < n; idx-- {
            temp[idx] += string(s[i])
            i++
        }
    }

    res := ""
    for j := 0; j < numRows; j++ {
        res += temp[j]
    }

    return res
}

// 使用一个变量 goingDown 控制方向
func convert2(s string, numRows int) string {
    // bug 修復：如果 numRows == 1 直接返回
    if numRows == 1 {
        return s
    }
    temp := make([]string, numRows)

    currRow, goingDown := 0, false
    for _, c := range s {
        temp[currRow] += string(c)
        if currRow == 0 || currRow == numRows - 1 {
            goingDown = !goingDown
        }
        if goingDown {
            currRow += 1
        } else {
            currRow -= 1
        }
    }

    res := ""
    for j := 0; j < numRows; j++ {
        res += temp[j]
    }

    return res
}

func convert(s string, numRows int) string {
    if numRows == 1 {
        return s
    }
    res := ""
    delta, n := 2 * numRows - 2, len(s)

    for row := 0; row < numRows; row++ {
        for col := 0; col + row < n; col += delta {
            res += string(s[row + col])
            if row != 0 && row != numRows - 1 && col + delta - row < n {
                res += string(s[col + delta - row])
            }
        }
    }

    return res
}