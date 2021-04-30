public:
    bool checkStraightLine(vector<vector<int>>& coordinates) {
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int x = coordinates[1][0] - x0;
        int y = coordinates[1][1] - y0;
        for (int i = 2; i < coordinates.size(); i++) {
            int xi = coordinates[i][0] - x0;
            int yi = coordinates[i][1] - y0;

            if (x * yi - y * xi) return false;
        }
        return true;
    }