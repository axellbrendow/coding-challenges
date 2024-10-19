# WRONG

class Node:
	def __init__(self, val = 0, right = None, left = None) -> None:
		self.val = val
		self.right = right
		self.left = left

def find_closest_value(root, target):
	def dfs(node, found_target):
		if not node: return float('inf'), False

		found_target = found_target or node.val == target
	
		val, found = dfs(node.left, found_target)
		if found: return val, True

		if found_target and node.val != target: return node.val, True

		val, found = dfs(node.right, found_target)
		if found: return val, True

		return float('inf'), False
	return dfs(root, float('inf'))[0]

root = Node(
	-1,
	Node(
		5,
		Node(6),
		Node(5)
	),
	Node(
		-1,
		Node(-1),
		Node(-1),
	)
)

assert find_closest_value(root, -1) == 5
