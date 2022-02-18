package com.douma._0_day_准备.HJ68;

import java.util.Scanner;
import java.util.Arrays;

class Student {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public String toString() {
        return name + " " + grade;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = Integer.valueOf(in.next());
            int flag = Integer.valueOf(in.next());
            Student[] students = new Student[n];
            for (int i = 0; i < n; i++) {
                students[i] = new Student(in.next(), Integer.valueOf(in.next()));
            }

            if (flag == 1) { // 按照成绩升序排列
                Arrays.sort(students, (s1, s2) -> s1.getGrade() - s2.getGrade());
            } else { // 按照成绩降序排列
                Arrays.sort(students, (s1, s2) -> s2.getGrade() - s1.getGrade());
            }

            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}
