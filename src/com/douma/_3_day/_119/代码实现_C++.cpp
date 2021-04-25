public:
    vector<int> getRow(int rowIndex) {
        vector<int> oneRow(rowIndex + 1);
        oneRow[0] = 1;

        for (int row = 1; row <= rowIndex; row++) {
            for (int col = row; col > 0; col--) {
                oneRow[col] += oneRow[col - 1];
            }
        }

        return oneRow;
    }