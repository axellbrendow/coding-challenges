class GameOfLife:
	def __init__(self, board):
		self.board = board
		self.nlines = len(board)
		self.ncolumns = len(board[0])
		self.directions = [
			(-1, 1),
			(0, 1),
			(1, 1),
			(1, 0),
			(1, -1),
			(0, -1),
			(-1, -1),
			(-1, 0),
		]
		self.create_live_neighbors()

	def update_neighbors(self, i, j, delta):
		for direction in self.directions:
			new_i = i + direction[0]
			new_j = j + direction[1]
			self.live_neighbors[new_i][new_j] += delta

	def create_live_neighbors(self):
		self.live_neighbors = [[0] * (self.ncolumns + 1) for _ in range(self.nlines + 1)]
		for i in range(self.nlines):
			for j in range(self.ncolumns):
				if self.board[i][j] == 1:
					self.update_neighbors(i, j, 1)

	def play(self):
		old_live_neighbors = [row.copy() for row in self.live_neighbors]
		for i in range(self.nlines):
			for j in range(self.ncolumns):
				if self.board[i][j] == 1:
					if old_live_neighbors[i][j] < 2 or old_live_neighbors[i][j] > 3:
						self.update_neighbors(i, j, -1)
						self.board[i][j] = 0
				elif old_live_neighbors[i][j] == 3:
					self.update_neighbors(i, j, 1)
					self.board[i][j] = 1

board = [
	[1, 1],
	[1, 0],
]
game = GameOfLife(board)

game.play()
next_board = [
	[1, 1],
	[1, 1],
]
assert str(game.board) == str(next_board)

game.play()
next_board = [
	[1, 1],
	[1, 1],
]
assert str(game.board) == str(next_board)


board = [
	[0, 1, 0],
	[0, 0, 1],
	[1, 1, 1],
	[0, 0, 0],
]
game = GameOfLife(board)

game.play()
next_board = [
	[0, 0, 0],
	[1, 0, 1],
	[0, 1, 1],
	[0, 1, 0],
]
assert str(game.board) == str(next_board)
