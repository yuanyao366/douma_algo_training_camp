type RandomizedCollection struct {
    idxMap map[int]map[int]bool
    nums []int
}


/** Initialize your data structure here. */
func Constructor() RandomizedCollection {
    return RandomizedCollection{idxMap:make(map[int]map[int]bool), nums:[]int{}}
}


/** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
func (this *RandomizedCollection) Insert(val int) bool {
    var indexes = this.idxMap[val]
    if indexes == nil {
        indexes = make(map[int]bool)
    }
    indexes[len(this.nums)] = true
    this.idxMap[val] = indexes
    this.nums = append(this.nums, val)
    return len(indexes) == 1
}


/** Removes a value from the collection. Returns true if the collection contained the specified element. */
func (this *RandomizedCollection) Remove(val int) bool {
    var indexes, has = this.idxMap[val]
    if !has {
        return false
    }
    // 拿到需要删除的元素在列表中的索引位置
    var index int
    for id := range indexes {
        index = id
        break
    }

    // 将列表中的最后一个元素覆盖掉需要删除的元素
    var lastNum = this.nums[len(this.nums) - 1]
    this.nums[index] =lastNum

    // 维护删除的元素和最后一个元素的索引
    delete(indexes, index)
    delete(this.idxMap[lastNum], len(this.nums) - 1)
    if index < len(this.nums) - 1 {
        this.idxMap[lastNum][index] = true
    }

    // 删除数组列表中的最后一个元素
    this.nums = this.nums[:len(this.nums) - 1]
    // 从 map 中删除指定的元素
    if len(this.idxMap[val]) == 0 {
        delete(this.idxMap, val)
    }
    return true
}


/** Get a random element from the collection. */
func (this *RandomizedCollection) GetRandom() int {
    if len(this.nums) == 0 {
        return -1
    }
    return this.nums[rand.Intn(len(this.nums))]
}


/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */