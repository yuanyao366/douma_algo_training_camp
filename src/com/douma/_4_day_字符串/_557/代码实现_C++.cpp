public:
    string reverseWords(string s) {
        int n = s.length();
        int left = 0;
        while (left < n) {
            if (s[left] != ' ') {
                int right = left;
                while (right + 1 < n && s[right + 1] != ' ') right++;
                reverse(s.begin() + left, s.begin() + right + 1);
                left = right + 1;
            } else {
                left++;
            }
        }
        return s;
    }