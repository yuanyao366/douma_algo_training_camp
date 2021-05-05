public:
    int findComplement(int num) {
        unsigned mask = ~0;
        while ((num & mask) > 0) mask <<= 1;
        return ~mask ^ num;
    }