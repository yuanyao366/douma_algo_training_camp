package com.douma._0_day;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class Solution2 {
    private static class TestCase {
        int[] nums;
        int target;
        int[] res;
    }

    public static void main(String[] args) throws IOException {

        // 1. 构建测试数据
        List<TestCase> cases = getTestCases();

        for (TestCase testCase : cases) {
            // 2. 调用功能代码
            int[] res = new com.twq.Solution2().twoSum(testCase.nums, testCase.target);
            // 3. 检测结果
            Assert.assertArrayEquals(testCase.res, res);
        }

    }

    // 将测试用例数据放入单独文件中
    private static List<TestCase> getTestCases() throws IOException {
        List<TestCase> cases = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("data/data.txt")));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(" ");
            TestCase testCase = new TestCase();
            testCase.nums = Arrays.stream(data[0].split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
            testCase.target = Integer.parseInt(data[1]);
            testCase.res = Arrays.stream(data[2].split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
        }
        return cases;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{j, i};
                }
            }
        }
        return new int[]{};
    }
}
