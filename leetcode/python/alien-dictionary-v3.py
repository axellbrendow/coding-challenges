import heapq

# Correct solution and returns the smallest lexicographically answer

def create_graph(words):
	graph = {c: [] for word in words for c in word}
	for i in range(len(words) - 1):
		w1, w2 = words[i], words[i + 1]
		min_len = min(len(w1), len(w2))
		if w1[:min_len] == w2[:min_len] and len(w1) > len(w2):
			return ''
		for j in range(min_len):
			if w1[j] != w2[j]:
				graph[w1[j]].append(w2[j])
				break
	return graph

def get_indegrees(graph):
	indegrees = {node: 0 for node in graph}
	for node in graph:
		for child in graph[node]:
			indegrees[child] += 1
	return indegrees

def topological_sort(graph):
	indegrees = get_indegrees(graph)
	heap = [c for c in graph if indegrees[c] == 0]
	heapq.heapify(heap)
	order = ''
	while len(heap) > 0:
		c = heapq.heappop(heap)
		order += c
		for node in graph[c]:
			indegrees[node] -= 1
			if indegrees[node] == 0:
				heapq.heappush(heap, node)
	return '' if len(order) != len(graph) else order

def find_order(words):
	graph = create_graph(words)
	if graph == '': return ''
	return topological_sort(graph)

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
