class Node:
	def __init__(self, val = 0, right = None, left = None) -> None:
		self.val = val
		self.right = right
		self.left = left

def get_longest_consecutive_path(root):
	def dfs(node):
		if not node: return 0, 0, 0

		incr_l, decr_l, max_path_l = dfs(node.left)
		incr_r, decr_r, max_path_r = dfs(node.right)

		incr, decr, max_path = 0, 0, 1

		if (node.left and abs(node.val - node.left.val) == 1):
			if (node.left.val <= node.val):
				decr = max(decr, 1 + decr_l)
			if (node.left.val >= node.val):
				incr = max(incr, 1 + incr_l)

		if (node.right and abs(node.val - node.right.val) == 1):
			if (node.right.val <= node.val):
				decr = max(decr, 1 + decr_r)
			if (node.right.val >= node.val):
				incr = max(incr, 1 + incr_r)

		max_path = max(max_path, max_path_l, max_path_r, 1 + incr + decr)
		return incr, decr, max_path
	return dfs(root)[2]

root = Node(0)
assert get_longest_consecutive_path(root) == 1

root = Node(
	0,
	Node(1)
)
assert get_longest_consecutive_path(root) == 2

root = Node(
	0,
	Node(1),
	Node(2)
)
assert get_longest_consecutive_path(root) == 2

root = Node(
	1,
	Node(2),
	Node(0)
)
assert get_longest_consecutive_path(root) == 3

root = Node(
	4,
	None,
	Node(
		2,
		Node(
			3,
			Node(4)
		),
		Node(
			3,
			None,
			Node(1)
		)
	)
)
assert get_longest_consecutive_path(root) == 3
