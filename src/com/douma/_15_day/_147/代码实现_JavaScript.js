/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var insertionSortList = function(head) {
    if (!head || !head.next) {
        return head
    }

    const dummyNode = new ListNode(-1)
    dummyNode.next = head

    let prev = head, curr = head.next
    while (curr) {
        if (curr.val >= prev.val) {
            prev = curr
            curr = curr.next
        } else {
            // 找到小于 curr.val 的最大的节点
            let p = dummyNode
            while (p.next && p.next.val < curr.val) {
                p = p.next
            }
            // 将 curr 插入到 p 和 p.next 之间
            prev.next = curr.next
            curr.next = p.next
            p.next = curr

            curr = prev.next
        }
    }

    return dummyNode.next
};