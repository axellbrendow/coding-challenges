def count_black_pixels(picture, nlines, ncolumns):
	lines, columns = [0] * nlines, [0] * ncolumns
	for i in range(nlines):
		for j in range(ncolumns):
			if picture[i][j] == 'B':
				lines[i] += 1
				columns[j] += 1
	return lines, columns

def count_alone_black_pixels(picture, lines, columns, nlines, ncolumns):
	count = 0
	for i in range(nlines):
		for j in range(ncolumns):
			if picture[i][j] == 'B' and lines[i] == 1 and columns[j] == 1:
				count += 1
	return count

def get_num_of_alone_pixels(picture):
	nlines, ncolumns = len(picture), len(picture[0])
	lines, columns = count_black_pixels(picture, nlines, ncolumns)
	return count_alone_black_pixels(picture, lines, columns, nlines, ncolumns)

picture = \
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

assert get_num_of_alone_pixels(picture) == 3

picture = \
[['B', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

assert get_num_of_alone_pixels(picture) == 1
