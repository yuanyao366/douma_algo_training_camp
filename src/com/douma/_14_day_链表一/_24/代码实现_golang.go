/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func swapPairs(head *ListNode) *ListNode {
    if head == nil || head.Next == nil {
        return head
    }

    var dummyNode = &ListNode{Next:head}

    var prev, first, second = dummyNode, head, head.Next
    for second != nil {
        var n = second.Next
        prev.Next = second
        second.Next = first
        first.Next = n

        prev = first
        first = n
        if first == nil {
            break
        }
        second = first.Next
    }

    return dummyNode.Next
}