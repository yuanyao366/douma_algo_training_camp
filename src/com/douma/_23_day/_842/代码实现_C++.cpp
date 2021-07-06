class Solution {
    private String num;
    private int length;
    private List<Integer> res;

    public List<Integer> splitIntoFibonacci(String num) {
        this.num = num;
        this.length = num.length();
        this.res = new ArrayList<>();

        dfs(0, 0, 0);
        return res;
    }

    private boolean dfs(int startIndex, int prevTwoNumSum, int prevNum) {
        if (startIndex == length)
            // 斐波那契数列至少 3 个数
            return res.size() >= 3;

        long currLongNum = 0;
        for (int i = startIndex; i < length; i++) {
            // 如果当前字符是 '0' 的话，则不做处理，因为数字不能以 0 开头
            if (i > startIndex && num.charAt(startIndex) == '0') break;
            // currLongNum = currLongNum * 10 + num.charAt(i) - '0';
            currLongNum = Long.valueOf(num.substring(startIndex, i + 1));
            if (currLongNum > Integer.MAX_VALUE) break;

            int currNum = (int)currLongNum;
            if (res.size() >= 2) {
                if (currNum < prevTwoNumSum) continue;
                else if (currNum > prevTwoNumSum) break;
            }

            res.add(currNum);
            if (dfs(i + 1, prevNum + currNum, currNum)) return true;
            res.remove(res.size() - 1);
        }

        return false;
    }
}