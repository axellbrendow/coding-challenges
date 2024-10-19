def get_min_cost_path(A, steps):
	min_cost = float('inf')
	best_path = []
	path = []
	def dfs(pos, cost):
		nonlocal min_cost
		nonlocal best_path
		if pos == len(A) - 1:
			if cost < min_cost:
				best_path = [*path, pos + 1]
				min_cost = cost
			return
		path.append(pos + 1)
		for i in range(1, steps + 1):
			if pos + i >= len(A) or A[pos + i] == -1: continue
			dfs(pos + i, cost + A[pos + i])
		path.pop()
	dfs(0, 0)
	return best_path

A = [1,2,4,-1,2]
steps = 2
assert get_min_cost_path(A, steps) == [1,3,5]
