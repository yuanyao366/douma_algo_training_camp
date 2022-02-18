class Node {
public:
    int key, value;
    Node* next = NULL;
    Node* prev = NULL;

    Node() {}
};

class LRUCache {
private:
    unordered_map<int, Node*> cache;
    int capacity;
    Node* head = NULL;
    Node* tail = NULL;

public:
    LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        head->next = tail;
        tail->prev = head;

        this->capacity = capacity;
    }

    int get(int key) {
        if (!cache.count(key)) return -1;
        Node* node = cache[key];
        moveNodeToHead(node);
        return node->value;
    }

    void moveNodeToHead(Node* node) {
        // 1. 删除节点 node
        removeNode(node);

        // 2. 将节点 node 添加到表头
        addNodeToHead(node);
    }

    void removeNode(Node* node) {
        Node* preNode = node->prev;
        Node* nextNode = node->next;

        preNode->next = nextNode;
        nextNode->prev = preNode;

        node->prev = NULL;
        node->next = NULL;
    }

    void addNodeToHead(Node* node) {
        node->next = head->next;
        head->next->prev = node;

        head->next = node;
        node->prev = head;
    }

    void put(int key, int value) {
        if (!cache.count(key)) {
            if (cache.size() == capacity) {
                Node* delNode = removeTailNode();
                cache.erase(delNode->key);
            }

            Node* node = new Node();
            node->key = key;
            node->value = value;

            cache[key] = node;
            addNodeToHead(node);
        } else {
            Node* node = cache[key];
            node->value = value;
            moveNodeToHead(node);
        }
    }

    Node* removeTailNode() {
        Node* delNode = tail->prev;
        removeNode(delNode);
        return delNode;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */