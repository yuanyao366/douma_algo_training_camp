/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
// BFS
var widthOfBinaryTree1 = function(root) {
    if (!root) return 0

    const queue = [[root, BigInt(1)]]
    let maxWidth = 0
    while (queue.length) {
        const size = queue.length
        let startSeqNo = BigInt(0), endSeqNo = BigInt(0)
        for (let i = 0; i < size; i++) {
            [currNode, seqNo] = queue.shift()
            if (i == 0) startSeqNo = seqNo
            if (i == size - 1) endSeqNo = seqNo
            if (currNode.left) queue.push([currNode.left, BigInt(BigInt(2) * seqNo)])
            if (currNode.right) queue.push([currNode.right, BigInt(BigInt(2) * seqNo + BigInt(1))])
        }
        maxWidth = Math.max(maxWidth, Number(endSeqNo - startSeqNo) + 1)
    }
    return maxWidth
};

// DFS
var widthOfBinaryTree2 = function(root) {
    if (!root) return 0

    const postorder = (node, level, seqNo, start, end) => {
        if (!node) return 0

        if (start.length == level) {
            start.push(seqNo)
            end.push(seqNo)
        } else {
            end[level] = seqNo
        }

        const leftMaxWidth = postorder(node.left, level + 1, BigInt(BigInt(2) * seqNo), start, end)
        const rightMaxWidth = postorder(node.right, level + 1, BigInt(BigInt(2) * seqNo + BigInt(1)), start, end)

        const currMaxWidth = Number(end[level] - start[level]) + 1

        return Math.max(currMaxWidth, Math.max(leftMaxWidth, rightMaxWidth))
    }

    return postorder(root, 0, BigInt(1), new Array(), new Array())
}


// DFS , 不需要 BigInt
var widthOfBinaryTree = function(root) {
    if (!root) return 0

    start = []

    const postorder = (node, level, seqNo) => {
        if (!node) return 0

        if (start.length == level) {
            start.push(seqNo)
        }

        const diff = seqNo - start[level]

        const currMaxWidth = diff + 1

        const leftMaxWidth = postorder(node.left, level + 1, 2 * diff)
        const rightMaxWidth = postorder(node.right, level + 1, 2 * diff + 1)


        return Math.max(currMaxWidth, Math.max(leftMaxWidth, rightMaxWidth))
    }

    return postorder(root, 0, 0)
}