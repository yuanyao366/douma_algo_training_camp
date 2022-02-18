class KthLargest1 {
public:
    vector<int> data;
    int k;
    KthLargest1(int k, vector<int>& nums) {
        this->k = k;
        this->data = nums;
    }

    // 时间复杂度：O(nlogn)，超时
    int add(int val) {
        data.push_back(val);
        sort(data.begin(), data.end());
        return data[data.size() - k];
    }
};

class KthLargest2 {
public:
    vector<int> data;
    int k;
    KthLargest2(int k, vector<int>& nums) {
        this->k = k;
        this->data = nums;
        sort(data.begin(), data.end());
    }

    // 插入排序
    // 时间复杂度：O(n)
    int add(int val) {
        if (data.empty()) {
            data.push_back(val);
        } else {
            // lower_bound(data.begin(), data.end(), val) 的作用是使用二分查找查找到 val 需要插入的位置
            data.insert(lower_bound(data.begin(), data.end(), val), val);
        }
        return data[data.size() - k];
    }
};

class KthLargest {
public:
    // 小顶堆
    priority_queue<int, vector<int>, greater<int>> pq;
    int k;
    KthLargest(int k, vector<int>& nums) {
        this->k = k;
        for (int num : nums) {
            add(num);
        }
    }

    // 时间复杂度：O(n)
    int add(int val) {
        if (pq.size() < k) {
            pq.push(val);
        } else if (val > pq.top()) {
            pq.pop();
            pq.push(val);
        }
        return pq.top();
    }
};

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest* obj = new KthLargest(k, nums);
 * int param_1 = obj->add(val);
 */