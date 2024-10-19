from collections import defaultdict

class Node:
	def __init__(self, val = 0, right = None, left = None) -> None:
		self.val = val
		self.right = right
		self.left = left

def get_vertical_order_traversal(root):
	map = defaultdict(list)
	min_column = 0  # -2

	def dfs(node, line, column):  # root, 0  | rl, -1  | rll, -2
		nonlocal min_column
		if not node: return
		min_column = min(min_column, column)
		map[column].append((line, node.val))
		dfs(node.left, line + 1, column - 1)
		dfs(node.right, line + 1, column + 1)
	dfs(root, 0, 0)

	shift = abs(min_column)
	output = [None] * len(map)
	for col in map:
		map[col].sort(key=lambda pair: pair[0])
		output[col + shift] = [pair[1] for pair in map[col]]
	return output

tree = Node(
	3,
	Node(
		8,
		Node(7),
		Node(
			1,
			None,
			Node(5)
		)
	),
	Node(
		9,
		Node(
			0,
			Node(2)
		),
		Node(4)
	)
)

output = [
	[4],
	[9,5],
	[3,0,1],
	[8,2],
	[7],
]

assert str(get_vertical_order_traversal(tree)) == str(output)
