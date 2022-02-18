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
var swapPairs = function(head) {
    if (!head || !head.next) return head

    const dummyNode = new ListNode(-1)
    dummyNode.next = head

    let prev = dummyNode, first = head, second = head.next
    while (second) {
        const next = second.next
        prev.next = second
        second.next = first
        first.next = next

        prev = first
        first = next
        if (!first) break
        second = first.next
    }

    return dummyNode.next
};