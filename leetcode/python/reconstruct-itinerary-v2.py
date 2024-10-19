from collections import defaultdict

def build_graph(flights):
	graph = defaultdict(list)
	for src, dest in flights:
		graph[src].append(dest)
		if not dest in graph:
			graph[dest] = []
	return graph

def find_smallest_itinerary(start, graph, flights, visited):
	smallest = None
	itinerary = []
	def dfs(node, graph, visited):
		nonlocal smallest
		itinerary.append(node)
		for child in graph[node]:
			if visited[(node, child)] == 0: continue  # A flight can happen from src to dest multiple times
			visited[(node, child)] -= 1
			dfs(child, graph, visited)
			visited[(node, child)] += 1

		if (
			len(itinerary) == len(flights) + 1 and (
				smallest == None
				or itinerary < smallest
			)
		):
			smallest = itinerary.copy()

		itinerary.pop()

	dfs(start, graph, visited)
	return smallest

def find_lexicographically_smallest_itinerary(flights, start):
	graph = build_graph(flights)

	visited = defaultdict(int)
	for src, dest in flights:
		visited[(src, dest)] += 1  # A flight can happen from src to dest multiple times

	return find_smallest_itinerary(start, graph, flights, visited)

flights = [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
start = 'JFK'
output = ["JFK","NRT","JFK","KUL"]
assert find_lexicographically_smallest_itinerary(flights, start) == output

flights = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
start = 'JFK'
output = ["JFK","MUC","LHR","SFO","SJC"]
assert find_lexicographically_smallest_itinerary(flights, start) == output

flights = [["EZE","AXA"],["TIA","ANU"],["ANU","JFK"],["JFK","ANU"],["ANU","EZE"],["TIA","ANU"],["AXA","TIA"],["TIA","JFK"],["ANU","TIA"],["JFK","TIA"]]
start = 'JFK'
output = ["JFK","ANU","EZE","AXA","TIA","ANU","JFK","TIA","ANU","TIA","JFK"]
assert find_lexicographically_smallest_itinerary(flights, start) == output
