/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3323/
May Leetcoding challenge, day 8

1. Sort by x coordinate (if not already sorted)
2. check slope between coordinates[0] and coordinates[1]
  - if this slope is steadily mainteined between every 2 neighboring pairs
    then it is a straight line
  - if it is ever broken, then it is not a straight line

0 ms, faster than 100%
39.3 mb, less than 100%
*/
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        double startingSlope = getSlope(coordinates[0], coordinates[1]);
        double slope;
        boolean startingSlopeBroken = false;
        int i = 1;
        
        while(!startingSlopeBroken && i < coordinates.length - 1)
        {
            slope = getSlope(coordinates[i], coordinates[i+1]);
            
            if(slope != startingSlope)
                startingSlopeBroken = true;
            
            i++;
        }
        
        return !startingSlopeBroken;
    }
    
    public double getSlope(int[] coor1, int[] coor2) 
    {
        // (y2 - y1) / (x2 - x1), as a double
        double ans = (double) (coor2[1] - coor1[1]) / (coor2[0] - coor1[0]);
        
        if(ans == Double.NEGATIVE_INFINITY)
            ans = Double.POSITIVE_INFINITY;
        
        return ans;
    }
}
