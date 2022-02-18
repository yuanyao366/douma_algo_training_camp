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
        // 这里 -1 的原因：每一段 curr 需要走的步数比这一段的节点数少 1 个
        // 比如链表：1 -> 2 -> 3 -> 4
        // 链表的长度为 4 ，也就是 4 个节点
        // 但是从第一个节点开始，只需要走 3 步就可以到达最后一个节点
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