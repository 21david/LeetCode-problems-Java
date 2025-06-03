/*
https://leetcode.com/problems/number-of-islands/

LeetCode 30 day challenge, day 17 (week 3)
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/530/week-3/3302/
*/

// 1 ms, faster than 99.96%
// 42 mb, less than 41.86%
class Solution {
    public int numIslands(char[][] matrix) {
        int countIslands = 0;

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] == '1') {
                    countIslands++;
                    floodFill(matrix, r, c);
                }
            }
        }

        return countIslands;
    }

    // post condition: every '1' touching the input '1' will be turned to 0,
    // and every '1' touching those '1's also, etc. (only touching in the 4 cardinal directions)
    public void floodFill(char[][] matrix, int r, int c) {
        if (outOfBounds(matrix, r, c) || matrix[r][c] == '0')
            return;

        matrix[r][c] = '0';

        floodFill(matrix, r - 1, c);
        floodFill(matrix, r + 1, c);
        floodFill(matrix, r, c - 1);
        floodFill(matrix, r, c + 1);
    }

    public boolean outOfBounds(char[][] matrix, int r, int c) {
        return r < 0 || c < 0 || r >= matrix.length || c >= matrix[0].length;
    }
}
