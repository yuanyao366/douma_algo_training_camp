/**
* 抖码算法，让算法学习变的简单有趣
* @作者 : 老汤
*/

#include <vector>
#include <unordered_set>

using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;

    TreeNode(int val) : val(val), left(nullptr), right(nullptr) {}
};

void inOrder(TreeNode* node, vector<int>& nums) {
    if (node == nullptr) return;

    inOrder(node->left, nums);
    nums.push_back(node->val);
    inOrder(node->right, nums);
}

// 二分查找
bool findTarget1(TreeNode* root, int target) {
    if (root == nullptr) return false;

    vector<int> nums;
    inOrder(root, nums); // O(n)

    int len = nums.size();
    int left = 0, right = len - 1;
    while (left < right) {
        int sum = nums[left] + nums[right];
        if ( sum == target)
            return true;
        else if (sum < target)
            left++;
        else
            right--;
    }

    return false;
}


// 注意 c++ 的 set 需要传址，而非传值
bool find(TreeNode* node, int target, unordered_set<int>& set) {
    if (node == nullptr) return false;
    if (set.count(target - node->val)) return true;
    set.insert(node->val);
    return find(node->left, target, set) || find(node->right, target, set);
}

// 哈希查找
bool findTarget2(TreeNode* root, int target) {
    if (root == nullptr) return false;
    unordered_set<int> set;
    return find(root, target, set);
}

