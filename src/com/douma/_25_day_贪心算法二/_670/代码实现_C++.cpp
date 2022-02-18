class Solution {
public:
    int maximumSwap(int num) {
        // 计算每位上数字出现的最后索引位置
        string strNum = to_string(num);
        vector<int> last(10);
        for (int i = 0; i < strNum.length(); i++) {
            last[strNum[i] - '0'] = i;
        }

        // 从高位到低位遍历
        // 对于每一位，如果后面有比这一位大的数字，则交换，然后返回
        for (int i = 0; i < strNum.length(); i++) {
            for (int d = 9; d > strNum[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = strNum[i];
                    strNum[i] = strNum[last[d]];
                    strNum[last[d]] = tmp;
                    return stoi(strNum);
                }
            }
        }

        return num;
    }
};