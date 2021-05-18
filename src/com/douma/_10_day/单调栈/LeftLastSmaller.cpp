public:
    vector<int> findRightSmall(vector<int> &nums) {
        vector<int> ans(nums.size());
        stack<int> st;
        // 从右往左遍历
        for (size_t i = nums.size() - 1; i >= 0; i--) {
            int x = nums[i];
            // 单调递增栈
            while (!st.empty() && x < nums[st.peek()]) {
                ans[st.top()] = i;
                st.pop();
            }
            st.push(i);
        }
        while (!st.empty()) {
            ans[st.top()] = -1;
            st.pop();
        }
        return ans;
    }