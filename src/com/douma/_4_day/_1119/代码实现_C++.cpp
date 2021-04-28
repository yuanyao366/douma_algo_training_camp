public:
    string removeVowels(string s) {
        string res;
        for (char c : s) {
            if (!isVowel(c)) res += c;
        }
        return res;
    }

    bool isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }