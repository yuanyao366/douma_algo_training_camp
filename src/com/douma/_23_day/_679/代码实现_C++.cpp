class Solution {
private:
    int TARGET = 24;
    double EPSILON = 1e-6;
    int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

public:
    bool judgePoint24(vector<int>& cards) {
        vector<double> list;
        for (int card : cards) {
            list.push_back((double)card);
        }

        return dfs(list);
    }

    bool dfs(vector<double> list) {
        if (list.size() == 0) return false;
        if (list.size() == 1) {
            return abs(list[0] - TARGET) < EPSILON;
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    vector<double> childList;
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) childList.push_back(list[k]);
                    }

                    for (int k = 0; k < 4; k++) {
                        // 0 + 1 = 1 + 0，剪枝
                        if (k < 2 && i > j) continue;
                        if (k == ADD) {
                            childList.push_back(list[i] + list[j]);
                        } else if (k == MULTIPLY) {
                            childList.push_back(list[i] * list[j]);
                        } else if (k == SUBTRACT) {
                            childList.push_back(list[i] - list[j]);
                        } else if (k == DIVIDE) {
                            if (abs(list[i]) < EPSILON) {
                                continue;
                            } else {
                                childList.push_back(list[i] / list[j]);
                            }
                        }
                        if (dfs(childList)) return true;
                        childList.pop_back();
                    }
                }
            }
        }

        return false;
    }
};