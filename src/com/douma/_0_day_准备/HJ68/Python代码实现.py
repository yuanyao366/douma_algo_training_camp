while True:
    try:
        n = int(input())
        flag = int(input())
        students = []
        for each in range(n):
            student = input().split(' ')
            name, score = student[0], int(student[1])
            students.append((name,score))
        if flag == 1:
            students.sort(key = lambda x:x[1])
        else:
            students.sort(key = lambda x:x[1], reverse = True)

        for student in students:
            print(student[0] + ' ' + str(student[1]))
    except:
        break