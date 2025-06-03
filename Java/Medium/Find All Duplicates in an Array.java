//  https://leetcode.com/problems/find-all-duplicates-in-an-array/submissions/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        // 5 ms, faster than 93.04%
        // 48.2 mb, less than 45.93%
        
        // time complexity: O(N)
        // space complexity: O(1)
        
        /* 
        Iterate through the numbers, for each number, take the absolute value 
        and subtract 1, then set that to a variable N
        visit nums[abs(N) - 1], mark it as visited by multiplying by -1,
        and at the end of an iteration, duplicated will have been negated twice,
        so they will be positive.
        If we iterate through the array again in the same exact manner, 
        any number that is positive is a duplicate, so we can add it to a
        list of duplicates. And we need to make sure we multiply by -1 so we
        don't add a duplicate two times to the list of duplicates.
        */
        
        ArrayList<Integer> dups = new ArrayList<>();
        int N;
        
        for(int i = 0; i < nums.length; i++) {
            N = Math.abs(nums[i]) - 1; // this gives us an index somewhere in nums
            nums[N] *= -1;  // mark as visited by multiplying by -1
        }
        
        // Iterate again. Any positive numbers we find will be duplicates
        for(int i = 0; i < nums.length; i++) {
            N = Math.abs(nums[i]) - 1;
            if(nums[N] > 0) {
                dups.add(Math.abs(nums[i]));
                nums[N] *= -1;
            }
        }
        
        return dups;
    }
    
    /*
    public List<Integer> findDuplicates(int[] nums) {
        // 4 ms, faster than 97.92%
        // 48 mb, less than 56.69%
        
        // O(N) runtime complexity
        // O(N) space complexity
        
        ArrayList<Integer> dups = new ArrayList<>();
        int[] multiset = new int[nums.length];
        
        for(int i = 0; i < nums.length; i++) {
            multiset[ nums[i] - 1 ]++;
            if(multiset[ nums[i] - 1 ] == 2)  // found a duplicate
                dups.add(nums[i]);
        }
        
        return dups;
    }
    */
}
