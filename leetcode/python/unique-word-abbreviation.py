class IsUnique:
	def __init__(self, dictionary):
		self.dictionary = dictionary
		self.dictionary_set = set(dictionary)
		self.create_words_map()

	@staticmethod
	def abbreviate(word):
		if len(word) <= 2: return word
		return word[0] + str(len(word) - 2) + word[-1]

	def create_words_map(self):
		self.words_map = {}
		for word in self.dictionary:
			abbreviation = IsUnique.abbreviate(word)
			if not abbreviation in self.words_map:
				self.words_map[abbreviation] = 1
			else:
				self.words_map[abbreviation] += 1

	def is_unique(self, word):
		abbreviation = IsUnique.abbreviate(word)
		if not abbreviation in self.words_map: return True
		return word in self.dictionary_set and self.words_map[abbreviation] == 1

obj = IsUnique([ "deer", "door", "cake", "card" ])
assert obj.is_unique("deer") == False
assert obj.is_unique("cart") == True
assert obj.is_unique("cane") == False
assert obj.is_unique("make") == True
