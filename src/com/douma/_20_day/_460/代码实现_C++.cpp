class Node {
public:
    int key, val, count;
    Node* next = NULL;
    Node* prev = NULL;

    Node() {}

    Node(int key, int val, int count) {
        this->key = key;
        this->val = val;
        this->count = count;
    }
};

class DoubleLinkedList {
private:
    Node* head;
    Node* tail;

public:
    DoubleLinkedList() {
        this->head = new Node();
        this->tail = new Node();
        this->head->next = this->tail;
        this->tail->prev = this->head;
    }

    Node* remove(Node* node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
        node->prev = NULL;
        node->next = NULL;
        return node;
    }

    // 将 node 拼接到表尾
    void append(Node* node){
        node->prev = this->tail->prev;
        this->tail->prev->next = node;
        node->next = this->tail;
        this->tail->prev = node;
    }


    Node* popFirst() {
        if (this->head->next == this->tail)
            return NULL;
        return this->remove(this->head->next);
    }


    bool isEmpty() {
        return this->head->next == this->tail;
    }
};

class LFUCache {
private:
    unordered_map<int, Node*> keyToNode;
    unordered_map<int, DoubleLinkedList*> usedCountToKeys;

    int capacity;
    int minUsedCount;

public:
    LFUCache(int capacity) {
        this->capacity = capacity;
        minUsedCount = 0;
    }

    int get(int key) {
        if (capacity == 0) return -1;

        if (!keyToNode.count(key)) return -1;

        Node* node = keyToNode[key];
        // 维护这个 key 对应的 count
        int usedCount = node->count;

        // 1. 从这个 key 目前对应的 count 的集合中删除掉这个 key
        usedCountToKeys[usedCount]->remove(node);

        // 2. 更新最小使用的次数
        // 如果当前的 usedCount 等于最小次数，
        // 并且当前的 usedCount 没有的 key，那么将最小次数加 1
        if (usedCount == minUsedCount && usedCountToKeys[usedCount]->isEmpty()) {
            minUsedCount++;
        }

        // 3. 将 key 记录到 usedCount + 1 中的集合中
        putUsedCount(node, usedCount + 1);

        return node->val;
    }

    void putUsedCount(Node* node, int count) {
        node->count = count;
        if (!usedCountToKeys.count(count)) {
            usedCountToKeys[count] = new DoubleLinkedList();
        }
        usedCountToKeys[count]->append(node);
    }

    void put(int key, int value) {
        if (capacity == 0) return;
        if (keyToNode.count(key)) {
            // 更新 key 对应的 value 值
            Node* node = keyToNode[key];
            node->val = value;
            keyToNode[key] = node;
            // 更新 key 对应的 count 值
            get(key);
            return;
        }

        if (keyToNode.size() == capacity) {
            // 删除最少使用的 key
            Node* removeNode = usedCountToKeys[minUsedCount]->popFirst();
            keyToNode.erase(removeNode->key);
        }

        // 新增一个缓存中不存在的 key
        Node* node = new Node(key, value, 1);
        keyToNode[key] = node;

        // 将 key 记录到 minUsedCount 中的集合中
        minUsedCount = 1;
        putUsedCount(node, minUsedCount);
    }
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache* obj = new LFUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */