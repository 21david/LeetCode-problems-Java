//  https://leetcode.com/problems/rotate-image/

class Solution {
    public void rotate(int[][] matrix) {
        // 0 ms, faster than 100%
        // 38.6 mb, less than 98.82%
        
        /*
        I think we can switch 4 spots at a time. for example, switch the four corners. Move one corner value to a temp variable, 
        move the other 3 to their new position, then put that value into its new position. Then, repeat this for every other set
        of 4 elements. We can do this layer by layer. For example, the outer rows and columns are the first layer, then the 
        second-last rows and columns are the second layer.
        */

        int n=matrix.length;
        
        // odd length matrices have (length/2)+1 layers (integer division). even length matrices have (length/2) layers
        int layers;
        if (n % 2 == 0)
            layers = n/2;
        else
            layers = n/2 + 1;
        
        int temp;  // hold a value so we can swap
        int curLength = n;  // length of the outer layer (num of elements per row or column)
        int l = 0;  // this keeps track of the current layer we're on
        
        while( l < layers ) {
            // rotate all the elements of a layer
            for(int e = 0; e < curLength-1 ; e++) {
                // swap 4 elements
                temp = matrix[l][l + e];  
                matrix[l][l + e] = matrix[n-1-l-e][l];
                matrix[n-1-l-e][l] = matrix[n-1-l][n-1-l-e];
                matrix[n-1-l][n-1-l-e] = matrix[l+e][n-1-l];
                matrix[l+e][n-1-l] = temp;
            }

            l++;  // move to the next layer
            curLength -= 2;  // each layer has a length of 2 less than the previous one
        }
    }
}

/* 
Sample input:
[[1,2,3],[4,5,6],[7,8,9]]
[[1,2],[3,4]]
[[1,2,3,4,5,6],[7,8,9,10,11,12],[13,14,15,16,17,18],[19,20,21,22,23,24],[25,26,27,28,29,30],[31,32,33,34,35,36]]
[[1]]

*/
