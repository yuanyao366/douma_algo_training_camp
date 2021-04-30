public:
    int countPrimes(int n) {
        int ans = 0;
        vector<bool> notPrimes(n);
        for (int x = 2; x < n; x++) {
            if (notPrimes[x]) continue;
            ans++;
            // 如果 x 是质数，那么 2x、3x、4x.... 肯定不是质数
            for (int i = x; i < n; i += x) {
                notPrimes[i] = true;
            }
        }
        return ans;
    }