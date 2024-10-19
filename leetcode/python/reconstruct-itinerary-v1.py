from collections import defaultdict

def dfs(node, graph, visited):
	itineraries = []
	for child in graph[node]:
		if visited[(node, child)] == 0: continue
		visited[(node, child)] -= 1
		child_itineraries = dfs(child, graph, visited)
		child_itineraries = [[node, *itinerary] for itinerary in child_itineraries]
		itineraries = [*itineraries, *child_itineraries]
		visited[(node, child)] += 1
	return itineraries if len(itineraries) > 0 else [[node]]

def build_graph(flights):
	graph = defaultdict(list)
	for src, dest in flights:
		graph[src].append(dest)
		if not dest in graph:
			graph[dest] = []
	return graph

def find_lexicographically_smallest(itineraries, flights):
	smallest = None
	for i in range(len(itineraries)):
		if (
			len(itineraries[i]) == len(flights) + 1 and (
				smallest == None
				or itineraries[i] < smallest
			)
		):
			smallest = itineraries[i]
	return smallest

def find_lexicographically_smallest_itinerary(flights, start):
	graph = build_graph(flights)
	visited = defaultdict(int)
	for src, dest in flights:
		visited[(src, dest)] += 1
	itineraries = dfs(start, graph, visited)
	return find_lexicographically_smallest(itineraries, flights)

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
