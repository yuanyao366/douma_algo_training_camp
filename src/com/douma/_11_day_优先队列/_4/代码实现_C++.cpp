class Solution {
public:
    // 二分查找
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(); // 5
        int n = nums2.size(); // 10
        int leftK = (m + n + 1) / 2; // 8
        int rightK = (m + n + 2) / 2; // 8
        // 将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        int lower = getKth(nums1, nums2, leftK);
        int upper = getKth(nums1, nums2, rightK);
        return (lower + upper) * 0.5;
    }

    int getKth(vector<int>& nums1, vector<int>& nums2, int k) {
        int len1 = nums1.size();
        int len2 = nums2.size();
        int i = 0, j = 0;
        while (true) {
            if (i == len1) return nums2[j + k - 1];
            if (j == len2) return nums1[i + k - 1];
            if (k == 1) return min(nums1[i], nums2[j]);
            // tips：计算 newi 和 newj 需要减 1 的原因：k/2 表示的是长度，长度是从 1 开始，而下标是从 0 开始的，所以需要减 1
            int newi = min(i + (k / 2), len1) - 1;
            int newj = min(j + (k / 2), len2) - 1;
            // tips：而下面计算 i 和 j 的时候加 1 的原因是：就是排除 i 前面或者 j 前面的元素，所以往前走一个
            // 计算 k 的时候加 1 的原因是：用从 0 开始的下标计算从 1 开始的长度，都需要加 1 的
            if (nums1[newi] < nums2[newj]) {
                k = k - (newi - i + 1);
                i = newi + 1;
            } else {
                k = k - (newj - j + 1);
                j = newj + 1;
            }
        }
    }

    // 数组划分
    double findMedianSortedArrays2(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        if (m > n) {
            swap(nums1, nums2);
            swap(m, n);
        }

        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                int maxLeft;
                if (i == 0) maxLeft = nums2[j - 1];
                else if (j == 0) maxLeft = nums1[i - 1];
                else maxLeft = max(nums1[i - 1], nums2[j - 1]);
                if ((m + n) % 2 == 1) return maxLeft;

                int minRight;
                if (i == m) minRight = nums2[j];
                else if (j == n) minRight = nums1[i];
                else minRight = min(nums1[i], nums2[j]);

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
};