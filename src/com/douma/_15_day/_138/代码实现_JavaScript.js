/**
 * // Definition for a Node.
 * function Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {Node} head
 * @return {Node}
 */
var copyRandomList1 = function(head) {

    const map = new Map()

    // 1. 递归解法
    const clone = oldNode => {
        if (!oldNode) return null
        const newNode = new Node(oldNode.val)
        map.set(oldNode, newNode)
        newNode.next = clone(oldNode.next)
        if (oldNode.random) newNode.random = map.get(oldNode.random)
        return newNode
    }

    return clone(head)
};

// 2. 迭代解法
var copyRandomList = function(head) {
    if (!head) return null

    const map = new Map()

    const get_clone_node = oldNode => {
        if (!oldNode) return null
        if (!map.has(oldNode)) {
            map.set(oldNode, new Node(oldNode.val))
        }
        return map.get(oldNode)
    }



    let oldNode = head
    let newNode = new Node(head.val)
    map.set(oldNode, newNode)
    const newHead = newNode
    while (oldNode) {
        newNode.next = get_clone_node(oldNode.next)
        newNode.random = get_clone_node(oldNode.random)
        oldNode = oldNode.next
        newNode = newNode.next
    }

    return newHead
};

// 3. 用新旧节点交替的方式，模拟 map 的功能
var copyRandomList = function(head) {
    if (!head) return null

     // 1. 新旧节点交替
    // 创建新节点，并且放在旧节点的后面
    // 假设原先链表是这样：A->B->C，那么创建新节点后，链表变成：A->A'->B->B'->C->C'
    // 其中 A' 是 A 的克隆节点
    let curr = head
    while (curr) {
        const newNode = new Node(curr.val)
        newNode.next = curr.next
        curr.next = newNode
        curr = newNode.next
    }

    // 2. 设置正确的 random
    curr = head
    while (curr) {
        if (curr.random) {
            curr.next.random = curr.random.next
        } else {
            curr.next.random = null
        }
        curr = curr.next.next
    }

    // 3. 分割新旧链表
    // 将 A->A'->B->B'->C->C' 切割成：A->B->C 和 A'->B'->C'
    let currOld = head, currNew = head.next
    const newHead = head.next
    while (currOld) {
        currOld.next = currOld.next.next
        if (currNew.next) {
            currNew.next = currNew.next.next
        } else {
            currNew.next = null
        }
        currNew = currNew.next
        currOld = currOld.next
    }
    return newHead
}

