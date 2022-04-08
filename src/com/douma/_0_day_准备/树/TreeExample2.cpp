#include <iostream>
#include <vector>
#include <string>
#include <regex>
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
CM 模式输入描述:
        第一行表示所有的节点

    输入:
        5,1,4,null,null,3,6

    说明：
        (1) 索引为 0 的节点 5 是根节点
        (2) 索引为 0 的节点的左节点是：2 * 0 + 1 = 1，也就是索引为 1 的节点
        (3) 索引为 0 的节点的右节点是：2 * 0 + 2 = 2，也就是索引为 2 的节点
        (2) 索引为 1 的节点的左节点是：2 * 1 + 1 = 3，也就是索引为 3 的节点
        (3) 索引为 1 的节点的右节点是：2 * 1 + 2 = 4，也就是索引为 4 的节点
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
  string line;
  getline(cin, line);

  regex re(",");
  vector<string> data(sregex_token_iterator(line.begin(), line.end(), re, -1), sregex_token_iterator());

  int n = data.size();

  // 1. 构造一棵树
  // 记录每个节点
  vector<TreeNode*> nodes(n);
  for (int i = 0; i < n; i++) {
    string val = data[i];
    if ("null" != val) {
      struct TreeNode* node = new TreeNode(atoi(val.c_str()));
      nodes[i] = node;
    }
  }

  for (int i = 0; i * 2 + 2 < n; i++) {
    if (nodes[i] != nullptr) {
        nodes[i]->left = nodes[2 * i + 1];
        nodes[i]->right = nodes[2 * i + 2];
    }
  }


  // 2. 拿到树的根节点
  TreeNode* root = nodes[0];

  // 3. 判断这棵树是否是二叉查找树
  bool res = isValidBST(root);

  if (res)
    cout << "true";
  else
    cout << "false";
}

