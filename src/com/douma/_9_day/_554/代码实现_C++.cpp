class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        unordered_map<int, int> edgeFreq;
        int maxFreq = 0;
        for (int row = 0; row < wall.size(); row++) {
            int edgePosition = 0;
            for (int col = 0; col < wall[row].size() - 1; col++) {
                int currBrikLength = wall[row][col];
                edgePosition += currBrikLength;
                edgeFreq[edgePosition] += 1;
                maxFreq = max(maxFreq, edgeFreq[edgePosition]);
            }
        }
        return wall.size() - maxFreq;
    }
};