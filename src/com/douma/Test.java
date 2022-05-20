package com.douma;

import java.io.*;

/**
 * @微信公众号 : 抖码课堂
 * @作者 : 老汤
 */
public class Test {

    public static int maxLen(int[] nums) {
        int n = nums.length;

        // isLeftInc[i]：第 i 个元素的左边是否是连续递增的
        boolean[] isLeftInc = new boolean[n + 1];
        isLeftInc[0] = true;
        isLeftInc[1] = true;
        int prev = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > prev) {
                prev = nums[i];
                isLeftInc[i + 1] = true;
            } else {
                break;
            }
        }

        if (isLeftInc[n]) return n;

        // isRightInc[i]：第 i 个元素的右边是否是连续递增的
        boolean[] isRightInc = new boolean[n + 1];
        isRightInc[n] = true;
        isRightInc[n - 1] = true;
        prev = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (prev > nums[i]) {
                prev = nums[i];
                isRightInc[i] = true;
            } else {
                break;
            }
        }

        int res = 0;

        // 枚举所有的子数组
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {

                if (isLeftInc[j]
                        && isRightInc[i + 1]
                        && ((j == 0 || i == n - 1) || nums[j - 1] < nums[i + 1])) {
                    res = Math.max(res, n - (i - j + 1));
                }

            }
        }

        return res;
    }

    public static int maxLen1(int[] nums) {
        int n = nums.length;
        int res = 0;

        // 枚举所有的子数组
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {

                // 判断删除子数组后，是否连续递增
                int prev = Integer.MIN_VALUE;
                boolean isInc = true;
                for (int k = 0; k < n; k++) {
                    if (k >= j && k <= i) continue;
                    if (nums[k] > prev) prev = nums[k];
                    else {
                        isInc = false;
                        break;
                    }
                }

                // 如果连续递增，则更新结果
                if (isInc) res = Math.max(res, n - (i - j + 1));
            }
        }

        return res;
    }


    private static int lines = 0;
    // 统计本项目中代码的行数
    public static void main(String[] args) throws IOException {
        int[] nums = {1, 2, 5, 4, 5, 7, 4};
        System.out.println(maxLen(nums));
    }

    private static void f(File file) throws IOException {
        if (file.isFile()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 去掉空行
                if (line.trim().length() == 0) continue;
                // 去掉注释
                if (line.trim().startsWith("/")
                        || line.trim().startsWith("*")) continue;
                lines++;
            }
        } else {
            File[] files = file.listFiles();
            for (File f : files) f(f);
        }
    }
}
