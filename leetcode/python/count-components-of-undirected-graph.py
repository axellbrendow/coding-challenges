def build_graph(num_nodes, edges):  # 5, [[0, 1], [1, 2], [3, 4]]
	graph = {node: [] for node in range(num_nodes)}
	for src, dest in edges:  # src = 0, to = 1
		graph[src].append(dest)
		graph[dest].append(src)
	return graph

def count_components(num_nodes, graph):  # 5
	count = 0  # 1
	visited = [False] * num_nodes  # [True, True, True, False, False]

	def dfs(node):
		visited[node] = True
		for child in graph[node]:
			if visited[child]: continue
			dfs(child)

	for node in graph:  # node = 3
		if visited[node]: continue
		dfs(node)  #
		count += 1
	return count

def get_num_of_connected_components(num_nodes, edges):  # 5, [[0, 1], [1, 2], [3, 4]]
	graph = build_graph(num_nodes, edges)
	return count_components(num_nodes, graph)

num_nodes = 5
edges = [[0, 1], [1, 2], [3, 4]]
assert get_num_of_connected_components(num_nodes, edges) == 2

num_nodes = 5
edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
assert get_num_of_connected_components(num_nodes, edges) == 1
