def shifted_strings(str1, str2):  # 'az', 'ba'
	if len(str1) != len(str2): return False
	if len(str1) == 0: return True
	diff = (ord(str1[0]) - ord(str2[0])) % 26  # ‘a’ - ‘b’ = -1 % 26 = 25
	for i in range(1, len(str1)):  # i = 2  ‘z’ - ‘a’ = 26 - 1 = 25
		if (ord(str1[i]) - ord(str2[i])) % 26 != diff: return False
	return True

def group_shifted_strings(strings):  # ['', 'a', '', 'b', 'az', 'ba']
	grouped = [False] * len(strings)  # [T, T, T, T, T, F]
	groups = []
	for i in range(len(strings)):  # i = 4
		if grouped[i]: continue
		grouped[i] = True
		group = [strings[i]]  # ['az']
		for j in range(i + 1, len(strings)):  # j = 5
			if grouped[j]: continue
			if shifted_strings(strings[i], strings[j]):
				grouped[j] = True
				group.append(strings[j])
		groups.append(group)
	return groups

strings = ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
output = [
  ["abc","bcd","xyz"],
  ["acef"],
  ["az","ba"],
  ["a","z"]
]
assert group_shifted_strings(strings) == output
