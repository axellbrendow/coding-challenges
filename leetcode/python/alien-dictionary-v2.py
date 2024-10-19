# Correct solution but returns any valid answer

def create_graph(words):
	graph = {c: set() for word in words for c in word}
	for i in range(len(words) - 1):
		w1, w2 = words[i], words[i + 1]
		min_len = min(len(w1), len(w2))
		if w1[:min_len] == w2[:min_len] and len(w1) > len(w2):
			return ''
		for j in range(min_len):
			if w1[j] != w2[j]:
				graph[w1[j]].add(w2[j])
				break
	return graph

def find_order(words):
	graph = create_graph(words)
	print(graph)
	if graph == '': return ''

	output = []
	visited = {}  # False means visited and True means visited and on the current path

	def dfs(node):
		if node in visited: return visited[node]
		visited[node] = True
		for child in graph[node]:
			if dfs(child): return True
		visited[node] = False
		output.append(node)

	for node in graph:
		if dfs(node): return ''

	return ''.join(reversed(output))

words = ['z','x']
output = 'zx'
assert find_order(words) == output

words = ['zz','z']
output = ''
assert find_order(words) == output

words = ['z','x', 'z']
output = ''
assert find_order(words) == output

words = ["wrt","wrf","er","ett","rftt"]
output = 'wertf'
assert find_order(words) == output
