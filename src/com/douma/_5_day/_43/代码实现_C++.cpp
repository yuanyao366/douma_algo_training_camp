public:
    string multiply(string num1, string num2) {
        if (num1 == "0" || num2 == "0") return "0";

        int m = num1.length();
        int n = num2.length();

        vector<int> res(m + n);
        for (int i = m - 1; i >= 0; i--) {
            int x = num1[i] - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2[j] - '0';
                int sum = res[i + j + 1] + x * y;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        string ans = "";
        for (int i = 0; i < res.size(); i++) {
            if (i == 0 && res[i] == 0) continue;
            ans.push_back(res[i] + '0');
        }

        return ans;
    }