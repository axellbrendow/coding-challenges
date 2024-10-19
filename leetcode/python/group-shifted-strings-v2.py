from collections import defaultdict

def hash_str(string):
	return tuple([(ord(c) - ord(string[0])) % 26 for c in string])

def group_shifted_strings(strings):  # ['', 'a', '', 'b', 'az', 'ba']
	buckets = defaultdict(list)
	for string in strings:
		buckets[hash_str(string)].append(string)
	return list(buckets.values())

strings = ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
output = [
  ["abc","bcd","xyz"],
  ["acef"],
  ["az","ba"],
  ["a","z"]
]
assert group_shifted_strings(strings) == output
