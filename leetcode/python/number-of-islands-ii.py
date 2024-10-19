class IslandsCounter:
	def __init__(self, nlines, ncolumns):
		self.grid = [[0 for _ in range(nlines)] for _ in range(ncolumns)]
		self.nlines = nlines
		self.ncolumns = ncolumns
		self.nislands = 0
		self.directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
		self.all_nislands = []
		self.parent = [self.to_1d(i, j) for i in range(self.nlines) for j in range(self.ncolumns)]

	def to_1d(self, i, j):
		return i * self.nlines + j

	def get_parent(self, pos):
		parent_pos = pos
		while self.parent[parent_pos] != parent_pos:
			parent_pos = self.parent[parent_pos]
		return parent_pos

	def get_num_islands_close_to_me(self, i, j):
		parent_set = set()
		for direction in self.directions:
			new_i, new_j = i + direction[0], j + direction[1]
			if (
				new_i < 0 or new_j < 0
				or new_i >= self.nlines or new_j >= self.ncolumns
				or self.grid[new_i][new_j] != 1
			): continue
			parent_set.add(self.get_parent(self.to_1d(new_i, new_j)))
		return len(parent_set)

	def union(self, pos1, pos2):
		parent1 = self.get_parent(pos1)
		parent2 = self.get_parent(pos2)
		self.parent[parent1] = parent2

	def add_land(self, i, j):
		num_close = self.get_num_islands_close_to_me(i, j)
		self.nislands -= num_close - 1
		self.all_nislands.append(self.nislands)
		for direction in self.directions:
			new_i, new_j = i + direction[0], j + direction[1]
			if (
				new_i < 0 or new_j < 0
				or new_i >= self.nlines or new_j >= self.ncolumns
				or self.grid[new_i][new_j] != 1
			): continue
			self.union(self.to_1d(i, j), self.to_1d(new_i, new_j))
		self.grid[i][j] = 1

islands_counter = IslandsCounter(3, 3)
islands_counter.add_land(0, 0)
islands_counter.add_land(0, 1)
islands_counter.add_land(1, 2)
islands_counter.add_land(2, 1)
islands_counter.add_land(1, 1)
assert str(islands_counter.all_nislands) == str([1, 1, 2, 3, 1])
