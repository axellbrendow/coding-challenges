import heapq

def maximum_value_of_each_subarray(array, k):
	if k <= 0: return []
	output = []
	heap = [(-array[i], i) for i in range(k - 1)]
	heapq.heapify(heap)
	for i in range(k - 1, len(array)):
		heapq.heappush(heap, (-array[i], i))
		val, index = heap[0]
		while index <= i - k:
			heapq.heappop(heap)
			val, index = heap[0]
		output.append(-val)
	return output

assert str(maximum_value_of_each_subarray([10, 5, 2, 7, 8, 7], 1)) == str([10, 5, 2, 7, 8, 7])

assert str(maximum_value_of_each_subarray([10, 5, 2, 7, 8, 7], 3)) == str([10, 7, 8, 8])
