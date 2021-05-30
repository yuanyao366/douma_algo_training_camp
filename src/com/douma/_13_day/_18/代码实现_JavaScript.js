/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[][]}
 */
var fourSum = function(nums, target) {
    if (nums.length < 4) return []

    nums.sort((a, b) => a - b)
    res = []

    for (let i = 0; i < nums.length - 3; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue

        for (let j = i + 1; j < nums.length - 2; j++) {
            if (j > i + 1 && nums[j] == nums[j - 1]) continue

            const partSum = nums[i] + nums[j]
            let left = j + 1, right = nums.length - 1
            while (left < right) {
                const sum = partSum + nums[left] + nums[right]
                if (sum == target) {
                    res.push([nums[i], nums[j], nums[left], nums[right]])
                    /*
                    下面的代码相当于：
                    while (left < right) {
                        // 不管前后相不相等，left 都要往前走
                        left++;
                        if (nums[left - 1] != nums[left]) break;
                    }
                    while (left < right) {
                        // 不管前后相不相等，right 都要往后走
                        right--;
                        if (nums[right + 1] != nums[right]) break;
                    }
                    */
                    // 去重
                    while (left < right && nums[left] == nums[++left]);
                    while (left < right && nums[right] == nums[--right]);
                } else if (sum < target) {
                    left++
                } else {
                    right--
                }
            }
        }
    }
    return res
};