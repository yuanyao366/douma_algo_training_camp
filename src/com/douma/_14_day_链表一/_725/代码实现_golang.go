/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 方法一
func splitListToParts1(head *ListNode, k int) []*ListNode {
    // 计算链表的长度
    var length, curr = 0, head
    for curr != nil {
        length++
        curr = curr.Next
    }

    var width, remainder = length / k, length % k
    var res = make([]*ListNode, k)
    curr = head
    for i := 0; i < k; i++ {
        var dummyNode = &ListNode{Val:-1}
        var node = dummyNode
        var realWidth = width
        if i < remainder {
            realWidth += 1
        }
        for j := 0; j < realWidth; j++ {
            node.Next = &ListNode{Val:curr.Val}
            node = node.Next
            if curr != nil {
                curr = curr.Next
            }
        }
        res[i] = dummyNode.Next
    }
    return res
}

// 方法二
func splitListToParts(head *ListNode, k int) []*ListNode {
    // 计算链表的长度
    var length, curr = 0, head
    for curr != nil {
        length++
        curr = curr.Next
    }

    var width, remainder = length / k, length % k
    var res = make([]*ListNode, k)
    curr = head
    for i := 0; i < k; i++ {
        var node = curr
        // 这里 -1 的原因：每一段 curr 需要走的步数比这一段的节点数少 1 个
        // 比如链表：1 -> 2 -> 3 -> 4
        // 链表的长度为 4 ，也就是 4 个节点
        // 但是从第一个节点开始，只需要走 3 步就可以到达最后一个节点
        var realWidth = width - 1
        if i < remainder {
            realWidth += 1
        }
        for j := 0; j < realWidth; j++ {
            if curr != nil {
                curr = curr.Next
            }
        }
        // bug 修复：判断 curr 不等于 null
        if curr != nil {
            var n = curr.Next
            curr.Next = nil
            curr = n
        }
        res[i] = node
    }
    return res
}