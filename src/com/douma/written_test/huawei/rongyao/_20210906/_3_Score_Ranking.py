# 抖码算法，让算法学习变得简单有趣
# 作者：小莹

from functools import cmp_to_key


class Student:
    def __init__(self):
        self.name = None
        self.chinese = None
        self.mathematics = None
        self.english = None
        self.total_score = None

    def to_string(self):
        return "{} {} {} {}".format(self.name, self.chinese, self.mathematics, self.english)


def sort_score_key(s1, s2):
    # 先按总成绩降序排列
    if s1.total_score != s2.total_score:
        return s2.total_score - s1.total_score
    if s1.chinese != s2.chinese:
        return s2.chinese - s1.chinese
    if s1.mathematics - s2.mathematics:
        return s2.mathematics - s1.mathematics
    if s1.english != s2.english:
        return s2.english > s1.english
    if s1.name > s2.name:
        return 1
    else:
        return -1


if __name__ == '__main__':
    # 1. 处理输入数据，并过滤不合格的学生
    students = []
    for i in range(10):
        data = str(input()).split(" ")
        stu = Student()
        stu.name = data[0]
        # 名字长度超过 10 ，不合格
        if len(stu.name) > 10:
            continue
        stu.chinese = int(data[1])
        # 语文成绩不合格
        if stu.chinese < 60:
            continue
        stu.mathematics = int(data[2])
        # 数学成绩不合格
        if stu.mathematics < 60:
            continue
        stu.english = int(data[3])
        # 英语成绩不合格
        if stu.english < 60:
            continue
        stu.total_score = stu.chinese + stu.mathematics + stu.english
        students.append(stu)

    # 成绩排序
    students.sort(key=cmp_to_key(sort_score_key))

    print("[First round name list]")
    for s in students:
        print(s.to_string())

    print("")
    print("[Final name list]")
    top, selected_stu = 3, students[0]
    for i in range(1, len(students)):
        if top > 0:
            print(selected_stu.to_string())
        curr_stu = students[i]
        # 如果排名一模一样的话，也输出
        # 有当总成绩、语文、数学、英语成绩都不相同时，排名才不相同
        if (curr_stu.total_score != selected_stu.total_score or
                curr_stu.chinese != selected_stu.chinese or
                curr_stu.mathematics != selected_stu.mathematics or
                curr_stu.english != selected_stu.english):
            top -= 1
        selected_stu = curr_stu
