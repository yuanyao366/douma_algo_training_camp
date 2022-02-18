class Solution {
public:
    // 线性查找
    // 时间复杂度：O(m*n*(min(m, n)))
    // 空间复杂度：min(m, n)
    vector<int> intersection1(vector<int>& nums1, vector<int>& nums2) {
        vector<int> res;
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 == num2) {
                    // 线性查找去重
                    if (find(res.begin(), res.end(), num1) == res.end()) res.push_back(num1);
                }
            }
        }
        return res;
    }

    // 线性查找
    // 时间复杂度：O(m*n))
    // 空间复杂度：min(m, n)
    vector<int> intersection2(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> set;
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 == num2) {
                    // 哈希去重
                    set.insert(num1);
                }
            }
        }
        vector<int> res(set.begin(), set.end());
        return res;
    }

    // 二分查找
    // 时间复杂度：O((m + n)logn)
    // 空间复杂度：min(m, n)
    vector<int> intersection3(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> set;

        sort(nums2.begin(), nums2.end());

        for (int num1 : nums1) {
            if (contains(nums2, num1)) set.insert(num1);
        }
        vector<int> res(set.begin(), set.end());
        return res;
    }

    bool contains(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    // 哈希查找
    // 时间复杂度：O(m + n)
    // 空间复杂度：min(m + n)
    vector<int> intersection4(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> set1;
        unordered_set<int> set2(nums2.begin(), nums2.end());
        for (int num1 : nums1) {
            // 哈希查找
            if (set2.count(num1)) set1.insert(num1);
        }
        vector<int> res(set1.begin(), set1.end());
        return res;
    }

    // 排序查找
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());

        vector<int> res;
        int i = 0, j = 0;
        while (i < nums1.size() && j < nums2.size()) {
            if (nums1[i] == nums2[j]) {
                if (res.empty() || res[res.size() - 1] != nums1[i]) {
                    res.push_back(nums1[i]);
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return res;
    }
};