package main
import (
    "fmt"
)

/*
输入两行：
5
3 4 1 2 4

输出
34124
*/
// 使用 fmt.Scanf 来实现
func main(){

    var length int
    fmt.Scanf("%d", &length)

    nums := make([]int, length)
    for i := 0; i < length; i++ {
        var num int
        fmt.Scanf("%d", &num)
        nums[i] = num
    }

    for i := 0; i < length; i++ {
        fmt.Printf("%d", nums[i])
    }

}


package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
    "strings"
)
/*
输入两行：
5
3 4 1 2 4

输出
34124
*/
// 使用 bufio.NewScanner 来实现
func main(){

    scanner := bufio.NewScanner(os.Stdin)

    scanner.Scan()
    length,_ := strconv.Atoi(scanner.Text())
    nums := make([]int, length)

    scanner.Scan()
    arr := strings.Split(scanner.Text()," ")

    for i := 0; i < length; i++ {
        nums[i], _ = strconv.Atoi(arr[i])
    }

    for i := 0; i < length; i++ {
        fmt.Printf("%d", nums[i])
    }
}