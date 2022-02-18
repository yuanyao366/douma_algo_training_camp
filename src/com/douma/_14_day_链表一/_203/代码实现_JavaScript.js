/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} val
 * @return {ListNode}
 */
var removeElements = function(head, val) {
    const dummyNode = new ListNode(-1)
    dummyNode.next = head

    let prev = dummyNode, curr = head
    while (curr) {
        if (curr.val == val) {
            prev.next = curr.next
            curr.next == null
        } else {
            prev = curr
        }
        curr = prev.next
    }
    return dummyNode.next
};