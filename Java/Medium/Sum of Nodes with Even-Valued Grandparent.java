//  https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/

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

/*
We can traverse through the tree (DFS or BFS). For every even-valued node,
we can add all of its grand-children to a sum variable.
Then we can return the sum.
*/
class Solution {
    int sum = 0;
    public int sumEvenGrandparent(TreeNode root) {
        // DFS
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            
            if(cur.val % 2 == 0) {
                addGrandchildren(cur);
            }
            
            if(cur.left != null)
                stack.add(cur.left);
            if(cur.right != null)
                stack.add(cur.right);
        }
        
        return sum;
    }
    
    /*
    For any input node, we need to travel to all the children two levels down (max 4 children)
    and add their values to the 'sum' variable.
    */
    public void addGrandchildren(TreeNode cur) {
        // check the left child grandchildren
        if(cur.left != null) {
            if(cur.left.left != null)
                sum += cur.left.left.val;
            if(cur.left.right != null)
                sum += cur.left.right.val;
        }
        
        // check the right child grandchildren
        if(cur.right != null) {
            if(cur.right.left != null)
                sum += cur.right.left.val;
            if(cur.right.right != null)
                sum += cur.right.right.val;
        }
    }
}
