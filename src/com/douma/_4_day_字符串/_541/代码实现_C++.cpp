public:
    string reverseStr(string s, int k) {
        for (int start = 0; start < s.size(); start += 2 * k) {
            int left = start;
            int right = min(left + k - 1, (int)s.size() - 1);
            while (left < right) {
                swap(s[left], s[right]);
                left++;
                right--;
            }
        }
        return s;
    }