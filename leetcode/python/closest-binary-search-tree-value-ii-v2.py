class Node:
	def __init__(self, val = 0, right = None, left = None) -> None:
		self.val = val
		self.right = right
		self.left = left

from collections import deque

def get_smaller_and_greater(root, target):
	smaller, greater = [], deque()
	def dfs(node):
		if not node: return
		dfs(node.left)
		if node.val <= target: smaller.append(node.val)
		else: greater.append(node.val)
		dfs(node.right)
	dfs(root)
	return smaller, greater

def get_values(smaller, greater, target, num_values):
	values = []
	while len(smaller) > 0 and len(greater) > 0 and len(values) < num_values:
		if abs(target - smaller[-1]) <= abs(target - greater[0]):
			values.append(smaller.pop())
		else:
			values.append(greater.popleft())
	while len(smaller) > 0 and len(values) < num_values:
		values.append(smaller.pop())
	while len(greater) > 0 and len(values) < num_values:
		values.append(greater.popleft())
	return values

def find_closest_values(root, target, num_values):
	smaller, greater = get_smaller_and_greater(root, target)
	return get_values(smaller, greater, target, num_values)

root = Node(7)
target = 0
num_values = 1
output = [7]
assert find_closest_values(root, target, num_values) == output

root = Node(
	7,
	Node(9)
)
target = 10
num_values = 1
output = [9]
assert find_closest_values(root, target, num_values) == output

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
target = -2
num_values = 6
output = [-1, -1, -1, -1, 5, 5]
assert sorted(find_closest_values(root, target, num_values)) == sorted(output)

root = Node(
	5,
	Node(6),
	Node(
		3,
		Node(4),
		Node(
			2,
			None,
			Node(1)
		),
	)
)
target = 2.857143
num_values = 4
output = [1,2,3,4]
assert sorted(find_closest_values(root, target, num_values)) == sorted(output)
