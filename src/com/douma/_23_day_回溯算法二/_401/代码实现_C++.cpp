class Solution {
private:
    vector<string> res;
public:
    vector<string> readBinaryWatch(int turnedOn) {
        vector<int> nums1 = {8, 4, 2, 1};
        vector<int> nums2 = {32, 16, 8, 4, 2, 1};

        vector<string> res;
        for (int i = 0; i <= turnedOn; i++) {
            vector<int> hours = genDigits(nums1, i);
            vector<int> minutes = genDigits(nums2, turnedOn - i);
            for (int hour : hours) {
                if (hour > 11) continue;
                for (int minute : minutes) {
                    if (minute > 59) continue;
                    res.push_back(to_string(hour) + ":" + ((minute < 10) ? "0" + to_string(minute) : to_string(minute)));
                }
            }
        }
        return res;
    }

    vector<int> genDigits(vector<int> nums, int count) {
        vector<int> res;
        dfs(nums, count, 0, 0, res);
        return res;
    }

    void dfs(vector<int> nums, int count, int startIndex, int sum, vector<int>& res) {
        if (count == 0) {
            res.push_back(sum);
            return;
        }

        for (int i = startIndex; i < nums.size(); i++) {
            dfs(nums, count - 1, i + 1, sum + nums[i], res);
        }
    }
};