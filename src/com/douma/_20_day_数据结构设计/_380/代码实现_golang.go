type RandomizedSet struct {
    idxMap map[int]int
    nums []int
}


/** Initialize your data structure here. */
func Constructor() RandomizedSet {
    return RandomizedSet{idxMap:make(map[int]int), nums:[]int{}}
}


/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
func (this *RandomizedSet) Insert(val int) bool {
    if _, ok := this.idxMap[val]; ok {
        return false
    }
    this.idxMap[val] = len(this.nums)
    this.nums = append(this.nums, val)
    return true
}


/** Removes a value from the set. Returns true if the set contained the specified element. */
func (this *RandomizedSet) Remove(val int) bool {
    if _, ok := this.idxMap[val]; !ok {
        return false
    }
    // 拿到需要删除的元素在列表中的索引位置
    var index = this.idxMap[val]

    // 将列表中的最后一个元素覆盖掉需要删除的元素
    var lastNum = this.nums[len(this.nums) - 1]
    this.nums[index] =lastNum

    // 更新最后一个元素的索引
    this.idxMap[lastNum] = index

    // 删除数组列表中的最后一个元素
    this.nums = this.nums[:len(this.nums) - 1]

    // 从 map 中删除指定的元素
    delete(this.idxMap, val)

    return true
}


/** Get a random element from the set. */
func (this *RandomizedSet) GetRandom() int {
    if len(this.nums) == 0 {
        return -1
    }
    return this.nums[rand.Intn(len(this.nums))]
}


/**
 * Your RandomizedSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */