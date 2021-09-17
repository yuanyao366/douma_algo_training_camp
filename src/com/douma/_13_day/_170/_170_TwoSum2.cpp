/**
* 抖码算法，让算法学习变的简单有趣
* @作者 : 老汤
*/

#include <unordered_map>

using namespace std;

class _170_TwoSum2 {
private:
    unordered_map<int, int> nums;

public:

    _170_TwoSum2() {}

    void add(int number) {
        if (nums.find(number) != nums.end()) {
            nums[number] += 1;
        } else {
            nums[number] = 1;
        }
    }

    bool find(int value) {
        for (auto& kv : nums) {
            int target = value - kv.first;
            if (target == kv.first && nums[target] > 1) return true;
            if (target != kv.first && nums.count(target)) return true;
        }
        return false;
    }
};