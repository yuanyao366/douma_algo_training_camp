class Solution {
public:
    vector<int> relativeSortArray1(vector<int>& arr1, vector<int>& arr2) {
        unordered_map<int, int> map;
        for (int i = 0; i < arr2.size(); i++) map[arr2[i]] = i;

        sort(arr1.begin(), arr1.end(), [&](int x, int y){
            if (map.count(x)) {
                return map.count(y) ? map[x] < map[y] : true;
            } else {
                return map.count(y) ? false : x < y;
            }
        });

        return arr1;
    }

    vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
        int max = *max_element(arr1.begin(), arr1.end());
        vector<int> count(max + 1);
        for (int num : arr1) count[num]++;

        int index = 0;
        for (int num : arr2) {
            for (int i = 0; i < count[num]; i++) {
                arr1[index++] = num;
            }
            count[num] = 0;
        }

        for (int i = 0; i <= max; i++) {
            for (int k = 0; k < count[i]; k++) {
                arr1[index++] = i;
            }
        }

        return arr1;
    }
};