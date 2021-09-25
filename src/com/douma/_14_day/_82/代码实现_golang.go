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

    var dummyNode = &ListNode{Next:head}
    var prev, curr = dummyNode, head
    for curr != nil {
        if curr.Next != nil && curr.Val == curr.Next.Val {
            for curr.Next != nil && curr.Val == curr.Next.Val {
                curr = curr.Next
            }
            prev.Next = curr.Next
            curr.Next = nil
        } else {
            prev = curr
        }
        curr = prev.Next
    }

    return dummyNode.Next
}