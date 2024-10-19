from collections import deque

def rearrange(string, distance):
	output = ''
	queue = deque()
	hashmap = {}

	def remove_keys():
		keys_to_remove = [key for key in hashmap if hashmap[key] <= len(output) - distance]
		for key in keys_to_remove: del hashmap[key]

	def try_to_remove_from_queue():
		nonlocal output
		count = 0
		for j in range(len(queue)):
			if queue[j] in hashmap: break
			output += queue[j]
			hashmap[queue[j]] = len(output) - 1
			count += 1
		for _ in range(count):
			queue.popleft()
		remove_keys()
		if count > 0: try_to_remove_from_queue()
	
	for i in range(len(string)):
		remove_keys()
		try_to_remove_from_queue()
		if not string[i] in hashmap:
			output += string[i]
			hashmap[string[i]] = len(output) - 1
		else:
			queue.append(string[i])

	remove_keys()
	try_to_remove_from_queue()
	return output if len(output) == len(string) else ''

assert rearrange('aabbcc', 3) == 'abcabc'
