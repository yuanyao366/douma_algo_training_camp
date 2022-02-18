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
var deleteDuplicates = function(head) {
    if (!head || !head.next) return head
    const dumyNode = new ListNode(-1)
    dumyNode.next = head

    let prev = dumyNode, curr = head
    while (curr) {
        if (curr.next && curr.val == curr.next.val) {
            do {
                curr = curr.next
            } while (curr.next && curr.val == curr.next.val)
            prev.next = curr.next;
            curr.next = null
        } else {
            prev = curr
        }
        curr = prev.next
    }
    return dumyNode.next
};