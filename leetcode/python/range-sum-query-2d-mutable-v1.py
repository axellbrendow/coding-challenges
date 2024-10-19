class NumMatrix:
	def __init__(self, matrix):
		self.matrix = matrix
		self.nlines = len(matrix)
		self.ncolumns = len(matrix[0])
		self.total = self.nlines * self.ncolumns
		self.create_sum_matrix()

	def create_sum_matrix(self):
		self.sum_matrix = []
		for i in range(self.nlines):  # 0
			for j in range(self.ncolumns):  # 0
				new_array = [0] * self.total
				self.sum_matrix.append(new_array)
				last_value = self.matrix[i][j]
				new_array[self.to_1d(i, j)] = last_value

				for j_sum in range(j + 1, self.ncolumns):  # 2
					new_value = last_value + self.matrix[i][j_sum]
					new_array[self.to_1d(i, j_sum)] = new_value
					last_value = new_value

				for i_sum in range(i + 1, self.nlines):  # 0
					for j_sum in range(self.ncolumns):  # 2
						new_value = last_value + self.matrix[i_sum][j_sum]
						new_array[self.to_1d(i_sum, j_sum)] = new_value
						last_value = new_value

	def to_1d(self, row, col):
		return row * self.ncolumns + col

	def sumRegion(self, row1, col1, row2, col2):
		sum = 0
		for row in range(row1, row2 + 1):
			sum += self.sum_matrix[self.to_1d(row, col1)][self.to_1d(row, col2)]
		return sum

	def update(self, row, col, new_val):
		self.my_pos = self.to_1d(row, col)
		old_val = self.matrix[row][col]
		self.matrix[row][col] = new_val
		for i in range(self.my_pos + 1):
			for j in range(self.my_pos, self.total):
				self.sum_matrix[i][j] += -old_val + new_val

matrix = NumMatrix([
	[3, 0, 1, 4, 2],
	[5, 6, 3, 2, 1],
	[1, 2, 0, 1, 5],
	[4, 1, 0, 1, 7],
	[1, 0, 3, 0, 5],
])

assert matrix.sumRegion(2, 1, 4, 3) == 8
matrix.update(3, 2, 2)
assert matrix.sumRegion(2, 1, 4, 3) == 10
