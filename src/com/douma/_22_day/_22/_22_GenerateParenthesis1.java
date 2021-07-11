package com.douma._22_day._22;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _22_GenerateParenthesis1 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(n, "", res);
        return res;
    }

    private void dfs(int n, String path, List<String> res) {
        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }

        dfs(n, path + "(", res);
        dfs(n, path + ")", res);
    }

    public static void main(String[] args) {
        System.out.println(new _22_GenerateParenthesis1().generateParenthesis(2));
    }
}