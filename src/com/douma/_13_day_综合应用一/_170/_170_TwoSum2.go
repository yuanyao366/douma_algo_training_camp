type TwoSum struct {
    nums map[int]int
}


func Constructor() TwoSum {
    nums := make(map[int]int)
    return TwoSum{nums:nums}
}


func (this *TwoSum) Add(number int)  {
    this.nums[number]++
}


func (this *TwoSum) Find(value int) bool {
    for key, _ := range this.nums {
        var target = value - key
        if target == key && this.nums[target] > 1 {
            return true
        }
        if target != key && this.nums[target] > 0 {
            return true
        }
    }

    return false
}


/**
 * Your TwoSum object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(number);
 * param_2 := obj.Find(value);
 */