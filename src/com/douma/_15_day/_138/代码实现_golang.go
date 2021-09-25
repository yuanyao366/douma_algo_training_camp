/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */

var nodeMap = make(map[*Node]*Node)

// 1. 递归解法
func copyRandomList1(head *Node) *Node {
    if head == nil {
        return head
    }

    var newNode = &Node{Val:head.Val}
    nodeMap[head] = newNode

    newNode.Next = copyRandomList1(head.Next)
    newNode.Random = nodeMap[head.Random]

    return newNode
}

// 2. 迭代解法
func copyRandomList2(head *Node) *Node {
    if head == nil {
        return head
    }

    var oldNode = head
    var newNode = &Node{Val:head.Val}

    // bug 修复
    nodeMap[oldNode] = newNode

    var newHead = newNode
    for oldNode != nil {
        newNode.Next = getCloneNode(oldNode.Next)
        newNode.Random = getCloneNode(oldNode.Random)

        oldNode = oldNode.Next
        newNode = newNode.Next
    }

    return newHead
}

func getCloneNode(node *Node) *Node {
    if node == nil {
        return nil
    }

    if nodeMap[node] == nil {
        nodeMap[node] = &Node{Val:node.Val}
    }

    return nodeMap[node]
}


// 3. 用新旧节点交替的方式，模拟 map 的功能
func copyRandomList(head *Node) *Node {
    if head == nil {
        return head
    }

    // 1. 新旧节点交替
    // 创建新节点，并且放在旧节点的后面
    // 假设原先链表是这样：A->B->C，那么创建新节点后，链表变成：A->A'->B->B'->C->C'
    // 其中 A' 是 A 的克隆节点
    var curr = head
    for curr != nil {
        var newNode = &Node{Val:curr.Val}
        newNode.Next = curr.Next
        curr.Next = newNode
        curr = newNode.Next
    }

    // 2. 设置正确的 random
    curr = head
    for curr != nil {
        if curr.Random != nil {
            curr.Next.Random = curr.Random.Next
        } else {
            curr.Next.Random = nil
        }
        curr = curr.Next.Next
    }

    // 3. 分割新旧链表
    // 将 A->A'->B->B'->C->C' 切割成：A->B->C 和 A'->B'->C'
    var currOld, currNew, newHead = head, head.Next, head.Next
    for currOld != nil {
        currOld.Next = currOld.Next.Next
        if currNew.Next != nil {
            currNew.Next = currNew.Next.Next
        } else {
            currNew.Next = nil
        }
        currOld = currOld.Next
        currNew = currNew.Next
    }
    return newHead
}