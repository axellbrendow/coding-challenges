from collections import defaultdict

def build_graph(seqs):
	graph = defaultdict(list)
	for seq in seqs:
		if len(seq) == 1: graph[seq[0]] = []
		else:
			for i in range(1, len(seq)):
				graph[seq[i - 1]].append(seq[i])
				if not seq[i] in graph:
					graph[seq[i]] = []
	return graph

def find_root(graph):
	incoming = {node: 0 for node in graph}
	for node in graph:
		for child in graph[node]:
			incoming[child] += 1
	for node in incoming:
		if incoming[node] == 0: return node
	return None

def check_if_theres_a_path_with_all_nodes(graph, org):
	root = find_root(graph)
	if not root: return False
	visited = {node: False for node in graph}
	path = []

	def dfs(node):
		if visited[node]: return None
		visited[node] = True
		path.append(node)
		if len(graph[node]) == 0:
			found = all([visited[node] for node in visited])
			visited[node] = False
			old_path = path.copy() if found else None
			path.pop()
			return old_path
		else:
			for child in graph[node]:
				found_path = dfs(child)
				if found_path: return found_path
			visited[node] = False
			path.pop()
		return None

	return dfs(root) == org

def is_uniquely_reconstructable_from(seqs, org):
	graph = build_graph(seqs)
	if len(org) == 0 and len(graph) == 0: return True
	return check_if_theres_a_path_with_all_nodes(graph, org)

org = []
seqs = [[]]
assert is_uniquely_reconstructable_from(seqs, org) == True

org = [1]
seqs = [[1]]
assert is_uniquely_reconstructable_from(seqs, org) == True

org = [1,2,3]
seqs = [[1,2],[1,3]]
assert is_uniquely_reconstructable_from(seqs, org) == False

org = [1,2,3]
seqs = [[3,2],[2,1]]
assert is_uniquely_reconstructable_from(seqs, org) == False

org = [1,2,3]
seqs = [[1,2]]
assert is_uniquely_reconstructable_from(seqs, org) == False

org = [1,2,3]
seqs = [[1,2],[1,3],[2,3]]
assert is_uniquely_reconstructable_from(seqs, org) == True

org = [4,1,5,2,6,3]
seqs = [[5,2,6,3],[4,1,5,2]]
assert is_uniquely_reconstructable_from(seqs, org) == True
