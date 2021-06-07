/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode[]} lists
 * @return {ListNode}
 */
var mergeKLists = function(lists) {
    if (lists.length == 0) return null
    return merge(lists, 0, lists.length - 1)
};

var merge = function(lists, left, right) {
    if (left == right) return lists[left]
    if (left > right) return null

    const mid = left + Math.floor((right - left) / 2)
    const mergedLeftList = merge(lists, left, mid)
    const mergedRightList = merge(lists, mid + 1, right)

    return mergeTwoLists(mergedLeftList, mergedRightList)
}

var mergeTwoLists = function(l1, l2) {
    if (!l1) return l2
    if (!l2) return l1

    const dummyNode = new ListNode(-1)
    let curr = dummyNode;
    while (l1 && l2) {
        if (l1.val <= l2.val) {
            curr.next = l1
            l1 = l1.next
        } else {
            curr.next = l2
            l2 = l2.next
        }
        curr = curr.next
    }
    if (l1) curr.next = l1
    if (l2) curr.next = l2
    return dummyNode.next
};