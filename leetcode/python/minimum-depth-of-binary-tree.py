class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def minDepth(root) -> int:
    if not root:
        return 0

    def dfs(node, depth):
        if not node:
            return float('+inf')
        if not node.left and not node.right:
            return depth
        return min(dfs(node.left, depth + 1), dfs(node.right, depth + 1))

    return dfs(root, 1)


root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
assert minDepth(root) == 2

root = TreeNode(2, TreeNode(3, TreeNode(4, TreeNode(5, TreeNode(6)))))
assert minDepth(root) == 5
