class Solution {
public:
    // BFS
    vector<string> removeInvalidParentheses1(string s) {
        vector<string> res;
        queue<string> q;
        q.push(s);
        unordered_set<string> visited;
        visited.insert(s);

        bool found = false;
        while (!q.empty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                string currStr = q.front();
                q.pop();
                if (isValid(currStr)) {
                    res.push_back(currStr);
                    found = true;
                }
                if (found) continue;
                int currStrLen = currStr.length();
                for (int j = 0; j < currStrLen; j++) {
                    if (currStr[j] != '(' && currStr[j] != ')') continue;
                    string leftStr = currStr.substr(0, j);
                    string rightStr = (j == currStrLen - 1) ? "" : currStr.substr(j + 1, currStrLen - (j + 1));
                    string nextStr = leftStr + rightStr;
                    if (!visited.count(nextStr)) {
                        q.push(nextStr);
                        visited.insert(nextStr);
                    }
                }
            }
            if (found) break;
        }

        return res;
    }

    bool isValid(string s) {
        int count = 0;
        for (char c : s) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    // DFS
    unordered_set<string> res;
    string s;
    vector<string> removeInvalidParentheses(string s) {
        this->s = s;

        int leftRemove = 0, rightRemove = 0;
        for (char c : s) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove == 0) rightRemove++;
                else if (leftRemove > 0) leftRemove--;
            }
        }

        dfs(0, leftRemove, rightRemove, 0, 0, "");
        vector<string> ans;
        ans.assign(res.begin(), res.end());
        return ans;
    }

    void dfs(int index, int leftRemove, int rightRemove, int leftCount, int rightCount, string path) {
        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                res.insert(path);
            }
            return;
        }

        char c = s[index];
        if (c == '(' && leftRemove > 0) {
            dfs(index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, path);
        }
        if (c == ')' && rightRemove > 0) {
            dfs(index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, path);
        }

        if (c != '(' && c != ')') {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount, path + c);
        } else if (c == '(') {
            dfs(index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, path + c);
        } else if (rightCount < leftCount) {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, path + c);
        }
    }
};