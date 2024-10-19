class Node:
	def __init__(self, val = 0, right = None, left = None):
		self.val = val
		self.right = right
		self.left = left

def get_longest_consecutive_path(root):
	def dfs(node):  # *0
		if not node: return 0, 0
		max_i_can_achieve = total_max = 1  # 2, 2
		if node.left:
			max_i_can_achieve_left, total_max_left = dfs(node.left)  # 1, 1
			if node.val + 1 == node.left.val:
				max_i_can_achieve = max_i_can_achieve_left + 1
			total_max = max(total_max, total_max_left, max_i_can_achieve)
		if node.right:
			max_i_can_achieve_right, total_max_right = dfs(node.right)
			if node.val + 1 == node.right.val:
				max_i_can_achieve = max(
					max_i_can_achieve,
					max_i_can_achieve_right + 1
				)
			total_max = max(total_max, total_max_right, max_i_can_achieve)
		return max_i_can_achieve, total_max
	return dfs(root)[1]  # 2

root = Node(
	1,
	Node(
		3,
		Node(
			4,
			Node(5)
		),
		Node(2)
	)
)

assert get_longest_consecutive_path(root) == 3
