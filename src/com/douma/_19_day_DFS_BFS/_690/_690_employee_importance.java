package com.douma._19_day_DFS_BFS._690;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _690_employee_importance {
    /* 690. 员工的重要性
     给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。

     比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。
     那么员工 1 的数据结构是 [1, 15, [2]] ，
     员工 2的 数据结构是 [2, 10, [3,4]] ，
     员工 3 的数据结构是 [3, 5, []] 。
     员工 4 的数据结构是 [4, 2, []] 。
     注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，
     因此没有体现在员工 1 的数据结构中。

    现在输入一个公司的所有员工信息，以及单个员工 id ，
    返回这个员工和他所有下属的重要度之和。

    示例：
    输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
    输出：5 + 3 + 3 = 11

    提示：
    一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
    员工数量不超过 2000 。
     */

    private Map<Integer, Employee> map = new HashMap<>();
    private int res = 0;
    // DFS
    public int getImportance1(List<Employee> employees, int id) {
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        dfs1(id);
        return res;
    }

    // 前序遍历
    private void dfs1(int id) {
        Employee emp = map.get(id);
        if (emp == null) return;

        res += emp.importance;

        for (int subordinateId : emp.subordinates) {
            dfs1(subordinateId);
        }
    }

    // DFS
    public int getImportance2(List<Employee> employees, int id) {
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return dfs(id);
    }

    // 后序遍历
    private int dfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return 0;

        int total = 0;
        for (int subordinateId : emp.subordinates) {
            total += dfs(subordinateId);
        }

        return emp.importance + total;
    }

    // BFS
    public int getImportance(List<Employee> employees, int id) {
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return bfs(id);
    }

    private int bfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return 0;

        int res = 0;
        Queue<Employee> queue = new ArrayDeque<>();
        queue.offer(emp);
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            res += e.importance;
            for (int subordinateId : e.subordinates) {
                queue.offer(map.get(subordinateId));
            }
        }
        return res;
    }
}
