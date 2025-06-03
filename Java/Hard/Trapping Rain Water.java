/*
https://leetcode.com/problems/trapping-rain-water/

I think if we have an array that stores the highest elevation to 
the right of every index, and another array that stores the
highest elevation to the left of every index, we can use the 
three arrays to compute how much water is in each index.
All of these iterations would be O(N).

1 ms, faster than 83.80%
38.9 mb, less than 20.43%
Solved in 18 minutes (watched a solution video)
*/
class Solution {
    public int trap(int[] height) {
        if(height.length <= 2)
            return 0;
        
        int[] maxOnLeft = new int[height.length];
        int[] maxOnRight = new int[height.length];
        
        // Fill up the maxOnLeft array by starting from the left
        // and keeping track of the maximum elevation
        int max = height[0];
        for(int i = 1; i < height.length; i++) {
            maxOnLeft[i] = max;  // assign it the highest elevetion we have found so far
            
            // update the maximum is there is a new maximum
            max = Math.max(max, height[i]);
        }
        
        
        // Fill up the maxOnRight array similarly, starting from the right
        max = height[height.length - 1];
        for(int i = height.length - 2; i >= 0; i--) {
            maxOnRight[i] = max;
            
            max = Math.max(max, height[i]);
        }
        
        // At eaxh index i, the amount of rain is 
        // min(maxOnLeft[i], maxOnRight[i]) - height[i]
        // but if it is a negative number, then it is 0.
        int countRain = 0;
        int computation;
        
        for(int i = 0; i < height.length; i++) {
            computation = Math.min(maxOnLeft[i], maxOnRight[i]) - height[i];
            if(computation > 0)
                countRain += computation;
            else
                continue;
        }
        
        return countRain;
    }
}

/*
Sample input:
[]
[20]
[12,20]
[0,1,0,2,1,0,1,3,2,1,2,1]

*/
