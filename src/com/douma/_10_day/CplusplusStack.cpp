public:
    int main(int argc, char* argv[]) {
        stack<int> st;
        st.push(2);
        st.push(3);
        // 拿到栈顶元素
        int topElement = st.top();
        // 判断栈是否为空
        bool isEmpty = st.empty();
        // 弹出栈顶元素，但是不会返回栈顶元素
        st.pop();

        // 数组实现栈功能
        vector<int> s;
        s.push_back(2);
        s.push_back(3);
        // 拿到栈顶元素
        int topElement = s[s.size() - 1];
        // 判断栈是否为空
        bool isEmpty = s.empty();
        // 弹出栈顶元素，但是不会返回栈顶元素
        s.pop_back();
    }