/*
https://leetcode.com/problems/recover-binary-search-tree/
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Accepted
// ~17ms, faster than 5.15%
// 40.4 mb, less than 80.77%
class Solution {
    ArrayList<Integer> almostSorted = new ArrayList<>();
    TreeNode first;
    TreeNode second;
    
    public void recoverTree(TreeNode root) {
      // create almost sorted array
      inOrder(root);
      
      int[] misplaced = new int[2];
      int m = 0;
      
      // find the elements that are misplaced in the array
      // [1,4,3,2,5]
      // [1,2,3,4,5]
      for(int i = 0; i < almostSorted.size() - 1; i++)
      {
          if(almostSorted.get(i) > almostSorted.get(i + 1))
          {
            if(m == 0)
              misplaced[m++] = almostSorted.get(i);
            else if(m == 1)
              misplaced[m++] = almostSorted.get(i + 1);
          }
      }
        
      // corner case if statement (where the numbers to be swapped are next to each other in the almost sorted array)
      if(m == 1)
      {
          for(int i = 0; i < almostSorted.size()- 1; i++)
          {
              if(almostSorted.get(i) > almostSorted.get(i + 1))
              {
                misplaced[m++] = almostSorted.get(i + 1);
              }
          }
      }
        
      // travel to the misplaced numbers, and swap
      travel(root, misplaced[0], true);
      travel(root, misplaced[1], false);
        
      // swap
      int temp = first.val;
      first.val = second.val;
      second.val = temp;
    }
    
    public void travel(TreeNode root, int misplaced, boolean firstOrSecond) // firstOrSecond = true, means its searching first
    {
        if(first != null && firstOrSecond) // to prevent unnecessary searching
            return;
        
        if(second != null && !firstOrSecond)
            return;
        
        if(root == null)
            return;
        if(root.val == misplaced)
        {
            if(first == null)
            {
                first = root;
                return;
            }
            else
            {
                second = root;
                return;
            }
        }
        
        travel(root.left, misplaced, firstOrSecond);
        travel(root.right, misplaced, firstOrSecond);
    }
  
    public void inOrder(TreeNode root)
    {
        if(root == null)
            return;
        else
        {
            inOrder(root.left);
            almostSorted.add(root.val);
            inOrder(root.right);
        }
    }
}
