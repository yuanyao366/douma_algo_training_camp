/**
 * @param {number[]} gas
 * @param {number[]} cost
 * @return {number}
 */
var canCompleteCircuit = function(gas, cost) {
    let totalGas = 0, currGas = 0, startStation = 0
    for (let i = 0; i < gas.length; i++) {
        totalGas += gas[i] - cost[i]
        currGas += gas[i] - cost[i]
        if (currGas < 0) {
            startStation = i + 1
            currGas = 0
        }
    }
    return totalGas >= 0 ? startStation : -1
};