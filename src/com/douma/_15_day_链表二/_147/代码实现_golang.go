/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func insertionSortList(head *ListNode) *ListNode {
    if head == nil || head.Next == nil {
        return head
    }

    var dummyNode = &ListNode{Next:head}
    var prev, curr = head, head.Next
    for curr != nil {
        if curr.Val >= prev.Val {
            prev = curr
            curr = curr.Next
        } else {
            // 找到小于 curr.val 的最大的节点
            var p = dummyNode
            // 说明：这里的 p.next 不可能为空
            // 因为 p 从头开始，最远可以到达的节点是 curr 的前一个节点
            // 所以 p.next 不可能为 nil，我这里加上 p.next 的判空，是我个人的习惯哟~
            for p.Next != nil && p.Next.Val < curr.Val {
                p = p.Next
            }
            // 将 curr 插入到 p 和 p.next 之间
            prev.Next = curr.Next
            curr.Next = p.Next
            p.Next = curr

            curr = prev.Next
        }
    }
    return dummyNode.Next
}