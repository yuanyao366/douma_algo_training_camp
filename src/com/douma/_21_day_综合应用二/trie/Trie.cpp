/**
* 抖码算法，让算法学习变的简单有趣
* @作者 : 老汤
*/

#include <unordered_map>
#include <string>
#include <iostream>
using namespace std;

struct Node {
    // 每个节点包含：
    // 1. 一个字符
    // 2. 若干个子节点
    unordered_map<char, Node*> children;

    // 标识，这个节点是否是一个单词最后一个字符
    bool isWord = false;

    Node() {}
};

class Trie {
private:
    Node* root;

public:
    Trie() {
        root = new Node();
    }

    // 添加单词
    // O(n)
    void add(string word) {
        Node* curr = root;
        for (char c : word) { // O(n)
            // 1. 先从子节点中查找是否包含当前字符 c
            if (curr->children[c] == nullptr) {
                curr->children[c] = new Node();
            }
            curr = curr->children[c];
        }
        curr->isWord = true;
    }

    // 判断是否包含指定的单词
    // O(n)
    bool contains(string word) {
        Node* curr = root;
        for (char c : word) { // O(n)
            // 1. 先从子节点中查找是否包含当前字符 c
            if (curr->children[c] == nullptr) {
                return false;
            }
            curr = curr->children[c];
        }
        return curr->isWord;
    }
};

int main() {
    Trie trie;
    trie.add("big");
    trie.add("pat");
    trie.add("bigger");
    trie.add("dog");
    trie.add("door");

    cout << trie.contains("dog") << endl;

    return 0;
}