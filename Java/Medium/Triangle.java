//  https://leetcode.com/problems/triangle/

/*
I will try to copy my approach from 931. Minimum Falling Path Sum
I will make a triangle matrix that will store the minimum
sum to the bottom at each location. I will copy the bottom
row of the input matrix to this matrix, then start from the
second last row. For each element, I will find the minimum
of the two elements below, add the current value, and set
that spot to that summed value. Then, I'll repeat this
for all values, and the last value should be the minimum
path sum to the bottom.
*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        Stack<List<Integer>> lowestSums = new Stack<>();
        
        List<Integer> bottomRow = triangle.get(triangle.size()-1);
        
        lowestSums.push(bottomRow);
        
        // Make another arraylist, use the top row in the lowestSums
        // to find the values for this row, then add it to lowestSums
        List<Integer> newList = new ArrayList<>();
        List<Integer> listBelow;
        List<Integer> curList;
        
        int r = triangle.size() - 2;
        
        int min;
        while(lowestSums.peek().size() >= 2) {
            curList = triangle.get(r--);
            listBelow = lowestSums.pop();
            newList = new ArrayList<>();
            
            for(int i = 0; i < listBelow.size() - 1; i++) {
                min = Math.min(listBelow.get(i), listBelow.get(i+1));
                newList.add(min + curList.get(i));
            }
            
            lowestSums.push(newList);
        }
        
        return lowestSums.peek().get(0);
    }
}

/*
Sample input:
[[14]]
[[5],[1,3]]
[[20],[14,6],[1,2,3],[4,5,6,60]]
[[2],[3,4],[6,5,7],[4,1,8,3]]
*/
