public:
    bool isValid1(string s) {
        if (s.size() % 2 == 1) return false;
        stack<int> st;
        for (char c : s) {
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {
                if (st.empty()) return false;
                char topChar = st.top();
                st.pop();
                if (c == ')' && topChar != '(') return false;
                if (c == ']' && topChar != '[') return false;
                if (c == '}' && topChar != '{') return false;
            }
        }
        return st.empty();
    }

    bool isValid(string s) {
        if (s.size() % 2 == 1) return false;

        unordered_map<char, char> map = {
            {'(', ')'},
            {'{', '}'},
            {'[', ']'}
        };

        stack<int> st;
        for (char c : s) {
            if (map.count(c)) {
                st.push(c);
            } else {
                if (st.empty()) return false;
                char topChar = st.top();
                if (c != map[topChar]) return false;
                st.pop();
            }
        }
        return st.empty();
    }

    bool isValid(string s) {
        if (s.size() % 2 == 1) return false;
        stack<char> st;
        for (char c : s) {
            if (c == '(') {
                st.push(c);
            } else {
                if (st.empty()) return false;
                st.pop();
            }
        }
        return st.empty();
    }