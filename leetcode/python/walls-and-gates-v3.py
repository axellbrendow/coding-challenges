from collections import deque

# BFS is far better than DFS in this problem because you always reach a cell with the minimum steps

def bfs(i, j, nlines, ncolumns, grid):
	queue = deque()
	queue.append((i, j, 0))
	directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
	while len(queue) > 0:
		cell_i, cell_j, distance = queue.popleft()
		for direction in directions:
			new_i = cell_i + direction[0]
			new_j = cell_j + direction[1]
			if new_i < 0 or new_i >= nlines or new_j < 0 or new_j >= ncolumns: continue
			if grid[new_i][new_j] == -1 or grid[new_i][new_j] <= distance + 1: continue
			grid[new_i][new_j] = distance + 1
			queue.append((new_i, new_j, distance + 1))

def fill_nearest_gate_distance(grid):
	nlines = len(grid)
	ncolumns = len(grid[0])
	for i in range(nlines):
		for j in range(ncolumns):
			if grid[i][j] == 0:
				bfs(i, j, nlines, ncolumns, grid)

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

fill_nearest_gate_distance(grid)
assert str(grid) == str(result)
