/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    dummy := &ListNode{Val: -1}
    curr, carry := dummy, 0
    for l1 != nil || l2 != nil {
        x, y := 0, 0
        if l1 != nil {
            x = l1.Val
        }
        if l2 != nil {
            y = l2.Val
        }

        total := x + y + carry
        curr.Next = &ListNode{Val: total % 10}
        // bug 修复：视频中忘了加上这一步
        curr = curr.Next
        carry = total / 10

        if l1 != nil {
            l1 = l1.Next
        }
        if l2 != nil {
            l2 = l2.Next
        }
    }
    if carry != 0 {
        curr.Next = &ListNode{Val: carry}
    }
    return dummy.Next
}