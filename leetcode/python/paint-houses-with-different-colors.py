def best_way_to_paint(costs, K):
	num_houses = len(costs)
	cache = {}
	def dfs(i, j):
		if i >= num_houses or j >= K: return 0
		if (i, j) in cache: return cache[(i, j)]
		min_cost = float('inf')
		for color in range(K):
			if color == j: continue
			min_cost = min(min_cost, costs[i][j] + dfs(i + 1, color))
		cache[(i, j)] = min_cost
		return min_cost

	min_cost = float('inf')
	for i in range(K):
		min_cost = min(min_cost, dfs(0, i))
	return min_cost

assert best_way_to_paint([
    [6, 2, 6],
    [1, 7, 9],
], 3) == 3

assert best_way_to_paint([
    [6, 2, 6],
    [1, 7, 9],
    [4, 2, 4],
    [5, 3, 3],
    [9, 1, 1],
], 3) == 9
