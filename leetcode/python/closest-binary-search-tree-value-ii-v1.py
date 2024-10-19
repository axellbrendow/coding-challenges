class Node:
	def __init__(self, val = 0, right = None, left = None) -> None:
		self.val = val
		self.right = right
		self.left = left

def to_iterator(root):
	def dfs(node):
		if not node: return
		yield from dfs(node.left)
		yield node.val
		yield from dfs(node.right)
	return dfs(root)

def binary_search(arr, target):
	left, right = 0, len(arr) - 1
	mid = (left + right) // 2
	while left <= right:
		mid = (left + right) // 2
		if arr[mid] == target: return mid
		if target < arr[mid]:
			right = mid - 1
		else:
			left = mid + 1
	return mid

def get_values(arr, target, num_values, start):
	left, right = start, start + 1
	values = []
	while left >= 0 and right < len(arr) and len(values) < num_values:
		if abs(target - arr[left]) <= abs(target - arr[right]):
			values.append(arr[left])
			left -= 1
		else:
			values.append(arr[right])
			right += 1
	while left >= 0 and len(values) < num_values:
		values.append(arr[left])
		left -= 1
	while right < len(arr) and len(values) < num_values:
		values.append(arr[right])
		right += 1
	return values

def find_closest_values(root, target, num_values):
	arr = list(to_iterator(root))
	index = binary_search(arr, target)
	return get_values(arr, target, num_values, index)

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
assert find_closest_values(root, target, num_values) == output
