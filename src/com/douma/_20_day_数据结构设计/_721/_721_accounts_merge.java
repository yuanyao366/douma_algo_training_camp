package com.douma._20_day_数据结构设计._721;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _721_accounts_merge {
    /* 721. 账户合并
    给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，
    其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。

    现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
    请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
    一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。

    合并账户后，按以下格式返回账户：
        每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。
    账户本身可以以任意顺序返回。

    输入：
    accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                ["John", "johnnybravo@mail.com"],
                ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                ["Mary", "mary@mail.com"]]
    输出：
    [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
     ["John", "johnnybravo@mail.com"],
     ["Mary", "mary@mail.com"]]
    解释：
    第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
    第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
    可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
    ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
     */


    /*
       accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                   ["John", "johnnybravo@mail.com"],
                   ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                   ["Mary", "mary@mail.com"]]
   */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        /*
            emailToIndex = {"johnsmith@mail.com" -> 0, "john00@mail.com" -> 1, "johnnybravo@mail.com" -> 2
                            "john_newyork@mail.com" -> 3, "mary@mail.com" -> 4}
        */
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();
        /*
            emailToName = {"johnsmith@mail.com" -> "John", "john00@mail.com" -> "John", "johnnybravo@mail.com" -> "John"
                            "john_newyork@mail.com" -> "John", "mary@mail.com" -> "Mary"}
        */
        Map<String, String> emailToName = new HashMap<String, String>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailsCount++);
                    emailToName.put(email, name);
                }
            }
        }

        /*
            将同一个人的邮箱对应的 index 进行合并
            [0, 1, 3]、[2]、[4]
        */
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.unionElement(firstIndex, nextIndex);
            }
        }
        /*
            indexToEmails = {1 -> ["johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                             2 - > ["johnnybravo@mail.com"], 4 -> ["mary@mail.com"]]}
        */
        Map<Integer, List<String>> indexToEmails = new HashMap<Integer, List<String>>();
        for (String email : emailToIndex.keySet()) {
            int index = emailToIndex.get(email);
            int indexRoot = uf.find(index);
            List<String> account = indexToEmails.getOrDefault(indexRoot, new ArrayList<>());
            account.add(email);
            // bug 修复：这里的 key 应该是 indexRoot
            indexToEmails.put(indexRoot, account);
        }

        /*
            merged = [["John", "johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                      ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
        */
        List<List<String>> merged = new ArrayList<List<String>>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<String>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }

        return merged;
    }
}

class UnionFind {
    // parent[i] 表示的是节点 i 所指向的父亲节点
    private int[] parent;
    private int[] rank;

    public UnionFind(int capacity) {
        parent = new int[capacity];
        rank = new int[capacity];

        // 一开始的时候，每个元素都属于不同的集合
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找元素 p 所属的集合
    public int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        while (p != parent[p]) {
            // 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // 查看 p 和 q 是否属于同一个集合
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并 p 和 q 所属的集合
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}