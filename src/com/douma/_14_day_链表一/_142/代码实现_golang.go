/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 哈希查找
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func detectCycle1(head *ListNode) *ListNode {
    var visited = make(map[*ListNode]bool)
    for head != nil {
        if visited[head] {
            return head
        }
        visited[head] = true
        head = head.Next
    }
    return nil
}

// 快慢指针
// 时间复杂度：O(n):
// 空间复杂度：O(1)
func detectCycle(head *ListNode) *ListNode {
    var slow, fast = head, head
    for fast != nil && fast.Next != nil {
        slow = slow.Next
        fast = fast.Next.Next
        if slow == fast {
            var p = head
            for p != slow {
                p = p.Next
                slow = slow.Next
            }
            return p
        }
    }
    return nil
}