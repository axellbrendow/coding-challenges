class StringIterator:
	def __init__(self, compressed_str):
		self._compressed_str = compressed_str
		self._index = 0
		self._get_next()

	def _find_non_digit(self, start):
		while (
            start < len(self._compressed_str)
            and '0' <= self._compressed_str[start] <= '9'
        ): start += 1
		return start

	def _get_next(self):
		if self._index >= len(self._compressed_str):
			self._letter = ' '
			self._count = 0
		else:
			self._letter = self._compressed_str[self._index]
			first_digit = self._index + 1
			self._index = self._find_non_digit(first_digit)
			self._count = int(self._compressed_str[first_digit:self._index])

	def next(self):
		if self._count == 0:
			self._get_next()
		self._count -= 1
		return self._letter

	def has_next(self):
		if self._count > 0: return True
		self._get_next()
		return self._letter != ' '

iterator = StringIterator('L1e2t1C1o1d1e1')
assert iterator.next() == 'L'
assert iterator.next() == 'e'
assert iterator.next() == 'e'
assert iterator.next() == 't'
assert iterator.next() == 'C'
assert iterator.next() == 'o'
assert iterator.next() == 'd'
assert iterator.has_next() == True
assert iterator.next() == 'e'
assert iterator.has_next() == False
assert iterator.next() == ' '
