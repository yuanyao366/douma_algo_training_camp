var sortColors = function(nums) {
    let zero = 0, two = nums.length - 1
    let i = 0
    while (i <= two) {
        if (nums[i] == 0) {
            swap(nums, i, zero)
            i++
            zero++
        } else if (nums[i] == 2) {
            swap(nums, i, two)
            two--
        } else {
            i++
        }
    }
};

const swap = (nums, i, j) => {
    const temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
};