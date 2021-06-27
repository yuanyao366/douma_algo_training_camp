class RandomizedSet {
private:
    unordered_map<int, int> idxMap;
    vector<int> nums;

public:
    /** Initialize your data structure here. */
    RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    bool insert(int val) {
        if (idxMap.find(val) != idxMap.end()) return false;
        idxMap[val] = nums.size();
        nums.push_back(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    bool remove(int val) {
        if (idxMap.find(val) == idxMap.end()) return false;

        int index = idxMap[val];
        int lastNum = nums.back();
        nums[index] = lastNum;
        idxMap[lastNum] = index;

        nums.pop_back();
        idxMap.erase(val);
        return true;
    }

    /** Get a random element from the set. */
    int getRandom() {
        return nums[rand() % nums.size()];
    }
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */