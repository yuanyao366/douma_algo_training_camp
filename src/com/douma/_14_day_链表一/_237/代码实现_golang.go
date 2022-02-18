/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 时间复杂度：O(n)
func deleteNode(node *ListNode) {
    var prev *ListNode = nil;
    for node != nil {
        var next = node.Next
        if next != nil {
            node.Val = next.Val
        } else {
            prev.Next = nil
        }
        prev = node
        node = node.Next
    }
}

// 代码优化
// 时间复杂度：O(1)
func deleteNode(node *ListNode) {
    // 实际上，我们只需要将下一个节点的值覆盖掉要删除的节点的值就可以
    node.Val = node.Next.Val
    // 然后将下一个节点从链表中断开
    var next = node.Next
    node.Next = next.Next
    next.Next = nil
}