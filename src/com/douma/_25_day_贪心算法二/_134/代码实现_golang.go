// 暴力解法
 // O(n^2)
func canCompleteCircuit1(gas []int, cost []int) int {
    for i := range gas {
        if gas[i] < cost[i] {
            continue
        }
        var index, remainGas = i, gas[i] - cost[i]
        for remainGas >= 0 {
            index = (index + 1) % len(gas)
            if index == i {
                return i
            }
            remainGas = remainGas - cost[index] + gas[index]
        }
    }
    return -1
}


// 贪心
 // O(n)
func canCompleteCircuit(gas []int, cost []int) int {
    // totalGas 表示总油量
    // currGas 表示当前总油量
    var totalGas, currGas, startStation = 0, 0, 0
    for i := range gas {
        totalGas += gas[i] - cost[i]
        currGas += gas[i] - cost[i]
        if currGas < 0 {
            startStation = i + 1
            currGas = 0
        }
    }
    if totalGas >= 0 {
        return startStation
    } else {
        return -1
    }
}