/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    typedef unsigned long long ULL;
    // BFS
    int widthOfBinaryTree1(TreeNode* root) {
        if (root == nullptr) return 0;
        queue<pair<TreeNode*, ULL>> q;
        q.push(make_pair(root, 1));
        int maxWidth = 0;
        while (!q.empty()) {
            int size = q.size();
            ULL startSeqNo = 0;
            ULL endSeqNo = 0;
            for (int i = 0; i < size; i++) {
                pair<TreeNode*, ULL> p = q.front();
                q.pop();
                TreeNode *currNode = p.first;
                ULL seqNo = p.second;
                if (i == 0) startSeqNo = seqNo;
                if (i == size - 1) endSeqNo = seqNo;
                if (currNode->left) q.push(make_pair(currNode->left, 2 * seqNo));
                if (currNode->right) q.push(make_pair(currNode->right, 2 * seqNo + 1));
            }
            maxWidth = max(maxWidth, (int)(endSeqNo - startSeqNo + 1));
        }
        return maxWidth;
    }

    // DFS
    int widthOfBinaryTree2(TreeNode* root) {
        vector<ULL> start, end;
        return postorder(root, 0, 1, start, end);
    }

    int postorder(TreeNode* node, int currLevel, ULL seqNo, vector<ULL>& start, vector<ULL>& end) {
        if (node == nullptr) return 0;

        if (start.size() == currLevel) {
            start.push_back(seqNo);
            end.push_back(seqNo);
        } else {
            end[currLevel] = seqNo;
        }

        int leftMaxWidth = postorder(node->left, currLevel + 1, seqNo * 2, start, end);
        int rightMaxWidth = postorder(node->right, currLevel + 1, seqNo * 2 + 1, start, end);

        int currWidth = end[currLevel] - start[currLevel] + 1;

        return max(currWidth, max(leftMaxWidth, rightMaxWidth));
    }
};