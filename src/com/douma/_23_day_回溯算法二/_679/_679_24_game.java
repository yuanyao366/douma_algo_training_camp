package com.douma._23_day_回溯算法二._679;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _679_24_game {
    /* 679. 24 点游戏
    你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。

    示例 1:
    输入: [4, 1, 8, 7]
    输出: True
    解释: (8-4) * (7-1) = 24

    示例 2:
    输入: [1, 2, 1, 2]
    输出: False

    注意:
    1. 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
    2. 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。
    例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
    3. 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
     */

    static final int TARGET = 24;
    static final double EPSILON = 1e-6;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    // 回溯
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<Double>();
        for (int num : nums) {
            list.add((double) num);
        }
        return dfs(list);
    }

    private boolean dfs(List<Double> list) {
        if (list.size() == 0) return false;
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> childList = new ArrayList<Double>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            childList.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        // 0 + 1 = 1 + 0，剪枝
                        if (k < 2 && i > j) continue;
                        if (k == ADD) {
                            childList.add(list.get(i) + list.get(j));
                        } else if (k == MULTIPLY) {
                            childList.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            childList.add(list.get(i) - list.get(j));
                        } else if (k == DIVIDE) {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            } else {
                                childList.add(list.get(i) / list.get(j));
                            }
                        }
                        if (dfs(childList)) return true;
                        childList.remove(childList.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
