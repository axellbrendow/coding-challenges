def build_graph(num_nodes, edges):
	graph = {i: [] for i in range(num_nodes)}
	for edge in edges:
		graph[edge[0]].append(edge[1])
		graph[edge[1]].append(edge[0])
	return graph

def not_valid_tree(graph):
	visited = [False] * len(graph)

	def has_cycle(node, parent):
		visited[node] = True
		for child in graph[node]:
			if child == parent: continue
			if visited[child]: return True
			if has_cycle(child, node): return True
		return False

	return has_cycle(0, None) or not all(visited)

def is_valid_tree(num_nodes, edges):
	if len(edges) != num_nodes - 1: return False
	graph = build_graph(num_nodes, edges)
	return not not_valid_tree(graph)

assert is_valid_tree(1, []) == True
assert is_valid_tree(5, [[0, 1], [0, 0], [2, 3]]) == False
assert is_valid_tree(5, [[0, 1], [0, 2], [0, 3], [1, 4]]) == True
assert is_valid_tree(5, [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]) == False
assert is_valid_tree(10, [[0,1],[5,6],[6,7],[9,0],[3,7],[4,8],[1,8],[5,2],[5,3]]) == False