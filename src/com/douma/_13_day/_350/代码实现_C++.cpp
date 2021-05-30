class Solution {
public:
    // 哈希查找
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    vector<int> intersect1(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> countMap;
        for (int num : nums1) {
            countMap[num]++;
        }

        vector<int> res;
        for (int num : nums2) {
            if (countMap.count(num) && countMap[num] > 0) {
                res.push_back(num);
                countMap[num]--;
            }
        }

        return res;
    }

    // 二分查找
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    vector<int> intersect2(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());

        // 时间复杂度：O(mlogn) + n
        vector<int> res;
        int i = 0;
        while (i < nums1.size()) {
            int lowerBound = searchFirstTarget(nums2, nums1[i]);
            if (lowerBound == -1) {
                i++;
                continue;
            }

            int count = 0;
            while (lowerBound < nums2.size() && nums2[lowerBound] == nums1[i]) {
                count++;
                lowerBound++;
            }

            int j = i;
            while (j < nums1.size() && nums1[j] == nums1[i]) {
                j++;
                if (count > 0) {
                    res.push_back(nums1[i]);
                    count--;
                }
            }
            i = j;
        }
        return res;
    }

    int searchFirstTarget(vector<int>& nums, int& target) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return target == nums[left] ? left : -1;
    }

    // 排序查找
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());

        vector<int> res;
        int i = 0, j = 0;
        while (i < nums1.size() && j < nums2.size()) {
            if (nums1[i] == nums2[j]) {
                res.push_back(nums1[i]);
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