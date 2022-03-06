package main
import (
    "bufio"
    "fmt"
    "os"
)
// 使用 bufio.NewScanner
func main1(){
    // 获取输入
    scanner := bufio.NewScanner(os.Stdin)
    scanner.Scan()
    //转换，byte不支持中文，rune支持中文
    str : =[]rune(scanner.Text())

    l:=len(str)

    //逆序
    for i := 0; i < l/2; i++ {
        str[i], str[l - i - 1] = str[l - i - 1], str[i]
    }

    fmt.Println(string(str[:]))
}

// 也可以使用 fmt.Scanf
package main
import (
    "fmt"
)
func main(){
    // 获取输入
    line := ""
    fmt.Scanf("%s", &line)

    str := []rune(line)
    l:=len(str)

    //逆序
    for i := 0; i < l/2; i++ {
        str[i], str[l - i - 1] = str[l - i - 1], str[i]
    }

    fmt.Println(string(str[:]))
}