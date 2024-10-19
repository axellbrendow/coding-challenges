def get_area_of_the_black_pixels_rectangle(image, x, y):
	nlines, ncolumns = len(image), len(image[0])  # 3, 4
	smallest_line, greatest_line = x, x  # 0, 1
	smallest_column, greatest_column = y, y  # 2, 2
	directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
	visited = [[False for _ in range(ncolumns)] for _ in range(nlines)]

	def dfs(i, j):  # i = 0, j = 2  |  i = 1, j = 2
		nonlocal smallest_line
		nonlocal greatest_line
		nonlocal smallest_column
		nonlocal greatest_column
		if (
			i < 0 or i >= nlines
			or j < 0 or j >= ncolumns
			or visited[i][j]
			or image[i][j] == 0
		): return

		visited[i][j] = True
		smallest_line = min(smallest_line, i)
		greatest_line = max(greatest_line, i)
		smallest_column = min(smallest_column, j)
		greatest_column = max(greatest_column, j)

		for direction in directions:  # direction = (1, 0)
			new_i, new_j = i + direction[0], j + direction[1]  # 1, 2
			dfs(new_i, new_j)

	dfs(x, y)
	return (greatest_line - smallest_line + 1) * (greatest_column - smallest_column + 1)

image = [
	[0, 0, 1, 0],
	[0, 1, 1, 0],
	[0, 1, 0, 0],
]
x, y = 0, 2
assert get_area_of_the_black_pixels_rectangle(image, x, y) == 6

image = [
	[1, 0, 0, 0],
	[1, 1, 1, 1],
	[0, 0, 0, 0],
]
x, y = 0, 0
assert get_area_of_the_black_pixels_rectangle(image, x, y) == 8
