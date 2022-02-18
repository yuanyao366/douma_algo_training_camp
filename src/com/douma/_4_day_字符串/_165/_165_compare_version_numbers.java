package com.douma._4_day_字符串._165;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _165_compare_version_numbers {
    // 不使用内置函数
    public int compareVersion(String version1, String version2) {
        int i1 = 0, i2 = 0;
        while (i1 < version1.length() || i2 < version2.length()) {
            int v1 = 0, v2 = 0;
            while (i1 < version1.length() && version1.charAt(i1) != '.') {
                v1 = v1 * 10 + (version1.charAt(i1) - '0');
                i1++;
            }
            // bug 修复：这里处理的是 version2
            while (i2 < version2.length() && version2.charAt(i2) != '.') {
                v2 = v2 * 10 + (version2.charAt(i2) - '0');
                i2++;
            }

            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
            i1++;
            i2++;
        }
        return 0;
    }
    // 使用内置函数
    public int compareVersion1(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");

        int n1 = nums1.length;
        int n2 = nums2.length;

        int v1, v2;
        for (int i = 0; i < Math.max(n1, n2); i++) {
            v1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            v2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;

            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
        }

        return 0;
    }
}
