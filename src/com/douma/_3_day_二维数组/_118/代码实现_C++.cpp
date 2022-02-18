public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> rows;
        for (int row = 0; row < numRows; row++) {
            vector<int> oneRow;
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    oneRow.push_back(1);
                } else {
                    vector<int> preRow = rows[row - 1];
                    oneRow.push_back(preRow[col - 1] + preRow[col]);
                }
            }
            rows.push_back(oneRow);
        }
        return rows;
    }