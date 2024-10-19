def find_shortest_distance_to_dest(maze, start, dest):
	nlines = len(maze)
	ncolumns = len(maze[0])
	visited = [[False] * ncolumns for _ in range(nlines)]

	directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

	def dfs(i, j, step):
		if (
			i < 0 or i >= nlines
			or j < 0 or j >= ncolumns
			or visited[i][j]
			or maze[i][j] == 1
		): return float('inf')

		if i == dest[0] and j == dest[1]: return step

		visited[i][j] = True
		min_distance = float('inf')

		for direction in directions:
			min_distance = min(
				min_distance,
				dfs(i + direction[0], j + direction[1], step + 1)
			)

		visited[i][j] = False
		return min_distance

	distance = dfs(start[0], start[1], 0)
	return distance if distance != float('inf') else -1

maze = [
	[0, 0, 1, 0],
	[1, 0, 0, 0],
	[1, 1, 1, 1],
]
start = (0, 3)
dest = (0, 0)
assert find_shortest_distance_to_dest(maze, start, dest) == 5

maze = [
	[0, 1, 1, 0],
	[1, 0, 0, 0],
	[1, 1, 1, 1],
]
start = (0, 3)
dest = (0, 0)
assert find_shortest_distance_to_dest(maze, start, dest) == -1

maze = [
	[0,	0,	0,	0,	0],
	[0,	1,	0,	1,	0],
	[0,	1,	0,	1,	0],
	[0,	1,	1,	1,	0],
	[0,	0,	0,	0,	0],
]
start = (4, 0)
dest = (2, 2)
assert find_shortest_distance_to_dest(maze, start, dest) == 8
