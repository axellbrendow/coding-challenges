from collections import defaultdict

def build_graph(similar_words):
	graph = defaultdict(set)
	for w0, w1 in similar_words:
		graph[w0].add(w1)
		graph[w1].add(w0)
	return graph

def are_similar(words1, words2, similar_words):
	if len(words1) != len(words2): return False
	if len(words1) == 0: return True
	graph = build_graph(similar_words)
	visited = None

	def dfs(node, end):
		if node == end: return True
		visited[node] = True
		for child in graph[node]:
			if visited[child]: continue
			if dfs(child, end): return True
		return False

	for i in range(len(words1)):
		visited = {node: False for node in graph}
		if words1[i] == words2[i]: continue
		if not dfs(words1[i], words2[i]): return False
	return True

assert are_similar(
	["great"],
	["great"],
	[]
)

assert are_similar(
	["great", "acting", "skills"],
	["fine", "drama", "talent"],
	[["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]]
)
