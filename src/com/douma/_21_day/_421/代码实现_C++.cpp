class Node {
public:
    Node* zero = NULL;
    Node* one = NULL;

    Node() {}
};

class Trie {
private:
    Node* root;

public:
    Trie() {
        this->root = new Node();
    }

    void add(int num) {
        Node* curr = root;
        for (int k = 30; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (curr->zero == NULL) curr->zero = new Node();
                curr = curr->zero;
            } else {
                if (curr->one == NULL) curr->one = new Node();
                curr = curr->one;
            }
        }
    }

    int maxXor(int num) {
        Node* curr = root;
        int x = 0;
        for (int k = 30; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (curr->one != NULL) {
                    curr = curr->one;
                    x = 2 * x + 1;
                } else {
                    curr = curr->zero;
                    x = 2 * x;
                }
            } else {
                if (curr->zero != NULL) {
                    curr = curr->zero;
                    x = 2 * x + 1;
                } else {
                    curr = curr->one;
                    x = 2 * x;
                }
            }
        }
        return x;
    }
};


class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        Trie* trie = new Trie();
        int res = 0;
        for (int i = 1; i < nums.size(); i++) {
            trie->add(nums[i - 1]);
            res = max(res, trie->maxXor(nums[i]));
        }
        return res;
    }
};