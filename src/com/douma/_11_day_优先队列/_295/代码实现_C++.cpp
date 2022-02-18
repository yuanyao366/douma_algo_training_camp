// 1. 普通排序
class MedianFinder1 {
public:
    vector<int> data;
    /** initialize your data structure here. */
    MedianFinder1() {

    }
    
    void addNum(int num) {
        data.push_back(num);
    }
    // 时间复杂度：O(nlogn)
    double findMedian() {
        sort(data.begin(), data.end());
        int n = data.size();
        if (n % 2 == 1) {
            return data[n / 2];
        } else {
            return (data[n / 2 - 1] + data[n / 2]) * 0.5;
        }
    }
};

// 2. 插入排序
class MedianFinder2 {
public:
    vector<int> data;
    /** initialize your data structure here. */
    MedianFinder2() {

    }
    
     // 时间复杂度：O(n)
    void addNum(int num) {
        if (data.empty()) {
            data.push_back(num);
        } else {
            // lower_bound(data.begin(), data.end(), num) 的作用是使用二分查找查找到 num 需要插入的位置
            data.insert(lower_bound(data.begin(), data.end(), num), num);
        }
    }
   
    double findMedian() {
        int n = data.size();
        if (n % 2 == 1) {
            return data[n / 2];
        } else {
            return (data[n / 2 - 1] + data[n / 2]) * 0.5;
        }
    }
};

// 3. 大顶堆 + 小顶堆
class MedianFinder3 {
public:
    // 小顶堆
    priority_queue<int, vector<int>, greater<int>> minHeap;
    // 大顶堆
    priority_queue<int> maxHeap;

    /** initialize your data structure here. */
    MedianFinder3() {

    }
    
     // 时间复杂度：O(logn)
    void addNum(int num) {
        if (maxHeap.empty()) {
            maxHeap.push(num);
            return;
        }

        if (num <= maxHeap.top()) {
            maxHeap.push(num);
        } else {
            minHeap.push(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.push(maxHeap.top());
            maxHeap.pop();
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.push(minHeap.top());
            minHeap.pop();
        }
    }
   
    double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.top();
        } else {
            return (maxHeap.top() + minHeap.top()) * 0.5;
        }
    }
};

// 4. 二叉查找树
class MedianFinder {
public:
    multiset<int> data;
    multiset<int>::iterator lower, upper;
    /** initialize your data structure here. */
    MedianFinder() {

    }
    
     // 时间复杂度：O(logn)
    void addNum(int num) {
        const size_t n = data.size();
        data.insert(num);
        if (!n) {
            lower = upper = data.begin();
        } else if (n & 1) {
            if (num < *lower) {
                lower--;
            } else {
                upper++;
            }
        } else {
            if (num <= *lower) {
                upper--;
                lower = upper;
            } else if (num >= *upper) {
                lower++;
                upper = lower;
            } else {
                lower++;
                upper--;
            }
        }
    }
   
    double findMedian() {
        return (*lower + *upper) * 0.5;
    }
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */