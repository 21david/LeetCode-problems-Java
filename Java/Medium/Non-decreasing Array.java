//  https://leetcode.com/problems/non-decreasing-array/

// 0 ms, faster than 100% (varies with each submission...)
// 40.1 mb, les than 71.54% (varies with each submission...)
// Solved in 32 minutes
class Solution {
    public boolean checkPossibility(int[] nums) {
        int ct = 0;
        int index = 0;
        
        for(int i = 0; i < nums.length-1; i++) {
            if(nums[i] > nums[i+1]) {  // if we found a dip
                if(ct == 1)
                    return false;  // can't have 2 dips
                ct++;
                index = i;
            }
        }
        
        if(ct == 0)
          return true;
        if(index == 0)
            return true;
        if(index == nums.length - 2)  // we could change the last element
            return true;
        if(nums[index-1] > nums[index+1]) {
            if(index < nums.length - 2 && nums[index] <= nums[index+2])  // edge case [5, 7, 1, 8]
                return true;
            return false;
        }
        
        return true;
    }
}

/*
Sample input:
[4,2,3]
[4,2,1]
[1,2,3,6,4,5,6,7]
[1,2,3,4,6,5,6,8,7,9]
[1,1,1,1,6,1,1,1]
[1,1,1,5,1,1,1,1,5]
[1,1,1,1,4,1,1,1,1,4,1]
[3,4,2,3]
[5,6,7,8,1,2,3,4]
[5,1,2,3,4,5,6,7]
[1,2,3,4,5,7,6]
[4,5,6,7,8,9,6]
[5,7,1,8]
*/
