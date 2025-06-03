/*
https://leetcode.com/problems/product-of-array-except-self/
*/

// LeetCode 30 day challenge, day 15

/*
Create 2 arrays (O(N) + O(N) or O(N) depending on implementation)
- (leftProducts) one holds the product of all the numbers to the left of nums[i]
- (rightProducts) the other holds the product of all the numbers to the right of nums[i]

Create the final answer array
- for each element, multiply the product of the elements to the left of it (leftProducts[i-1])
  with the product of the elements to the right of it (rightProducts[i+1])
- first and last elements will just use one of those arrays
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] leftProducts = Arrays.copyOf(nums, nums.length);
        int[] rightProducts = Arrays.copyOf(nums, nums.length);
        
        int last = nums.length - 1;
        
        for(int i = 1; i < nums.length - 1; i++)
        {
            leftProducts[i] = leftProducts[i-1] * leftProducts[i];
            
            rightProducts[last - i] = rightProducts[last - i + 1] * rightProducts[last - i];
        }
        
        // for each element, multiply the left product with the right product
        int left = 1;
        int right = 1;
        
        for(int i = 0; i < nums.length; i++)
        {
            if(i == 0)
                left = 1;
            else
                left = leftProducts[i-1];
            
            if(i == nums.length - 1)
                right = 1;
            else
                right = rightProducts[i+1];
            
            nums[i] = left * right;
        }
        
        return nums;
    }
}
