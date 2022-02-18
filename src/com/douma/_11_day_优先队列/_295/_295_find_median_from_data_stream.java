package com.douma._11_day_优先队列._295;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _295_find_median_from_data_stream {
    /* leetcode 295 号算法题：数据流的中位数

    中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
    例如，
    [2,3,4] 的中位数是 3
    [2,3] 的中位数是 (2 + 3) / 2 = 2.5
    设计一个支持以下两种操作的数据结构：
         void addNum(int num) - 从数据流中添加一个整数到数据结构中。
         double findMedian() - 返回目前所有元素的中位数。

    进阶：
    1. 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
    2. 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
     */
}

// 1. 普通排序
class MedianFinder1 {
    private List<Integer> data;
    /** initialize your data structure here. */
    public MedianFinder1() {
        data = new ArrayList<>();
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：O(1)
    public void addNum(int num) {
        data.add(num);
    }

    // 返回目前所有元素的中位数。
    // 时间复杂度：O(nlogn)
    public double findMedian() {
        Collections.sort(data);
        int n = data.size();
        if (n % 2 == 1) {
            return data.get(n / 2);
        } else {
            return (data.get((n - 1) / 2) + data.get(n / 2)) * 0.5;
        }
    }
}

// 2. 插入排序
class MedianFinder2 {
    private List<Integer> data;
    /** initialize your data structure here. */
    public MedianFinder2() {
        data = new ArrayList<>();
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：O(n)
    public void addNum(int num) {
        if (data.isEmpty()) {
            data.add(num);
        } else {
            // 插入排序
            // bug 修复：需要先记住之前的数组的长度
            int n = data.size();
            data.add(Integer.MIN_VALUE);
            int j = n;
            for (; j > 0; j--) {
                if (num < data.get(j - 1)) {
                    data.set(j, data.get(j - 1));
                } else {
                    break;
                }
            }
            data.set(j, num);
        }
    }

    // 返回目前所有元素的中位数。
    // 时间复杂度：O(1)
    public double findMedian() {
        int n = data.size();
        if (n % 2 == 1) {
            return data.get(n / 2);
        } else {
            return (data.get((n - 1) / 2) + data.get(n / 2)) * 0.5;
        }
    }
}

// 3. 大顶堆 + 小顶堆
class MedianFinder3 {
    // 大顶堆用于存储较小的一半元素
    private PriorityQueue<Integer> maxHeap;
    // 小顶堆用于存储较大的一半元素
    private PriorityQueue<Integer> minHeap;

    // 注意：如果元素的个数是奇数的话，那么大顶堆中的元素个数比小顶堆中元素个数多 1

    /** initialize your data structure here. */
    public MedianFinder3() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：log(n)
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    // 返回目前所有元素的中位数。
    // 时间复杂度：O(1)
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }
}

// 4. 红黑树
class MedianFinder4 {
    private TreeSet<int[]> data;
    private Comparator<int[]> customComparator;
    // index 用于记录数据流中每个元素的位置
    int index;

    // lower 表示两个中间元素小的那个
    // lower 表示两个中间元素大的那个
    /*
     比如元素：1,2,3,4,5
     其中 lower = upper = [3,2] --> 数组中第一个元素是元素值 3，第二个元素是元素值 3 在数据流中的索引位置

     比如元素：1,2,3,4,5,6
     其中 lower = [3,2]
         upper = [4,3]
     */
    private int[] lower, upper;

    // 注意：如果元素的个数是奇数的话，那么大顶堆中的元素个数比小顶堆中元素个数多 1

    /** initialize your data structure here. */
    public MedianFinder4() {
        // 自定义比较器
        customComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            }
        };
        data = new TreeSet<>(customComparator);
        lower = null;
        upper = null;
        index = 0;
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：log(n)
    public void addNum(int num) {
        // 记录当前的元素值及其位置
        int[] curr = new int[]{num, index++};
        data.add(curr);

        if (data.size() == 1) {
            lower = data.first();
            upper = data.first();
        } else if (customComparator.compare(lower, upper) == 0) {
            // lower 和 upper 相等的话，说明上一次数据流中有奇数个元素
            // 现在需要处理进来的元素，这个时候元素的个数变成了偶数个了
            if (customComparator.compare(curr, lower) < 0) {
                lower = data.lower(lower);
            } else {
                upper = data.higher(upper);
            }
        } else {
            // lower 和 upper 不相等的话，说明上一次数据流中有偶数个元素
            // 现在需要处理进来的元素，这个时候元素的个数变成了奇数个了
            if (customComparator.compare(curr, lower) < 0) {
                upper = lower;
            } else if (customComparator.compare(curr, upper) > 0) {
                lower = upper;
            } else {
                lower = curr;
                upper = curr;
            }
        }
    }

