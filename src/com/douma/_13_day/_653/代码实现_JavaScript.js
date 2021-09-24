// 抖码算法，让算法学习变的简单有趣
// 作者：老汤


class TreeNode {
    constructor(val) {
        this.val = val
        this.left = null
        this.right = null
    }
}


// 二分查找
var findTarget1 = function (root, target) {
    if (!root) return false

    const nums = []

    const inOrder = (node) => {
        if (!node) return
        inOrder(node.left)
        res.push(node.val)
        inOrder(node.right)
    }

    inOrder(root)

    const len = nums.length;
    let left = 0, right = len - 1;
    while (left < right) {
        const sum = nums.get(left) + nums.get(right);
        if ( sum == target)
            return true;
        else if (sum < target)
            left++;
        else
            right--;
    }

    return false;
}

var findTarget = function (root, target) {
    if (!root) return false

    const set = new Set()

    const find = (node) => {
        if (!node) return false
        if (set.has(target - node.val)) return true
        set.add(node.val)
        return find(node.left) || find(node.right)
    }

    return find(root)
}