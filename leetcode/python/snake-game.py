from collections import deque

class Snake:
	def __init__(self, width, height, food):
		self._width = width
		self._height = height
		self._food_gen = (tuple(f) for f in food)
		self._get_next_food()

		self._snake_queue = deque()
		self._snake_queue.append((0, 0))

		self._snake_set = set()
		self._snake_set.add((0, 0))

		self._next_pos = {
			'R': (0, 1),
			'D': (1, 0),
			'L': (0, -1),
			'U': (-1, 0),
		}
		self.print()

	def _get_cell(self, i, j):
		if (i, j) in self._snake_set: return 'S'
		if (i, j) == self._food: return 'F'
		return '_'

	def print(self):
		for i in range(self._height):
			for j in range(self._width):
				print(f'{self._get_cell(i, j)}', end=' ')
			print()
		print()

	def _get_next_food(self):
		try:
			self._food = next(self._food_gen)
		except:
			self._food = None

	def move(self, direction):
		curr_pos = self._snake_queue[-1]
		offset = self._next_pos[direction]
		next_pos = (curr_pos[0] + offset[0], curr_pos[1] + offset[1])

		if (
			next_pos[0] < 0 or next_pos[0] >= self._height
			or next_pos[1] < 0 or next_pos[1] >= self._width
		):
			print('Game over')
			return -1  # Question what should I do if move() is called after game over

		self._snake_queue.append(next_pos)

		if next_pos == self._food:
			self._get_next_food()
		else:
			pos = self._snake_queue.popleft()

			if pos in self._snake_set:
				self._snake_set.remove(pos)

			if next_pos in self._snake_set:
				print('Game over')
				return -1

		self._snake_set.add(next_pos)
		self.print()
		return len(self._snake_queue)

width = 3
height = 2
food = [[1,2],[0,1]]
game = Snake(width, height, food)
assert game.move('R') == 1
assert game.move('D') == 1
assert game.move('R') == 2
assert game.move('U') == 2
assert game.move('L') == 3
assert game.move('U') == -1
