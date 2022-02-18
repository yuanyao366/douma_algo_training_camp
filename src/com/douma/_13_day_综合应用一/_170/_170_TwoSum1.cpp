/**
* 抖码算法，让算法学习变的简单有趣
* @作者 : 老汤
*/

#include <vector>
#include <algorithm>

using namespace std;

class _170_TwoSum1 {
private:
    vector<int> nums;
    bool isSorted;

public:
    _170_TwoSum1() {
        this->isSorted = false;
    }

    void add(int number) {
        nums.push_back(number); // O(1)
        isSorted = false;
    }

    // 查找是否存在两个数，这两个数的和等于 value
    bool find(int value) { // O(nlogn)
        if (!isSorted) {
            sort(nums.begin(), nums.end()); // O(nlogn)
            isSorted = true;
        }

        int left = 0;
        int right = nums.size() - 1;
        while (left < right) { // O(n)
            int sum = nums[left] + nums[right];
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
};