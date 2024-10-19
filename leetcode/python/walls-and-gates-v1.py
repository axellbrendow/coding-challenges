# WRONG SOLUTION, it doesn't fill all the grid cells correctly, just most of them.
def fill_nearest_gate_distance(nlines, ncolumns, grid):
	visited = [[False for _ in range(ncolumns)] for _ in range(nlines)]
	cache = {}
	directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

	def dfs(i, j):
		if i < 0 or i >= nlines or j < 0 or j >= ncolumns or grid[i][j] == -1: return float('inf')
		if grid[i][j] == 0: return 0
		min_steps = float('inf')
		for d_num, direction in enumerate(directions):
			new_i, new_j = i + direction[0], j + direction[1]
			if (
				new_i < 0 or new_i >= nlines
				or new_j < 0 or new_j >= ncolumns
				or visited[new_i][new_j]
				or (i, j, d_num) in cache
			): continue
			visited[new_i][new_j] = True
			steps = dfs(new_i, new_j)
			if steps != float('inf'):
				steps += 1
			min_steps = min(min_steps, steps)
			if steps != float('inf'): cache[(i, j, d_num)] = steps
			visited[new_i][new_j] = False
		grid[i][j] = min(grid[i][j], min_steps)
		return min_steps

	for i in range(nlines):
		for j in range(ncolumns):
			if grid[i][j] != 0 and grid[i][j] != -1:
				visited[i][j] = True
				dfs(i, j)
				visited[i][j] = False

grid = [
	[float('inf'),  -1,             0,              float('inf')],
	[float('inf'),  float('inf'),   float('inf'),   -1],
	[float('inf'),  -1,             float('inf'),   -1],
	[0,             -1,             float('inf'),   float('inf')],
]

result = [
	[3, -1, 0, 1],
	[2, 2, 1, -1],
	[1, -1, 2, -1],
	[0, -1, 3, 4],
]

fill_nearest_gate_distance(4, 4, grid)
assert str(grid) == str(result)
