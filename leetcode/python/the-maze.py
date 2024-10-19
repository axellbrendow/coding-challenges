def can_reach_dest(maze, start, dest):
	nlines = len(maze)
	ncolumns = len(maze[0])
	visited = [[False] * ncolumns for _ in range(nlines)]
	directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

	def dfs(i, j):
		if (
			i < 0 or i >= nlines
			or j < 0 or j >= ncolumns
			or visited[i][j]
			or maze[i][j] == 1
		): return False

		if i == dest[0] and j == dest[1]: return True

		visited[i][j] = True
		for direction in directions:
			if dfs(i + direction[0], j + direction[1]):
				return True
		return False

	return dfs(start[0], start[1])

maze = [
	[0, 0, 1, 0],
	[1, 0, 0, 0],
	[1, 1, 1, 1],
]
start = (0, 3)
dest = (0, 0)
assert can_reach_dest(maze, start, dest) == True

maze = [
	[0, 1, 1, 0],
	[1, 0, 0, 0],
	[1, 1, 1, 1],
]
start = (0, 3)
dest = (0, 0)
assert can_reach_dest(maze, start, dest) == False
