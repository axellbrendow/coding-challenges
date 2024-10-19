class TicTacToe:
	def __init__(self, n):
		self.n = n
		self.lines = [0] * n
		self.columns = [0] * n
		self.main_diag = 0
		self.second_diag = 0

	def move(self, i, j, player):
		player_delta = 1 if player == 1 else -1
		self.lines[i] += player_delta
		self.columns[i] += player_delta
		if i == j: self.main_diag += player_delta
		if self.n - i - 1 == j: self.second_diag += player_delta

		win_count = player_delta * self.n
		return player if (
			self.lines[i] == win_count
			or self.columns[i] == win_count
			or self.main_diag == win_count
			or self.second_diag == win_count
		) else 0

game = TicTacToe(3)
assert game.move(0, 0, 1) == 0
assert game.move(0, 2, 2) == 0
assert game.move(2, 1, 1) == 0
assert game.move(1, 1, 2) == 0
assert game.move(2, 0, 1) == 0
assert game.move(1, 0, 2) == 0
assert game.move(2, 1, 1) == 1
