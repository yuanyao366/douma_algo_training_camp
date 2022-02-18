public:
    vector<int> addTwoNum(vector<int>& num1, vector<int>& num2) {
        vector<int> res;
        int carry = 0;
        int l1 = num1.size() - 1;
        int l2 = num2.size() - 1;
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 < 0 ? 0 : nums1[l1];
            int y = l2 < 0 ? 0 : nums2[l2];

            int sum = x + y + carry;
            res.push_back(sum % 10);
            carry = sum / 10;

            l1--;
            l2--;
        }
        if (carry) res.push_back(carry);
        reverse(res.begin(), res.end());
        return res;
    }