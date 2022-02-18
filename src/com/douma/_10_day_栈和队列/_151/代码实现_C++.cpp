class Solution {
public:
    // 不使用内置 API
    string reverseWords1(string s) {
        s = trimSpaces(s, s.length());
        reverse(s, 0, s.length() - 1);
        reverseEachWord(s);
        return s;
    }

    string trimSpaces(string s, int n) {
        int slow = 0, fast = 0;
        while (fast < n) {
            while (fast < n && s[fast] == ' ') fast++;
            while (fast < n && s[fast] != ' ') s[slow++] = s[fast++];
            while (fast < n && s[fast] == ' ') fast++;
            if (fast < n) s[slow++] = ' ';
        }
        return s.substr(0, slow);
    }

    void reverse(string &a, int i, int j) {
        while (i < j)  swap(a[i++],a[j--]);
    }

    void reverseEachWord(string &s) {
        int n = s.length();
        int left = 0;
        while (left < n) {
            if (s[left] != ' ') {
                int right = left;
                while (right + 1 < n && s[right + 1] != ' ') right++;
                reverse(s, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
    }

    // 双端队列
    string reverseWords(string s) {
        int left = 0, right = s.size() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s[left] == ' ') ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s[right] == ' ') --right;

        deque<string> deque;
        string word;
        while (left <= right) {
            char c  = s[left];
            if (c != ' ') {
                word += c;
            } else if (word.size()) {
                deque.push_front(word);
                word = "";
            }
            left++;
        }
        deque.push_front(word);

        string ans;
        while (!deque.empty()) {
            ans += deque.front();
            deque.pop_front();
            if (!deque.empty()) ans += " ";
        }
        return ans;
    }
};