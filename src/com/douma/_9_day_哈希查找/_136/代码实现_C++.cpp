class Solution {
public:
    int singleNumber(vector<int>& nums) {
        unordered_map<int, int> map;
        for (int num : nums) {
            map[num] += 1;
        }
        for (auto &t : map) {
            if (t.second == 1) return t.first;
        }
        return -1;
    }

    int singleNumber1(vector<int>& nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
};