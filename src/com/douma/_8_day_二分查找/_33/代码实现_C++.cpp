class Solution {
public:
    // 时间复杂度：O(logn)，注意，视频中说时间复杂度是 O(n)，这是口误
    int search(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // 上面的代码如果不理解的话，可以这样来理解：
    int search2(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            // 当只有两个元素的时候，left 和 mid 指向的都是第一个元素
            // 这个时候 left == mid，那么 [left, mid] 和 [mid + 1, right] 都只有一个元素
            if (nums[left] == nums[mid]) {
                if (target == nums[right]) return right;
                else return -1;
            }

            if (nums[left] < nums[mid]) { // 左边有序
                if (target == nums[left]) return left;

                if (nums[left] < target && target < nums[mid]) { // target 在左边
                    right = mid - 1;
                } else { // 否则 target 在右边
                    left = mid + 1;
                }
            } else { // 右边有序
                if (target == nums[right]) return right;

                if (nums[mid] < target && target < nums[right]) { // target 在右边
                    left = mid + 1;
                } else { // 否则 target 在左边
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
};