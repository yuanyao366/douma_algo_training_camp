/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} root
 * @param {number} k
 * @return {ListNode[]}
 */
var splitListToParts = function(root, k) {
    let len = 0, curr = root
    while (curr) {
        len++
        curr = curr.next
    }

    const width = Math.floor(len / k), remainder = len % k
    const res = new Array(k)
    curr = root
    for (let i = 0; i < k; i++) {
        const head = curr
        const realWidth = width + (i < remainder ? 1 : 0) - 1;
        for (let j = 0; j < realWidth; j++) {
            if (curr) curr = curr.next
        }
        if (curr) {
            const next = curr.next
            curr.next = null
            curr = next
        }
        res[i] = head
    }
    return res
};