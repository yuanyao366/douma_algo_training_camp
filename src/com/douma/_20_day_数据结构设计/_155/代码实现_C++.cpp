// 1. 双栈实现
class MinStack1 {
private:
    stack<int> dataStack;
    stack<int> minStack;

public:
    /** initialize your data structure here. */
    MinStack1() {

    }
    
    void push(int val) {
        dataStack.push(val);

        // bug 修复：视频中少了 = ，= 号是需要加上的
        // 如果去掉等于的话，可能会出现 dataStack 不为空，但是 minStack 为空了
        // 这样下面的 getMin 就会出现异常了
        if (minStack.empty() || val <= minStack.top()) {
            minStack.push(val);
        }
    }
    
    void pop() {
        if (dataStack.top() == minStack.top()) {
            minStack.pop();
        }
        dataStack.pop();
    }
    
    int top() {
        return dataStack.top();
    }
    
    int getMin() {
        return minStack.top();
    }
};

class Node {
public:
    int val, min;
    // 这个属性在方法 3 中使用
    Node* next = NULL;

    Node() {

    }

    Node(int val, int min) {
        this->val = val;
        this->min = min;
    }
};

// 2. 单栈 + 每个元素记住当前为止的最小值
class MinStack2 {
private:
    stack<Node*> s;

public:
    /** initialize your data structure here. */
    MinStack2() {

    }
    
    void push(int val) {
        Node* node = new Node();
        node->val = val;
        int min = val;
        if (!s.empty() && s.top()->min < val) {
            min = s.top()->min;
        }
        node->min = min;
        s.push(node);
    }
    
    void pop() {
        s.pop();
    }
    
    int top() {
        return s.top()->val;
    }
    
    int getMin() {
        return s.top()->min;
    }
};

// 方法 3. 单链表实现(表头作为栈顶)
class MinStack {
private:
    Node* dummyNode;

public:
    /** initialize your data structure here. */
    MinStack() {
        dummyNode = new Node();
    }
    
    void push(int val) {
        Node* head = dummyNode->next;
        int min = val;
        if (head && head->min < val) {
            min = head->min;
        }
        Node* node = new Node(val, min);
        node->next = dummyNode->next;
        dummyNode->next = node;
    }
    
    void pop() {
        Node* head = dummyNode->next;
        if (head) {
            dummyNode->next = head->next;
            head->next = NULL;
        }
    }
    
    int top() {
        return dummyNode->next->val;
    }
    
    int getMin() {
        return dummyNode->next->min;
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */