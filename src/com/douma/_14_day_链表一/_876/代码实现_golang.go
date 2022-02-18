/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 普通解法
func middleNode1(head *ListNode) *ListNode {
    if head == nil || head.Next == nil {
        return head
    }

    // 1. 计算链表的长度
    var length, curr = 0, head
    for curr != nil {
        length++
        curr = curr.Next
    }

    // 2. 找到链表的中间节点
    for i := 0; i < length / 2; i++ {
        head = head.Next
    }

    return head
}

// 快慢指针
func middleNode(head *ListNode) *ListNode {
    if head == nil || head.Next == nil {
        return head
    }

    var slow, fast = head, head
    for fast != nil && fast.Next != nil {
        slow = slow.Next
        fast = fast.Next.Next
    }

    return slow
}