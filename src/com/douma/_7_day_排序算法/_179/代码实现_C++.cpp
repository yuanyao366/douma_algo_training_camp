int compare(int n1, int n2) {
    string s1 = to_string(n1);
    string s2 = to_string(n2);
    string order1 = s1 + s2;
    string order2 = s2 + s1;
    return order1 > order2;
}

class Solution {
public:
    string largestNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end(), compare);
        if (nums[0] == 0) return "0";
        string res;
        for (auto &num : nums) res += to_string(num);
        return res;
    }

};