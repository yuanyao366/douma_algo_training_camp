
var TARGET, EPSILON = float64(24), 1e-6
var ADD, MULTIPLY, SUBTRACT, DIVIDE = 0, 1, 2, 3

func judgePoint24(cards []int) bool {
    var list = make([]float64, len(cards))
    for i := range cards {
        list[i] = float64(cards[i])
    }

    var dfs func([]float64) bool
    dfs = func(list []float64) bool {
        if len(list) == 0 {
            return false
        }
        if len(list) == 1 {
            return abs(list[0] - TARGET) < EPSILON
        }

        var size = len(list)
        for i := 0; i < size; i++ {
            for j := 0; j < size; j++ {
                if i != j {
                    var childList = make([]float64, 0)
                    for k := 0; k < size; k++ {
                        if k != i && k != j {
                            childList = append(childList, list[k])
                        }
                    }
                    for k := 0; k < 4; k++ {
                        // 0 + 1 = 1 + 0，剪枝
                        if k < 2 && i > j {
                            continue
                        }
                        if k == ADD {
                            childList = append(childList, list[i] + list[j])
                        } else if k == MULTIPLY {
                            childList = append(childList, list[i] * list[j])
                        } else if k == SUBTRACT {
                            childList = append(childList, list[i] - list[j])
                        } else if k == DIVIDE {
                            if abs(list[j]) < EPSILON {
                                continue
                            } else {
                                childList = append(childList, list[i] / list[j])
                            }
                        }
                        if dfs(childList) {
                            return true
                        }
                        childList = childList[:len(childList) - 1]
                    }
                }
            }
        }
        return false
    }

    return dfs(list)
}

func abs(a float64) float64 {
    if a < 0 {
        return -a
    }
    return a
}