    // 返回目前所有元素的中位数。
    // 时间复杂度：O(1)
    public double findMedian() {
        if (lower == null) {
            // 防止没有添加数据，然后调用这个方法的情况
            return 0.0;
        }
        return (lower[0] + upper[0]) * 0.5;
    }
}

// 进阶 1：如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
// 4. 计数排序
class MedianFinder5 {
    private int[] count;
    private int size;

    /** initialize your data structure here. */
    public MedianFinder5() {
        this.count = new int[101];
        this.size = 0;
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：O(1)
    public void addNum(int num) {
        count[num]++;
        size++;
    }

    // 返回目前所有元素的中位数。
    // 时间复杂度：O(101) -> O(1)
    public double findMedian() {
        // cnt 表示到当前数字为止，一共出现的数字的个数
        // prevCnt 表示到前一个数字为止，一共出现的数字的个数
        int cnt = 0, prevCnt = 0;
        boolean isEven = size % 2 == 0;

        int lastNonZero = 0;
        int lower = 0;
        int upper = 0;
        for (int num = 0; num < 101; num++) {
            cnt += count[num];
            if (cnt >= size / 2 + 1) {
                upper = num;
                // bug 修复：偶数并且中间两个元素不是同一个元素
                // 比如数组 [1,2,2,2,2,2,2,8]，这个时候 upper = num = 2
                // 按照之前的逻辑的话，因为是偶数，所以 upper = num, lower = lastNonZero，也就是 upper = 2, lower = 1，这是不对的
                // 上面之所以不对，是因为中间的两个元素都是 2，所以不能单纯的考虑是偶数， 还需要加上 prevCnt + 1 == cnt
                // 因为只有 prevCnt + 1 == cnt 就可以确定中间两个元素肯定不是相同的

                // 比如数组 [1,1,2,3,4,5] 这个时候 upper = num = 3
                // cnt = 4(到当前数字 3 为止，一共出现了 4 个数字)
                // prevCnt = 3(到前一个数字 2 为止，一共出现了 3 个数字)
                // 这个时候 prevCnt + 1 == cnt，表明中间的两个数字不相同

                // 再比如数组 [1,2,2,2,2,2,2,8]，这个时候 upper = num = 2
                // cnt = 7(到当前数字 2 为止，一共出现了 7 个数字)
                // prevCnt = 1(到前一个数字 1 为止，一共出现了 1 个数字)
                // 这个时候 prevCnt + 1 != cnt，表明中间的两个数字相同，既然相同的话，那么 lower 和 upper 相等
                if (isEven && prevCnt + 1 == cnt) {
                    lower = lastNonZero;
                } else {
                    lower = upper;
                }
                break;
            }
            // bug 修复：设置最后一个出现的数字
            if (count[num] > 0) lastNonZero = num;
            prevCnt = cnt;
        }
        return (lower + upper) * 0.5;
    }

    public static void main(String[] args) {
        MedianFinder5 medianFinder5 = new MedianFinder5();
        medianFinder5.addNum(1);
        System.out.println(medianFinder5.findMedian());
        medianFinder5.addNum(2);
        //medianFinder5.addNum(3);
        //medianFinder5.addNum(8);
        System.out.println(medianFinder5.findMedian());
    }
}


// 进阶 2：如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
// . 计数排序
class MedianFinder {
    private int[] count;
    private int size;

    private int countLessZero;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.count = new int[101];
        this.size = 0;
        this.countLessZero = 0;
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：O(1)
    public void addNum(int num) {
        if (num < 0) countLessZero++;
        else if (num >=0 && num <= 100) count[num]++;
        size++;
    }

    // 返回目前所有元素的中位数。
    // 时间复杂度：O(101) -> O(1)
    public double findMedian() {
        int cnt = countLessZero;
        boolean isEven = size % 2 == 0;

        int lastNonZero = 0;
        int lower = 0;
        int upper = 0;
        for (int num = 0; num < 101; num++) {
            if (count[num] > 0) lastNonZero = num;
            cnt += count[num];
            if (cnt >= size / 2 + 1) {
                upper = num;
                if (isEven) {
                    lower = lastNonZero;
                } else {
                    lower = upper;
                }
                break;
            }
        }
        return (lower + upper) * 0.5;
    }

}
