class Solution {
public:
    // 贪心 + 双端队列
    string removeKdigits(string num, int k) {
        deque<char> d;
        for (char c : num) {
            while (!d.empty() && k > 0 && d.front() > c) {
                d.pop_front();
                k--;
            }
            d.push_front(c);
        }

        for (int i = 0; i < k; i++) {
            d.pop_front();
        }

        string res = "";
        bool isFirst = true;
        while (!d.empty()) {
            char c = d.back();
            d.pop_back();
            if (c == '0' && isFirst) continue;
            res += c;
            isFirst = false;
        }

        return res.length() == 0 ? "0" : res;
    }
};