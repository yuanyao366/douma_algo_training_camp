class RandomizedCollection {
private:
    unordered_map<int, unordered_set<int>> idxMap;
    vector<int> nums;

public:
    /** Initialize your data structure here. */
    RandomizedCollection() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    bool insert(int val) {
        idxMap[val].insert(nums.size());
        nums.push_back(val);
        return idxMap[val].size() == 1;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    bool remove(int val) {
        if (idxMap.find(val) == idxMap.end()) return false;

        int index = *(idxMap[val].begin());
        int lastNum = nums.back();
        nums[index] = lastNum;

        idxMap[val].erase(index);
        idxMap[lastNum].erase(nums.size() - 1);
        if (index < nums.size() - 1) {
            idxMap[lastNum].insert(index);
        }

        nums.pop_back();
        if (idxMap[val].size() == 0) idxMap.erase(val);
        return true;
    }

    /** Get a random element from the set. */
    int getRandom() {
        return nums[rand() % nums.size()];
    }
};

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection* obj = new RandomizedCollection();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */