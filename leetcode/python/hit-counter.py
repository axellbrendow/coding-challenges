class HitCounter:
	def __init__(self):
		self.hit_count = 0  # 3
		self.hit_count_map = {}

	def remove_hit_counts(self, timestamp):  # timestamp = 301
		# [1]
		keys_to_remove = [key for key in self.hit_count_map if key < timestamp - 300]
		for key in keys_to_remove:
			self.hit_count -= self.hit_count_map[key]
			del self.hit_count_map[key]

	def hit(self, timestamp):  # timestamp = 301
		if not timestamp in self.hit_count_map:
			self.hit_count_map[timestamp] = 1
		else:
			self.hit_count_map[timestamp] += 1
		self.remove_hit_counts(timestamp)
		self.hit_count += 1

	def get_hits(self, timestamp):  # timestamp = 3
		self.remove_hit_counts(timestamp)
		return self.hit_count  # 3

hitcounter = HitCounter()
assert hitcounter.get_hits(0) == 0
hitcounter.hit(1)
assert hitcounter.get_hits(1) == 1
hitcounter.hit(1)
assert hitcounter.get_hits(1) == 2
hitcounter.hit(3)
# assert hitcounter.get_hits(2) == 2  # I should ask for that situation in the interview, get hits of the past
assert hitcounter.get_hits(3) == 3
assert hitcounter.get_hits(301) == 3
assert hitcounter.get_hits(303) == 1
assert hitcounter.get_hits(304) == 0
