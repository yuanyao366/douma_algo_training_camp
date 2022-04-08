#include <iostream>
#include <vector>
#include <string>
using namespace std;

/* 力扣 98. 验证二叉搜索树
   给定一个二叉树，判断其是否是一个有效的二叉搜索树。
   假设一个二叉搜索树具有如下特征：
       1.节点的左子树只包含小于当前节点的数。
       2.节点的右子树只包含大于当前节点的数。
       3. 所有左子树和右子树自身必须也是二叉搜索树。

        输入:
           2
          / \
         1   3
       输出: true

       输入:
           5
          / \
         1   4
            / \
           3   6
       输出: false
    */

/*
ACM 模式输入描述:
第一行两个数 n, root，分别表示二叉树有 n 个节点，第 root 个节点时二叉树的根
接下来共 n 行，第 i 行三个数 val_i, left_i, right_i，分别表示第 i 个节点的值 val，左儿子，右儿子
值为 null 则表示空节点。

输入:
    5 1
    5 2 3
    1 null null
    3 4 5
    4 null null
    6 null null
 */

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

long long LONG_MIN = -2147483647 - 1;
long long LONG_MAX = 2147483647;

bool isValidBST_help(TreeNode* node, long long lower, long long upper) {
    if (!node) return true;
    if (node->val <= lower || node->val >= upper) {
        return false;
    }

    return isValidBST_help(node->left, lower, node->val) && isValidBST_help(node->right, node->val, upper);
}

// 2. 后序遍历
bool isValidBST(TreeNode* root) {
    return isValidBST_help(root, LONG_MIN, LONG_MAX);
}


int main() {
  int n, rootIndex;
  cin >> n >> rootIndex;

  // 1. 构造一棵树
  // 记录每个节点
  vector<TreeNode*> tree(n);
  // 记录每个节点的左右子节点的值
  vector<pair<string,string>> leaf(n);

  for (int i = 0; i < n; i++) {
      string root, left, right;
      cin >> root >> left >> right;
      struct TreeNode* node = new TreeNode(atoi(root.c_str()));
      leaf[i].first = left;
      leaf[i].second = right;
      tree[i] = node;
  }

  for (int i = 0; i < n; i++) {
    // 左子节点
    string left = leaf[i].first;
    if ("null" != left) {
        // 这里减 1 是因为：节点值从 1 开始，而索引从 0 开始
        tree[i]->left = tree[atoi(left.c_str()) - 1];
    }
    // 右子节点
    string right = leaf[i].second;
    if ("null" != right) {
        // 这里减 1 是因为：节点值从 1 开始，而索引从 0 开始
        tree[i]->right = tree[atoi(right.c_str()) - 1];
    }
  }

  // 2. 拿到树的根节点
  TreeNode* root = tree[rootIndex];

  // 3. 判断这棵树是否是二叉查找树
  bool res = isValidBST(root);

  if (res)
    cout << "true";
  else
    cout << "false";
}

