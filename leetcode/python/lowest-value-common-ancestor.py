'''
https://docs.google.com/document/d/1JZsT5S-cltzVZItxlAuLs4o3wb_mcJJxyH7j2nEawTI/edit
'''

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        def dfs(node):
            if not node:
                return False, False, None

            found_p_left, found_q_left, ancestor_left = dfs(node.left)
            if ancestor_left:
                return True, True, node if node.val < ancestor_left.val else ancestor_left

            found_p_right, found_q_right, ancestor_right = dfs(node.right)
            if ancestor_right:
                return True, True, node if node.val < ancestor_right.val else ancestor_right

            found_p = found_p_left or found_p_right or node == p
            found_q = found_q_left or found_q_right or node == q
            return found_p, found_q, node if found_p and found_q else None

        _, _, ancestor = dfs(root)
        return ancestor
