class Solution {
public:
    string simplifyPath(string path) {
        // 使用数组模拟栈的功能
        vector<string> st;
        stringstream ss(path);
        string dir;
         while(getline(ss, dir, '/')) {
            if (dir == "" || dir == ".") continue;
            else if (dir == ".." && st.empty()) continue;
            else if (dir == ".." && !st.empty()) st.pop_back();
            else st.push_back(dir);
        }
        if (st.empty()) return "/";
        string res;
        for (auto dir : st) res += "/" + dir;
        return res;
    }
};