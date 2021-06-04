/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var detectCycle1 = function(head) {
    const visited = new Set()
    while (head) {
        if (visited.has(head)) return head
        visited.add(head)
        head = head.next
    }
    return null
};

var detectCycle = function(head) {
    let slow = head, fast = head
    while (fast && fast.next) {
        slow = slow.next
        fast = fast.next.next
        if (slow == fast) {
            let p = head
            while (p != slow) {
                p = p.next
                slow = slow.next
            }
            return p
        }
    }
    return null
}