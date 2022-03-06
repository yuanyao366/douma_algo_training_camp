 /*
ACM 模式下，如果输入的是链表，
需要自己根据输入数据，在内存中构建链表
比如有道题目输入是链表。然后会给出下面一行数据：
23 22 1 5 6 3
 */

type Node struct {
    Val  int
    Next *Node
}

package main
import (
    "fmt"
)
func main(){
    scanner := bufio.NewScanner(os.Stdin)
    dummyNode := &Node{Val:-1}
    curr := dummyNode
    for scanner.Scan() {
        value,_ := strconv.Atoi(scanner.Text())
        curr.next = Node{Val:value}
        curr = curr.next
    }

    // 对 dummyNode 操作

    // 最后打印的时候，是要遍历操作后的链表
}