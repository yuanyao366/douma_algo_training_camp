/*
// Definition for Employee.
class Employee {
public:
    int id;
    int importance;
    vector<int> subordinates;
};
*/

class Solution {
private:
    unordered_map<int, Employee*> empMap;
public:
    int getImportance(vector<Employee*> employees, int id) {
        for (Employee* emp : employees) {
            empMap[emp->id] = emp;
        }

        return bfs(id);
    }

    int dfs(int id) {
        Employee* emp = empMap[id];
        if (!emp) return 0;

        int total = 0;
        for (int subordinateId : emp->subordinates) {
            total += dfs(subordinateId);
        }

        return emp->importance + total;
    }

    int bfs(int id) {
        Employee* emp = empMap[id];
        if (!emp) return 0;

        int total = 0;
        queue<Employee*> q;
        q.push(emp);
        while (!q.empty()) {
            Employee* e = q.front();
            q.pop();
            total += e->importance;
            for (int subordinateId : e->subordinates) {
                q.push(empMap[subordinateId]);
            }
        }
        return total;
    }
};