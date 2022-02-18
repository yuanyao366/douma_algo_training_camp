package com.douma.笔试代码.huawei.rongyao._20210906;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _3_Score_Ranking {
    private static class Student {
        String name;
        int chinese;
        int mathematics;
        int english;
        int totalScore;

        @Override
        public String toString() {
            return String.format("%s %d %d %d", name, chinese, mathematics, english);
        }
    }

    public static void main(String[] args) {
        // 1. 处理输入数据，并过滤不合格的学生
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String[] data = scanner.nextLine().split(" ");
            Student student = new Student();
            student.name = data[0];
            // 名字长度超过 10 ，不合格
            if (student.name.length() > 10) continue;
            student.chinese = Integer.valueOf(data[1]);
            // 语文成绩不合格
            if (student.chinese < 60) continue;
            student.mathematics = Integer.valueOf(data[2]);
            // 数学成绩不合格
            if (student.mathematics < 60) continue;
            student.english = Integer.valueOf(data[3]);
            // 英语成绩不合格
            if (student.english < 60) continue;
            student.totalScore = student.chinese + student.mathematics + student.english;
            students.add(student);
        }

        // 成绩排序
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // 先按总成绩降序排列
                if (s1.totalScore != s2.totalScore) {
                    return s2.totalScore - s1.totalScore;
                }
                // 再按语文成绩降序排列
                if (s1.chinese != s2.chinese) {
                    return s2.chinese - s1.chinese;
                }
                // 再按数学成绩降序排列
                if (s1.mathematics != s2.mathematics) {
                    return s2.mathematics - s1.mathematics;
                }
                // 再按英语成绩降序排列
                if (s1.english != s2.english) {
                    return s2.english - s1.english;
                }
                // 按照名字的 assci 码升序排列
                return s1.name.compareTo(s2.name);
            }
        });

        System.out.println("[First round name list]");
        for (Student s : students) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println("[Final name list]");
        int top = 3;
        Student selectedStu = students.get(0);
        for (int i = 1; i < students.size(); i++) {
            if (top > 0) {
                System.out.println(selectedStu);
            }
            Student currStu = students.get(i);
            // 如果排名一模一样的话，也输出
            // 只有当总成绩、语文、数学、英语成绩都不相同时，排名才不相同
            if (currStu.totalScore != selectedStu.totalScore ||
                    currStu.chinese != selectedStu.chinese ||
                    currStu.mathematics != selectedStu.mathematics ||
                    currStu.english != selectedStu.english) {
                top--;
            }
            selectedStu = currStu;
        }
    }
}
