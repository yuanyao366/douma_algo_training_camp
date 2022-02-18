public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int i = 0;
        int len = flowerbed.size();
        while (i < len && n > 0) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == len - 1 || flowerbed[i + 1] == 0) {
                // 注意：必须将 i == flowerbed.length - 1 放在前面， 否则 i + 1 可能会越界
                n--;
                i += 2;
            } else {
                i += 3;
            }
        }
        return n <= 0;
    }