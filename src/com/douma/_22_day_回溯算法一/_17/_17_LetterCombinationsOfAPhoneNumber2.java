package com.douma._22_day_回溯算法一._17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _17_LetterCombinationsOfAPhoneNumber2 {
    private Map<Character, String> phone = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    private List<String> res;

    // “234”
    public List<String> letterCombinations(String digits) {
        // bug 修复：需要先初始化 res，要不然当 digits 为空的时候，返回的就是 null ，而不是空的数组
        res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        findCombination(digits, 0, "");
        return res;
    }

    private void findCombination(String digits, int index, String combination) {
        if (index == digits.length()) {
            res.add(combination);
            return;
        }
        char numChar = digits.charAt(index);
        char[] letters = phone.get(numChar).toCharArray();
        for (char c : letters) {
            findCombination(digits, index + 1, combination + c);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _17_LetterCombinationsOfAPhoneNumber2()
                .letterCombinations("234"));
    }
}
