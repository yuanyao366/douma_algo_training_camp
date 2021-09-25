/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 反转解法 ，空间复杂度 O(1)
func addTwoNumbers1(l1 *ListNode, l2 *ListNode) *ListNode {
    if l1 == nil {
        return l2
    }
    if l2 == nil {
        return l1
    }

    l1 = reverseList(l1)
    l2 = reverseList(l2)

    var retNode = addTwoNumbersHelp(l1, l2)
    return reverseList(retNode)
}

func reverseList(head *ListNode) *ListNode {
    var prev *ListNode = nil
    var curr = head
    for curr != nil {
        var next = curr.Next
        curr.Next = prev
        prev = curr
        curr = next
    }
    return prev
}

func addTwoNumbersHelp(l1 *ListNode, l2 *ListNode) *ListNode {
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

        sum := x + y + carry
        curr.Next = &ListNode{Val: sum % 10}
        // bug 修复：视频中忘了加上这一步
        curr = curr.Next
        carry = sum / 10

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


// 使用栈
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    if l1 == nil {
        return l2
    }
    if l2 == nil {
        return l1
    }

    // 将 l1 所有节点放到栈中
    var stack1 = make([]*ListNode, 0)
    for l1 != nil {
        stack1 = append(stack1, l1)
        l1 = l1.Next
    }

    // 将 l2 所有节点放到栈中
    var stack2 = make([]*ListNode, 0)
    for l2 != nil {
        stack2 = append(stack2, l2)
        l2 = l2.Next
    }

    var ans *ListNode = nil
    // 用于存储两数相加时的进位
    var carry = 0

    for len(stack1) != 0 || len(stack2) != 0 {
        var x, y = 0, 0
        if len(stack1) != 0 {
            x = stack1[len(stack1) - 1].Val
            stack1 = stack1[:len(stack1) - 1]
        }
        if len(stack2) != 0 {
            y = stack2[len(stack2) - 1].Val
            stack2 = stack2[:len(stack2) - 1]
        }

        var sum = x + y + carry

        var curr = &ListNode{Val: sum % 10}
        curr.Next = ans
        ans = curr

        carry = sum / 10
    }

    if carry != 0 {
        var curr = &ListNode{Val:carry}
        curr.Next = ans
        ans = curr
    }

    return ans
}