package main

import (
    "bufio"
    "fmt"
    "os"
    "sort"
    "strconv"
    "strings"
)

type Student struct {
    Name string
    Grade int
}

func main(){
    scanner := bufio.NewScanner(os.Stdin)

    for scanner.Scan() {
        n,_ := strconv.Atoi(scanner.Text())

        scanner.Scan()
        flag,_ := strconv.Atoi(scanner.Text())

        students := make([]Student, n)
        for i := 0; i < n; i++ {
            scanner.Scan()
            arr := strings.Split(scanner.Text()," ")
            name := arr[0]
            grade,_ := strconv.Atoi(arr[1])
            students[i] = Student{name, grade}
        }

        if flag == 1 {
            sort.SliceStable(students, func(i,j int) bool {
                    return students[i].Grade < students[j].Grade
                })
        } else {
            sort.SliceStable(students, func(i,j int) bool {
                    return students[i].Grade > students[j].Grade
                })
        }

        for _, s := range students {
            fmt.Println(s.Name + " " + strconv.Itoa(s.Grade))
        }
    }
}


// 使用 fmt.Scanf
package main

import (
    "bufio"
    "fmt"
    "os"
    "sort"
    "strconv"
    "strings"
)

type Student struct {
    Name string
    Grade int
}

func main(){
    scanner := bufio.NewScanner(os.Stdin)

    for scanner.Scan() {
        n,_ := strconv.Atoi(scanner.Text())

        scanner.Scan()
        flag,_ := strconv.Atoi(scanner.Text())

        students := make([]Student, n)
        for i := 0; i < n; i++ {
            scanner.Scan()
            arr := strings.Split(scanner.Text()," ")
            name := arr[0]
            grade,_ := strconv.Atoi(arr[1])
            students[i] = Student{name, grade}
        }

        if flag == 1 {
            sort.SliceStable(students, func(i,j int) bool {
                    return students[i].Grade < students[j].Grade
                })
        } else {
            sort.SliceStable(students, func(i,j int) bool {
                    return students[i].Grade > students[j].Grade
                })
        }

        for _, s := range students {
            fmt.Println(s.Name + " " + strconv.Itoa(s.Grade))
        }
    }
}