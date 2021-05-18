public:
    vector<int> findRightSmall(vector<int> &nums) {
        vector<int> ans(nums.size());
        stack<int> st;
        for (size_t i = 0; i < nums.size(); i++) {
            int x = nums[i];
            // 单调递减栈
            while (!st.empty() && x > nums[st.peek()]) {
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