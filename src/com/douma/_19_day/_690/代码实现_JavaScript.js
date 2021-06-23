/**
 * Definition for Employee.
 * function Employee(id, importance, subordinates) {
 *     this.id = id;
 *     this.importance = importance;
 *     this.subordinates = subordinates;
 * }
 */

/**
 * @param {Employee[]} employees
 * @param {number} id
 * @return {number}
 */
const empMap = new Map()

var GetImportance = function(employees, id) {
    for (const e of employees) {
        empMap.set(e.id, e)
    }
    return bfs(id)
};

const dfs = function(id) {
    const emp = empMap.get(id)
    let total = 0
    for (const subordinateId of emp.subordinates) {
        total += dfs(subordinateId)
    }
    return emp.importance + total
}

const bfs = function(id) {
    let total = 0;
    const queue = [], emp = empMap.get(id)
    queue.push(emp)
    while (queue.length) {
        const e = queue.shift()
        total += e.importance
        for (const subordinateId of e.subordinates) {
            queue.push(empMap.get(subordinateId))
        }
    }
    return total
}