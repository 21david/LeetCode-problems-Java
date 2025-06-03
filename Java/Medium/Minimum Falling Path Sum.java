//  https://leetcode.com/problems/minimum-falling-path-sum/

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        // 3 ms, faster than 81.71%
        // 39.9 mb, less than 17.68%
        
        /*
        I thought of an O(N*N) approach. (N is the # of rows, or columns)
        
        We can make another matrix of the same size, but this
        matrix will store the shortest sum to the bottom at
        each location. So, we can set the bottom row to the same
        as in the input matrix, and we can start on the second-last
        row. For each element, we compare it with the 2 or 3
        elements that it can go down to, we pick the minimum
        of the 2 or 3, and we sum the current element's value to it
        and that's the value that we will store in this new matrix.
        As we proceed, we will visit every element in the matrix
        one time, and make 2-3 comparisons, so this should be
        O(N*N) * O(1), which is O(N*N) time complexity.
        The space complexity would be O(N*N) as well since we
        make another matrix.        
        */
        
        int[][] lowestSum = new int[matrix.length][matrix[0].length];
        
        // copy last row
        for(int c = 0; c < matrix[0].length; c++)
            lowestSum[matrix.length-1][c] = matrix[matrix.length-1][c];
        
        int min = Integer.MAX_VALUE;
        // start finding the lowest sum values, starting from 2nd last row
        for(int r = matrix.length - 2; r >= 0; r--) {
            for(int c = 0; c < matrix[0].length; c++) {
                // for each location, it is either a left-most element,
                // a middle element, or a right-most element
                if(c == 0) {
                    // check the 2 adjacent cells underneath
                    min = lowestSum[r+1][c];
                    min = Math.min(min, lowestSum[r+1][c+1]);
                }
                else if(c == matrix[0].length-1) {
                    // check the 2 adjacent cells underneath
                    min = lowestSum[r+1][c];
                    min = Math.min(min, lowestSum[r+1][c-1]);
                    
                }
                else {
                    // check the 3 adjacent cells underneath
                    min = lowestSum[r+1][c];
                    min = Math.min(min, lowestSum[r+1][c-1]);
                    min = Math.min(min, lowestSum[r+1][c+1]);
                    
                }
                
                // at this point, we should know the minimum
                // value of the 2 or 3 cells underneath
                lowestSum[r][c] = matrix[r][c] + min;
            }
        }
        
        // now we have to find the minimum value on the top row
        // of lowestSum matrix, as that is our answer
        int ans = lowestSum[0][0];
        for(int i = 1; i < lowestSum[0].length; i++)
            ans = Math.min(ans, lowestSum[0][i]);
        
        return ans;
    }
}

/*
Sample input:
[[1]]
[[1,2],[0,4]]
[[2,1,3],[6,5,4],[7,8,9]]
[[6,10,2,0,6],[3,50,4,3,1],[1,20,12,0,6],[5,7,2,6,2],[12,6,3,9,10]]
[[10,10,10,10,10,10,10],[10,10,10,10,10,10,10],[10,10,10,10,10,10,10],[10,10,10,10,10,10,10],[10,10,10,7,10,10,10],[10,10,10,10,10,20,20],[9,10,10,10,10,10,3]]

*/
