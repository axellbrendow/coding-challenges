import heapq

# This version always gives phone numbers that are smaller

class PhoneDirectory:
	def __init__(self, num_phones):
		self.num_phones = num_phones
		self.available = [phone for phone in range(num_phones)]
		heapq.heapify(self.available)
		self.unavailable = set()

	def check(self, phone):
		return not phone in self.unavailable

	def release(self, phone):
		if phone in self.unavailable:
			self.unavailable.remove(phone)
			heapq.heappush(self.available, phone)

	def get(self):
		if len(self.available) == 0: return -1
		phone = self.available[0]
		heapq.heappop(self.available)
		self.unavailable.add(phone)
		return phone

directory = PhoneDirectory(3)
assert directory.get() == 0
assert directory.get() == 1
assert directory.check(2) == True
assert directory.get() == 2
assert directory.check(2) == False
directory.release(2)
assert directory.check(2) == True

directory = PhoneDirectory(3)
assert directory.get() == 0
assert directory.get() == 1
assert directory.get() == 2
directory.release(0)
directory.release(2)
assert directory.check(0) == True
assert directory.get() == 0
