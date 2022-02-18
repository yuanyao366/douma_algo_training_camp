public:
    int getSum(int a, int b) {
        while (b != 0) {
            // 当a & b的结果是负数时，左移就会造成符号位的溢出，
            // 所以此处需要转换为unsigned int来避免可能出现的左移越界行为。
            int carry = ((unsigned int)(a & b)) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }