from collections import deque

def get_best_path_from_previous_paths(costs_queue, paths_queue):
	best_pos = 0
	min_cost = costs_queue[0]
	for cost_i in range(1, len(costs_queue)):
		if (
			costs_queue[cost_i] < min_cost
			or (
				costs_queue[cost_i] == min_cost
				and (
					len(paths_queue[cost_i]) > len(paths_queue[best_pos])
					or paths_queue[cost_i] < paths_queue[best_pos]
				)
			)
		):
			min_cost = costs_queue[cost_i]
			best_pos = cost_i
	return best_pos

def get_min_cost_path(A, steps):
	paths_queue = deque()
	costs_queue = deque()
	paths_queue.append([1])
	costs_queue.append(A[0])
	i = 1
	while i < len(A):
		while len(paths_queue) > 0 and paths_queue[0][-1] + steps < i + 1:
			paths_queue.popleft()
			costs_queue.popleft()
		if len(paths_queue) == 0 or len(costs_queue) == 0:
			return []
		if A[i] != -1:
			best_pos = get_best_path_from_previous_paths(costs_queue, paths_queue)
			new_path = [*paths_queue[best_pos], i + 1]
			paths_queue.append(new_path)
			costs_queue.append(costs_queue[best_pos] + A[i])
		i += 1
	return paths_queue[-1]

A = [0,0,0,0,0,0]
steps = 3
assert get_min_cost_path(A, steps) == [1,2,3,4,5,6]

A = [1,2,4,-1,2]
steps = 2
assert get_min_cost_path(A, steps) == [1,3,5]
