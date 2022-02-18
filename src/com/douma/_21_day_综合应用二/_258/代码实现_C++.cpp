class Solution {
public:
    int addDigits(int num) {
        while (num >= 10) {
            num = totalSum(num);
        }
        return num;
    }

    int totalSum(int num) {
        int total = 0;
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }
};