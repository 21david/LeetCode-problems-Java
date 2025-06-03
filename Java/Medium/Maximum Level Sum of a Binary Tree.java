// https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        /*
        We can make an ArrayList (HashMap<Integer, Integer> could work too) and store
        the sums of each level as an element in this ArrayList. As we traverse the tree,
        we can add each value to its corresponding level in the array. Then, we can
        find the left most maximum of the array, and return the index of this array plus 1
        (element at index 0 is the root value, at index 1 is the level 2 sum, at index 2
        is the level 3 sum, ...).
        */
        
        ArrayList<Integer> sums = new ArrayList<>();
        
        dfs(root, sums, 0);
        
        // find max
        int max = sums.get(0);
        int maxI = 0;
        
        for(int i = 1; i < sums.size(); i++)
            if(sums.get(i) > max) {
                max = sums.get(i);
                maxI = i;
            }
        
        // return max
        return maxI + 1;
    }
    
    /*
    Traverse the tree in a dfs manner, keeping track of which level it is on.
    Add the value of the current node to the sum of its level, which is
    stored in the 'sums' ArrayList at index 'level'.
    
    Here, level starts at 0 instead of 1. 
    */
    public void dfs(TreeNode root, ArrayList<Integer> sums, int level) {
        if(root == null)
            return;
        
        if(sums.size() == level) // if there is no index for this level yet
            sums.add(0);
        
        sums.set(level, sums.get(level) + root.val);
        
        dfs(root.left, sums, level+1);
        dfs(root.right, sums, level+1);
    }
}
