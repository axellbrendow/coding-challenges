class Node:
	def __init__(self, val = 0, right = None, left = None) -> None:
		self.val = val
		self.right = right
		self.left = left

def get_anti_clockwise_boundary_of_binary_tree(root):
	if not root: return []
	boundary = []
	boundary.append(root.val)

	def get_left_boundary(node):
		if not node: return
		if not node.left and not node.right: return
		boundary.append(node.val)
		if node.left: get_left_boundary(node.left)
		else: get_left_boundary(node.right)

	def get_right_boundary(node):
		if not node: return
		if not node.left and not node.right: return
		if node.right: get_right_boundary(node.right)
		else: get_right_boundary(node.left)
		boundary.append(node.val)

	def get_leaves(node):
		if not node: return
		if not node.left and not node.right:
			boundary.append(node.val)
		else:
			get_leaves(node.left)
			get_leaves(node.right)

	get_left_boundary(root.left)
	get_leaves(root)
	get_right_boundary(root.right)
	return boundary

tree = Node(
	4,
	None,
	Node(
		1,
		None,
		Node(
			2,
			None,
			Node(3)
		)
	),
)

assert str(get_anti_clockwise_boundary_of_binary_tree(tree)) == str([4, 1, 2, 3])

tree = Node(
	1,
	Node(
		2,
		Node(4),
		Node(3)
	),
)

assert str(get_anti_clockwise_boundary_of_binary_tree(tree)) == str([1, 3, 4, 2])

tree = Node(
	1,
	Node(
		3,
		None,
		Node(
			6,
			Node(10),
			Node(9)
		)
	),
	Node(
		2,
		Node(
			5,
			Node(8),
			Node(7)
		),
		Node(4)
	)
)

assert str(get_anti_clockwise_boundary_of_binary_tree(tree)) == str([1, 2, 4, 7, 8, 9, 10, 6, 3])
