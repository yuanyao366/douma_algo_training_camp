class Solution {
public:
    // 栈
    string removeDuplicates(string s, int k) {
        vector<pair<int, char>> counts;
        for (int i = 0; i < s.size(); ++i) {
            if (counts.empty() || s[i] != counts.back().second) {
                counts.push_back({ 1, s[i] });
            } else if (++counts.back().first == k) {
                counts.pop_back();
            }
        }
        s = "";
        for (auto &p : counts) {
            s += string(p.first, p.second);
        }
        return s;

    }

    // 快慢指针
    string removeDuplicates2(string s, int k) {
        stack<int> count;
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++, slow++) {
            s[slow] = s[fast];
            if (slow == 0 || s[slow] != s[slow - 1]) {
                count.push(1);
            } else {
                int incremented = count.top() + 1;
                count.pop();
                if (incremented == k) {
                    slow = slow - k;
                } else {
                    count.push(incremented);
                }
            }
        }
        return s.substr(0, slow);
    }
};