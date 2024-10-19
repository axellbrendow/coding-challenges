import heapq

def find_shortest_distance_to_dest(maze, start, dest):
	nlines = len(maze)
	ncolumns = len(maze[0])
	visited = [[False] * ncolumns for _ in range(nlines)]
	directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
	heap = [(0, *start)]
	while len(heap) > 0:
		distance, i, j = heapq.heappop(heap)
		visited[i][j] = True

		for direction in directions:
			new_i, new_j = i + direction[0], j + direction[1]
			if (
				new_i < 0 or new_i >= nlines
				or new_j < 0 or new_j >= ncolumns
				or maze[new_i][new_j] == 1
				or visited[new_i][new_j]
			): continue

			if new_i == dest[0] and new_j == dest[1]:
				return distance + 1

			heapq.heappush(heap, (distance + 1, new_i, new_j))
	return -1

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
