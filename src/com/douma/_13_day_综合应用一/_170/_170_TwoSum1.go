type TwoSum struct {
    nums []int
    isSorted bool
}


func Constructor() TwoSum {
    nums := make([]int, 0)
    return TwoSum{nums:nums, isSorted:false}
}


func (this *TwoSum) Add(number int)  {
    this.nums = append(this.nums, number)
    this.isSorted = false
}


func (this *TwoSum) Find(value int) bool {
    if !this.isSorted {
        sort.Ints(this.nums)
        this.isSorted = true
    }

    var left, right = 0, len(this.nums) - 1
    for left < right {
        var sum = this.nums[left] + this.nums[right]
        if sum == value {
            return true
        } else if sum < value {
            left++
        } else {
            right--
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