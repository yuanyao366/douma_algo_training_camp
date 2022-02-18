// 抖码算法，让算法学习变的简单有趣
// 作者：老汤

class TwoSum {
    constructor() {
        this.nums = []
        this.isSorted = false
    }

    add(number) {
        this.nums.push(number)
        this.isSorted = false
    }

    find(value) {
        if (!this.isSorted) {
            this.nums.sort((a, b) => a - b); // O(nlogn)
            this.isSorted = true;
        }

        let left = 0;
        let right = this.nums.size() - 1;
        while (left < right) { // O(n)
            const sum = this.nums[left] + this.nums[right];
            if (sum == value) {
                return true;
            } else if (sum < value) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }
}