while (line = readline()) {
    const n = parseInt(line)
    const flag = parseInt(readline())
    const students = []
    for (let i = 0; i < n; i++) {
        const student = readline().split(' ')
        students.push({"name": student[0], "grade": parseInt(student[1]), "index": i})
    }

    if (flag == 1) {
        // 因为在 chrome 浏览器下，sort 是不稳定的，为了保证相同成绩都按先录入排列在前的规则处理。
        // 使用学生的序号 index 来保证
        students.sort((s1, s2) => s1.grade - s2.grade || s1.index - s2.index)
    } else {
        students.sort((s1, s2) => s2.grade - s1.grade || s1.index - s2.index)
    }

    for (const student of students) {
        console.log(student.name + " " + student.grade)
    }
}