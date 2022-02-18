/**
 * Definition for Employee.
 * type Employee struct {
 *     Id int
 *     Importance int
 *     Subordinates []int
 * }
 */

// DFS 前序遍历
func getImportance1(employees []*Employee, id int) int {
    var idEmpMap = make(map[int]*Employee)
    for _, e := range employees {
        idEmpMap[e.Id] = e
    }

    var res = 0

    var dfs func(int)
    dfs = func(id int) {
        var emp = idEmpMap[id]
        if emp == nil {
            return
        }

        res += emp.Importance

        for _, subordinateId := range emp.Subordinates {
            dfs(subordinateId)
        }
    }

    dfs(id)

    return res
}


// DFS 后序遍历
func getImportance2(employees []*Employee, id int) int {
    var idEmpMap = make(map[int]*Employee)
    for _, e := range employees {
        idEmpMap[e.Id] = e
    }

    var dfs func(int) int
    dfs = func(id int) int {
        var emp = idEmpMap[id]
        if emp == nil {
            return 0
        }

        var total = 0
        for _, subordinateId := range emp.Subordinates {
            total += dfs(subordinateId)
        }

        return emp.Importance + total
    }

    return dfs(id)
}


// BFS
func getImportance(employees []*Employee, id int) int {
    var idEmpMap = make(map[int]*Employee)
    for _, e := range employees {
        idEmpMap[e.Id] = e
    }

    var emp = idEmpMap[id]
    if emp == nil {
        return 0
    }
    var res = 0
    var queue = list.New()
    queue.PushBack(emp)
    for queue.Len() > 0 {
        var e = queue.Remove(queue.Front()).(*Employee)
        res += e.Importance
        for _, subordinateId := range e.Subordinates {
            queue.PushBack(idEmpMap[subordinateId])
        }
    }

    return res
}