/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 删除头节点和非头节点逻辑分开
func removeElements1(head *ListNode, val int) *ListNode {
    // 1. 删除表头元素
    for head != nil && head.Val == val {
        var delNode = head
        head = head.Next
        delNode.Next = nil
    }

    if head == nil {
        return nil
    }

    // 2. 删除非表头元素
    var curr, prev = head.Next, head
    for curr != nil {
        if curr.Val == val {
            prev.Next = curr.Next
            curr.Next = nil
        } else {
            prev = curr
        }
        curr = prev.Next
    }

    return head
}

// 使用虚拟头结点，统一头节点和非头节点删除逻辑
func removeElements(head *ListNode, val int) *ListNode {
    var dummyNode = &ListNode{Val:-1}
    dummyNode.Next = head

    // 2. 删除非表头元素
    var curr, prev = head, dummyNode
    for curr != nil {
        if curr.Val == val {
            prev.Next = curr.Next
            curr.Next = nil
        } else {
            prev = curr
        }
        curr = prev.Next
    }

    return dummyNode.Next
}