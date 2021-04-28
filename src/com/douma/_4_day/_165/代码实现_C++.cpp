public:
    int compareVersion(string version1, string version2) {
        int i1 = 0, i2 = 0;
        while (i1 < version1.length() || i2 < version2.length()) {
            int v1 = 0, v2 = 0;
            while (i1 < version1.length() && version1[i1] != '.') {
                v1 = v1 * 10 + (version1[i1] - '0');
                i1++;
            }
            while (i2 < version2.length() && version2[i2] != '.') {
                v2 = v2 * 10 + (version2[i2] - '0');
                i2++;
            }

            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
            i1++;
            i2++;
        }
        return 0;
    }