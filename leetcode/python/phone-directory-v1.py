# This version gives available phone numbers but not in order

class PhoneDirectory:
	def __init__(self, num_phones):
		self.num_phones = num_phones
		self.available = {phone for phone in range(num_phones)}
		self.unavailable = set()

	def check(self, phone):
		return phone in self.available

	def release(self, phone):
		if phone in self.unavailable:
			self.unavailable.remove(phone)
			self.available.add(phone)

	def _get_first_available(self):
		for phone in self.available:
			return phone
		return -1

	def get(self):
		phone = self._get_first_available()
		if phone == -1: return -1
		self.available.remove(phone)
		self.unavailable.add(phone)
		return phone

directory = PhoneDirectory(3)
directory.get()
directory.get()
directory.check(2)
directory.get()
directory.check(2)
directory.release(2)
directory.check(2)
