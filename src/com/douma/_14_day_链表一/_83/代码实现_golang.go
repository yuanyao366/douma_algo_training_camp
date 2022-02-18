/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteDuplicates(head *ListNode) *ListNode {
    if head == nil || head.Next == nil {
        return head
    }

    var prev, curr = head, head.Next
    for curr != nil {
        if curr.Val == prev.Val {
            prev.Next = curr.Next
            curr.Next = nil
        } else {
            prev = curr
        }
        curr = prev.Next
    }
    return head
}