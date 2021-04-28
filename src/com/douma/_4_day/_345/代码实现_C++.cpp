public:
    string reverseVowels(string s) {
        if (s.size() == 0) return s;
        int n = s.size();
        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (left < right && !isVowel(s[left])) left++;
            while (left < right && !isVowel(s[right])) right--;

            swap(s[left], s[right]);

            left++;
            right--;
        }
        return s;
    }

    bool isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }