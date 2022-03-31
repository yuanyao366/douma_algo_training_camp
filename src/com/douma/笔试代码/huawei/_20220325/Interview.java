package com.douma.笔试代码.huawei._20220325;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 */
public class Interview {

    //分别为面试官数、受试者数、面试最高次数
    static int M, N, k;

    // 每个应聘者所有的组合 （2 个） 面试官
    static List<int[]>[] interviewee;

    // 记录每个面试官的面试次数
    static int[] interviewCnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        k = sc.nextInt();
        sc.nextLine();

        // 1. 记录每门语言所有的面试官，key 是语言，value 是会这门语言的面试官
        Map<String, List<Integer>> lgInterviewers = new HashMap<>();
        for (int i = 0; i < M; i++) {
            String[] lgs = sc.nextLine().split(" ");
            for (String lg : lgs) {
                if (lgInterviewers.containsKey(lg)) {
                    lgInterviewers.get(lg).add(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    lgInterviewers.put(lg, list);
                }
            }
        }

        // 2. 计算每门语言所有的组合面试官，key 是语言，value 是所有的组合 (2 个) 面试官
        // 时间复杂度：O(NM^2)
        Map<String, List<int[]>> interviewerPartners = new HashMap<>();
        for (String lg : lgInterviewers.keySet()) {
            List<Integer> allInterviewers = lgInterviewers.get(lg);
            List<int[]> allPartners = new ArrayList<>();
            for (int i = 0; i < allInterviewers.size(); i++) {
                for (int j = i + 1; j < allInterviewers.size(); j++) {
                    allPartners.add(new int[]{allInterviewers.get(i), allInterviewers.get(j)});
                }
            }
            if (!allPartners.isEmpty())
                interviewerPartners.put(lg, allPartners);
        }

        // 3. 记录每个应聘者所有的组合面试官
        interviewee = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            String lg = sc.nextLine();
            // 如果这个应聘者应聘的语言都没有合适的两个面试官组合，那么提前退出
            if (!interviewerPartners.containsKey(lg)) {
                System.out.println(false);
                return;
            }
            interviewee[i] = interviewerPartners.get(lg);
        }

        // 用于记录每个面试官面试的次数
        interviewCnt = new int[M];

        // 给每一个应聘者选择面试官组合
        dfs(0);
    }

    private static void dfs(int index) {
        if (index == N) { // 应聘者选择完了面试官组合

            // 如果有的面试官面试的次数大于 k 次了，那么打印 false
            for (int i = 0; i < M; i++) {
                if (interviewCnt[i] > k) {
                    System.out.println(false);
                    return;
                }
            }
            System.out.println(true);
            return;
        }

        // 枚举当前应聘者可以选择所有的面试官组合
        for (int i = 0; i < interviewee[index].size(); i++) {
            int[] ip = interviewee[index].get(i);
            // 如果面试官面试的机会超过 k 了，那么剪枝
            if (interviewCnt[ip[0]] == k || interviewCnt[ip[1]] == k) break;

            interviewCnt[ip[0]]++;
            interviewCnt[ip[1]]++;

            // 枚举下一个应聘者可以选择的所有的面试官组合
            dfs(index + 1);

            interviewCnt[ip[0]]--;
            interviewCnt[ip[1]]--;
        }
    }

}
