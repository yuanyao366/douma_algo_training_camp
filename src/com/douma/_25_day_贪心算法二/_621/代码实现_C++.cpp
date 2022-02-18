class Solution {
public:
    // 贪心策略：每次先安排出现次数最多的任务
    int leastInterval(vector<char>& tasks, int n) {
        vector<int> counter(26);
        int maxCount = 0, countMax = 0;
        for (char task : tasks) {
            int index = task - 'A';
            counter[index]++;
            if (maxCount == counter[index]) {
                countMax++;
            } else if (maxCount < counter[index]) {
                maxCount = counter[index];
                countMax = 1;
            }
        }

        int partCount = maxCount - 1;
        int partLength = n - (countMax - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.size() - maxCount * countMax;
        int idle = max(0, emptySlots - availableTasks);

        return tasks.size() + idle;
    }
};