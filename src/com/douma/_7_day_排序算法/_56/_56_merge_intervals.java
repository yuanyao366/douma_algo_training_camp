package com.douma._7_day_排序算法._56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _56_merge_intervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][2];

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        List<int[]> outputs = new ArrayList<>();
        outputs.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] lastInterval = outputs.get(outputs.size() - 1);
            int currLeft = intervals[i][0];
            int currRight = intervals[i][1];
            if (lastInterval[1] < currLeft) {
                outputs.add(intervals[i]);
            } else {
                lastInterval[1] = Math.max(lastInterval[1], currRight);
            }
        }
        return outputs.toArray(new int[outputs.size()][]);
    }
}
