from collections import deque
from typing import List, Optional


class TreeNode:  # Definition for a binary tree node.
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left: TreeNode = left
        self.right: TreeNode = right


class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []
        output = []
        queue = deque([(root, 0)])
        while len(queue) > 0:
            node, depth = queue.popleft()
            if not (depth + 1 <= len(output)):
                output.append([])
            output[depth].append(node.val)
            if node.left:
                queue.append((node.left, depth + 1))
            if node.right:
                queue.append((node.right, depth + 1))
        return output
