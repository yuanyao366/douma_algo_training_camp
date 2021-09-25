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