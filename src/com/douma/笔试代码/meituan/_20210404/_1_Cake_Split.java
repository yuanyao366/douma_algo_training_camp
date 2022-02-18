package com.douma.笔试代码.meituan._20210404;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _1_Cake_Split {
    // issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4BZ4A
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行是切的刀数
        int n = Integer.parseInt(scanner.nextLine());

        // 记录经度的度数是否被切(经度作为数组的索引)，就是竖着切
        boolean[] longitudes = new boolean[365];
        // 记录纬度的度数是否被切(纬度作为数组的索引)，就是横着切
        boolean[] latitudes = new boolean[365];

        // 分别记录竖着切和横着切的次数
        int lngCnt = 0, latCnt = 0;

        // 接下来是 n 刀切的情况
        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split(" ");
            if (data[0].equals("1")) { // 竖着切
                int lng = Integer.parseInt(data[1]);
                if (!longitudes[lng]) { // 去重，如果已经切了的话，就不用计算了
                    lngCnt++;
                    longitudes[lng] = true;
                }
            } else if (data[0].equals("0")) { // 横着切
                int lat = Integer.parseInt(data[1]);
                if (!latitudes[lat]) {
                    lngCnt++;
                    latitudes[lat] = true;
                }
            }
        }

        if (lngCnt == 0) {
            System.out.println(latCnt + 1);
        } else {
            System.out.println((lngCnt * 2) * (latCnt + 1));
        }
    }

}
