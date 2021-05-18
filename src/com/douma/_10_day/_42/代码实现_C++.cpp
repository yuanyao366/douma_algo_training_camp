class Solution {
public:
    // 预计算左右最大高度
    int trap1(vector<int>& height) {
        int n = height.size();
        if (n <= 2) return 0;

        vector<int> leftMax(n);
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = max(leftMax[i - 1], height[i]);
        }

        vector<int> rightMax(n);
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            int maxHeight = min(leftMax[i], rightMax[i]);
            if (maxHeight > height[i])
                ans += maxHeight - height[i];
        }

        return ans;
    }

    // 双指针优化
    int trap2(vector<int>& height) {
        int n = height.size();
        if (n <= 2) return 0;

        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;

        int ans = 0;
        while (left < right) {
            leftMax = max(leftMax, height[left]);
            rightMax = max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }

        return ans;
    }

    int trap(vector<int>& height) {
        int n = height.size();
        if (n <= 2) return 0;

        int ans = 0;
        stack<int> st;
        for (int i = 0; i < n; i++) {
            while (!st.empty() && height[i] > height[st.top()]) {
                int top = st.top();
                st.pop();
                if (st.empty()) break;

                int leftIndex = st.top();
                int currWidth = i - leftIndex - 1;
                int currHeight = min(height[leftIndex], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            st.push(i);
        }
        return ans;
    }
};