public:
    string convert(string s, int numRows) {
        if (numRows == 1) return s;
        int n = s.length();
        vector<string> rows(min(n, numRows));

        int currRow = 0;
        bool goingDown = false;
        for (char c : s) {
            rows[currRow] += c;
            if (currRow == 0 || currRow == numRows - 1) goingDown = !goingDown;
            if (goingDown) currRow ++;
            else currRow--;
        }

        for (int i = 1; i < rows.size(); i++) {
            rows[0] += rows[i];
        }

        return rows[0];
    }