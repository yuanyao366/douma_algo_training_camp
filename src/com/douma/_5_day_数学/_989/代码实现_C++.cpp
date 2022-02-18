public:
    vector<int> addToArrayForm(vector<int>& A, int K) {
        vector<int> res;
        int i = A.size() - 1, carry = 0;
        while (i >= 0 || K != 0) {
            int x = i >= 0 ? A[i] : 0;
            int y = K != 0 ? K % 10 : 0;

            int sum = x + y + carry;
            res.push_back(sum % 10);
            carry = sum / 10;

            i--;
            K = K / 10;
        }
        if (carry) res.push_back(carry);
        reverse(res.begin(), res.end());
        return res;
    }