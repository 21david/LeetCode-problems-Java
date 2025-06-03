//  https://leetcode.com/problems/unique-paths-ii/

/*
I remember this as a math problem from a long time ago.
If you start from the end point, you can know easily how many ways
to get to it from the adjacent cells around it (above and to the left).
There would only be 1 way for each, so their value becomes 1.
Then, for the next adjacent cells, we can do the same using the values
of the cells below and to the right. So the value for each cell becomes
the sum of the value of the blocks to the right and below. We can fill out
the end point as 1, and work our way from right-to-left, bottom-to-top,
filling out the value for each cell, until we reach the top-left corner.
Obstacles would be treated as having a value of 0.
*/

// 1 ms, faster than 15.13%
// 38.5 mb, less than 21.27%
// Solved in 38 minutes (with distractions)

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        
        // edge cases
        if(obstacleGrid[rows-1][cols-1] == 1 || obstacleGrid[0][0] == 1) 
            return 0;
        
        int[][] waysToTarget = new int[rows][cols];
        
        for(int r = 0; r < obstacleGrid.length; r++)
            for(int c = 0; c < obstacleGrid[0].length; c++)
                if(obstacleGrid[r][c] == 1) 
                    waysToTarget[r][c] = -1;
        
        // navigate from right-to-left, bottom-to-top, calculating a value
        // for each cell as we go
        // the value will be the sum of the blocks to the right and below
        // if a block is out of bounds, or is -1, then it's value gets treated as 0
        
        int belowVal = 0, rightVal = 0;
        for(int r = rows-1; r >= 0; r--) {
            for(int c = cols-1; c >= 0; c--) {
                if(r == rows-1 && c == cols-1) {  // bottom right corner should be 1
                    waysToTarget[r][c] = 1;
                    continue;
                }
                if(waysToTarget[r][c] == -1)  // skip obstacles
                    continue;
                
                if(outOfBounds(rows, cols, r+1, c) || waysToTarget[r+1][c] == -1) // see what's below
                    belowVal = 0;
                else  // if there is an actual value
                    belowVal = waysToTarget[r+1][c];
                
                if(outOfBounds(rows, cols, r, c+1) || waysToTarget[r][c+1] == -1) // see what's to the right
                    rightVal = 0;
                else  // if there is an actual value
                    rightVal = waysToTarget[r][c+1];
                
                waysToTarget[r][c] = belowVal + rightVal;
            }
        }
        
        return waysToTarget[0][0];
    }
    
    public boolean outOfBounds(int rows, int cols, int r, int c) {
        return r < 0 || c < 0 || r >= rows || c >= cols;
    }
}

/*
Sample input:
[[0,0,0],[0,1,0],[0,0,0]]

[[0,0,0,0,0],[0,0,0,1,0],[0,1,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]

[[0,0,0,0,1],[0,0,0,0,0],[0,0,0,1,1],[0,0,0,0,0],[0,0,0,0,0],[0,1,0,0,0]]

[[0,0],[0,1]]

[[1,0]]
*/
