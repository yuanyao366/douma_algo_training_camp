public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int i = 0;
        int len = flowerbed.size();
        while (i < len && n > 0) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == len - 1 || flowerbed[i + 1] == 0) {
                // 注意：必须将 i == flowerbed.length - 1 放在前面， 否则 i + 1 可能会越界
                // 这里用到了或运算的特点：或的前面表达式为 false 的话，就不会去指定或的后面的表达式
                n--;
                i += 2;
            } else {
                i += 3;
            }
        }
        return n <= 0;
    }