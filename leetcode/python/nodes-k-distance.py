class TreeNode:
    def __init__(self, x = 0, right = None, left = None):
        self.val = x
        self.left = left
        self.right = right

def distanceK(root: TreeNode, target, k: int):
    result = []

    def traverse(node, h):
        if not node: return 0

        if k == 0 and node.val == target or k > 0 and h == k:
            result.append(node.val)
            return 0

        left_h = traverse(node.left, 0 if h == 0 and node.val != target else h + 1)

        if left_h == k and k != 0:
            result.append(node.val)
            return 0

        if left_h > 0:
            traverse(node.right, left_h + 1)
            return left_h + 1

        right_h = traverse(node.right, 0 if h == 0 and node.val != target else h + 1)

        if right_h == k and k != 0:
            result.append(node.val)
            return 0

        if right_h > 0:
            traverse(node.left, right_h + 1)
            return right_h + 1

        return 0 if node.val != target else 1

    traverse(root, 0)

    return result

assert str(sorted(distanceK(None, 0, 0))) == str(sorted([]))
assert str(sorted(distanceK(TreeNode(0), 1, 0))) == str(sorted([]))
assert str(sorted(distanceK(TreeNode(0), 0, 0))) == str(sorted([0]))
assert str(sorted(distanceK(TreeNode(0), 0, 1))) == str(sorted([]))

assert str(sorted(distanceK(
    TreeNode(
        0,
        TreeNode(1)
    ),
    0, 1
))) == str(sorted([1]))

assert str(sorted(distanceK(
    TreeNode(
        0,
        TreeNode(1),
        TreeNode(1),
    ),
    0, 1
))) == str(sorted([1,1]))

assert str(sorted(distanceK(
    TreeNode(
        0,
        TreeNode(3),
        TreeNode(
            1,
            None,
            TreeNode(2)
        ),
    ),
    1, 1
))) == str(sorted([0,2]))

assert str(sorted(distanceK(
    TreeNode(
        3,
        TreeNode(
            1,
            TreeNode(8),
            TreeNode(0),
        ),
        TreeNode(
            5,
            TreeNode(
                2,
                TreeNode(4),
                TreeNode(7),
            ),
            TreeNode(6),
        ),
    ),
    5, 1
))) == str(sorted([2,6,3]))

assert str(sorted(distanceK(
    TreeNode(
        3,
        TreeNode(
            1,
            TreeNode(8),
            TreeNode(0),
        ),
        TreeNode(
            5,
            TreeNode(
                2,
                TreeNode(4),
                TreeNode(7),
            ),
            TreeNode(6),
        ),
    ),
    5, 2
))) == str(sorted([1,4,7]))

assert str(sorted(distanceK(
    TreeNode(
        3,
        TreeNode(
            1,
            TreeNode(8),
            TreeNode(0),
        ),
        TreeNode(
            5,
            TreeNode(
                2,
                TreeNode(4),
                TreeNode(7),
            ),
            TreeNode(6),
        ),
    ),
    5, 2
))) == str(sorted([1,4,7]))

assert str(sorted(distanceK(
    TreeNode(
        0,
        None,
        TreeNode(
            1,
        )
    ),
    1, 0
))) == str(sorted([1]))

assert str(sorted(distanceK(
    TreeNode(
        0,
        None,
        TreeNode(
            1,
            TreeNode(
                2,
                TreeNode(
                    3,
                    TreeNode(4)
                )
            )
        )
    ),
    3, 0
))) == str(sorted([3]))
