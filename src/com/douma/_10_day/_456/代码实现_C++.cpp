class Solution {
public:
    //1. 单变量维护最小值 + 线性查找 k
    // 时间复杂度：O(n^2)
    bool find132pattern1(vector<int>& nums) {
        int n = nums.size();
        if (n < 3) return false;

        int numsi = nums[0];
        for (int j = 1; j < n; j++) {
            // 线性查找
            for (int k = j + 1; k < n; k++) {
                if (numsi < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
            }
            // 维护最小的 nums[i]
            numsi = min(numsi, nums[j]);
        }
        return false;
    }

    //2. 单变量维护最小值 + 二叉查找树查找 k
    // 时间复杂度：O(nlogn)
    bool find132pattern2(vector<int>& nums) {
        int n = nums.size();
        if (n < 3) return false;

        int numsi = nums[0];

        multiset<int> rightAll; // 有序集合
        for (int k = 2; k < n; k++) {
            rightAll.insert(nums[k]);
        }

        for (int j = 1; j < n - 1; j++) {
            if (nums[j] > numsi) {
                auto numsk = rightAll.upper_bound(numsi);
                if (numsk != rightAll.end() && *numsk < nums[j]) {
                    return true;
                }
            }
            // 维护最小的 nums[i]
            numsi = min(numsi, nums[j]);

            rightAll.erase(rightAll.find(nums[j + 1]));
        }
        return false;
    }

    //3. 预计算前缀最小值 + 单调栈
    // 时间复杂度：O(n)
    bool find132pattern3(vector<int>& nums) {
        int n = nums.size();
        if (n < 3) return false;

        vector<int> minPrefix(n);
        minPrefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            minPrefix[i] = min(minPrefix[i - 1], nums[i]);
        }

        stack<int> st;
        st.push(nums[n - 1]);
        for (int j = n - 2; j >= 1; j--) {
            if (nums[j] > minPrefix[j]) {
                while (!st.empty() && st.top() <= minPrefix[j]) {
                    st.pop();
                }
                if (!st.empty() && st.top() < nums[j]) return true;
                st.push(nums[j]);
            }
        }

        return false;
    }

    //4. 单调栈
    // 时间复杂度：O(n)
    bool find132pattern(vector<int>& nums) {
        int n = nums.size();
        if (n < 3) return false;

        int maxk = INT_MIN;
        stack<int> st;
        st.push(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < maxk) return true;
            while (!st.empty() && nums[i] > st.top()) {
                maxk = st.top();
                st.pop();
            }
            if (nums[i] > maxk) st.push(nums[i]);
        }
        return false;
    }
};