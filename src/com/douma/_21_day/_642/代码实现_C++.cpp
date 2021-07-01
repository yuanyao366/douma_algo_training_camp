class SentenceInfo {
public:
    string content;
    int time;

    SentenceInfo(string content, int time) {
        this->content = content;
        this->time = time;
    }
};

class TrieNode {
public:
    unordered_map<char, TrieNode*> children;
    // times == 0 说明，这个节点不是字符串的结尾节点
    // times > 0 说明是结尾字符节点
    int times = 0;

    TrieNode() {}
};

int compare(SentenceInfo* s1, SentenceInfo* s2) {
    return s1->time == s2->time ? s1->content < s2->content : s1->time > s2->time;
}

class AutocompleteSystem {
private:
    TrieNode* root;
    string currentSentence;

public:
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
        this->root = new TrieNode();
        this->currentSentence = "";

        int size = sentences.size();
        for (int i = 0; i < size; i++) {
            insert(sentences[i], times[i]);
        }
    }
    
    vector<string> input(char c) {
        vector<string> res;
        if (c == '#') {
            insert(currentSentence, 1);
            currentSentence = "";
        } else {
            // 1. 将当前输入字符拼接到当前句子
            currentSentence.push_back(c);

            // 2. 找到所有以当前输入字符串开头的句子
            // 从字典树中找到包含 currSentence 为前缀的所有句子
            vector<SentenceInfo*> sentences = lookup(currentSentence);

            // 3. 拿到被搜索次数排名前 3 的句子
            sort(sentences.begin(), sentences.end(), compare);

            int size = (int)sentences.size();
            for (int i = 0; i < min(3, size); i++) {
                res.push_back(sentences[i]->content);
            }
        }
        return res;
    }

private:
    void insert(string s, int time) {
        TrieNode* curr = root;
        
        for (char c : s) {
            if (curr->children[c] == NULL) {
                curr->children[c] = new TrieNode();
            }
            curr = curr->children[c];
        }
        curr->times += time;
    }

    // 从当前前缀树中搜索以 s 开头的所有的句子
    vector<SentenceInfo*> lookup(string s) {
        vector<SentenceInfo*> res;

        // 1. 找到前缀
        TrieNode* curr = root;
        for (char c : s) {
            if (curr->children[c] == NULL) {
                return res;
            }
            curr = curr->children[c];
        }

        // 2. 找到以前缀结尾的所有的字符串
        dfs(curr, s, res);
        
        return res;
    }

    void dfs(TrieNode* curr, string s, vector<SentenceInfo*>& res) {
        if (curr->times > 0) {
            res.push_back(new SentenceInfo(s, curr->times));
        }

        for (auto [c, node] : curr->children) {
            dfs(node, s + c, res);
        }
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */