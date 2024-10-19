# CORRECT SOLUTION, START FROM THE GATES:
def fill_nearest_gate_distance(nlines, ncolumns, grid):
	visited = [[False for _ in range(ncolumns)] for _ in range(nlines)]
	directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

	def dfs(i, j, steps):
		if (
			i < 0 or i >= nlines
			or j < 0 or j >= ncolumns
			or visited[i][j]
			or grid[i][j] == -1
			or grid[i][j] == 0
			or grid[i][j] <= steps
		): return
		visited[i][j] = True
		grid[i][j] = steps
		for direction in directions:
			dfs(i + direction[0], j + direction[1], steps + 1)
		visited[i][j] = False

	for i in range(nlines):
		for j in range(ncolumns):
			if grid[i][j] == 0:
				for direction in directions:
					dfs(i + direction[0], j + direction[1], 1)
	print(grid)

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
