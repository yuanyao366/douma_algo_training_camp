class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& A) {
        int less = 0, great = A.size() - 1;
        while (less < great) {
            if (A[less] % 2 > A[great] % 2) {
                swap(A[less], A[great]);
            }
            if (A[less] % 2 == 0) less++;
            if (A[great] % 2 != 0) great--;
        }
        return A;
    }
};