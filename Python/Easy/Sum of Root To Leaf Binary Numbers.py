# https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

'''
Using strings, concatenation, and binary conversion algorithm
'''
class Solution(object):
    def sumRootToLeaf(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.total = 0
    
        def dfs(node, num_slate):
            if not (node.left or node.right):
                num_slate.append(str(node.val))
                self.total += int(''.join(num_slate), 2)
                num_slate.pop()
                return
                
            num_slate.append(str(node.val))
            if node.left:
                dfs(node.left, num_slate)
            if node.right:
                dfs(node.right, num_slate)
            num_slate.pop()
            
        dfs(root, [])
        return self.total


'''
Using math
'''
class Solution(object):
    def sumRootToLeaf(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.total = 0
    
        def dfs(node, num):
            if not (node.left or node.right):
                num <<= 1
                num += node.val
                self.total += num
                num >>= 1
                return
                
            num <<= 1
            num += node.val
            if node.left:
                dfs(node.left, num)
            if node.right:
                dfs(node.right, num)
            num >>= 1
            
        dfs(root, 0)
        return self.total
