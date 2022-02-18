/**
 * @param {number[]} cards
 * @return {boolean}
 */
var judgePoint24 = function(cards) {
    const TARGET = 24, EPSILON = 1e-6
    const ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3

    const dfs = (list) => {
        if (list.length == 0) return false
        if (list.length == 1) return Math.abs(list[0] - TARGET) < EPSILON

        for (let i = 0; i < list.length; i++) {
            for (let j = 0; j < list.length; j++) {
                if (i != j) {
                    const childList = []
                    for (let k = 0; k < list.length; k++) {
                        if (k != i && k != j) childList.push(list[k])
                    }

                    for (let k = 0; k < 4; k++) {
                        // 0 + 1 = 1 + 0，剪枝
                        if (k < 2 && i > j) continue;
                        if (k == ADD) {
                            childList.push(list[i] + list[j]);
                        } else if (k == MULTIPLY) {
                            childList.push(list[i] * list[j]);
                        } else if (k == SUBTRACT) {
                            childList.push(list[i] - list[j]);
                        } else if (k == DIVIDE) {
                            if (Math.abs(list[i]) < EPSILON) {
                                continue;
                            } else {
                                childList.push(list[i] / list[j]);
                            }
                        }
                        if (dfs(childList)) return true;
                        childList.pop();
                    }
                }
            }
        }

        return false
    }

    return dfs(cards)
};