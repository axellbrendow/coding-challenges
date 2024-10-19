import heapq

def get_num_nodes(transactions):
	num_nodes = 0
	for transaction in transactions:
		num_nodes = max(num_nodes, transaction[0] + 1, transaction[1] + 1)
	return num_nodes

def build_graph(transactions):
	num_nodes = get_num_nodes(transactions)
	graph = [[0 for _ in range(num_nodes)] for _ in range(num_nodes)]
	for transaction in transactions:
		graph[transaction[0]][transaction[1]] = transaction[2]
	return graph

def create_heaps(graph):
	positive_heap = []
	negative_heap = []
	for node in range(len(graph)):
		coming_out = sum(graph[node])
		coming_in = sum([graph[i][node] for i in range(len(graph))])
		balance = coming_in - coming_out
		if balance > 0:
			heapq.heappush(positive_heap, (balance, node))
		elif balance < 0:
			heapq.heappush(negative_heap, (balance, node))
	return positive_heap, negative_heap

def get_min_num_of_transactions(transactions):
	graph = build_graph(transactions)
	positive_heap, negative_heap = create_heaps(graph)
	num_transactions = 0
	while len(positive_heap) > 0:
		positive_balance, positive_node = heapq.heappop(positive_heap)
		negative_balance, negative_node = heapq.heappop(negative_heap)
		amount = min(positive_balance, -negative_balance)
		positive_balance -= amount
		negative_balance += amount
		num_transactions += 1
		if positive_balance > 0:
			heapq.heappush(positive_heap, (positive_balance, positive_node))
		if negative_balance < 0:
			heapq.heappush(negative_heap, (negative_balance, negative_node))
	return num_transactions

transactions = [[0, 1, 10], [2, 0, 5]]
assert get_min_num_of_transactions(transactions) == 2

transactions = [[0, 1, 6], [1, 2, 2], [2, 0, 4], [2, 3, 7], [3, 1, 4], [4, 0, 1]]
assert get_min_num_of_transactions(transactions) == 4
