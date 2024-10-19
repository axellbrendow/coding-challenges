from collections import deque

# Can window_size be <= 0 ?
def moving_average(stream, window_size):
	total = 0
	q = deque()
	for elem in stream:
		if len(q) == window_size:
			total -= q.popleft()
		total += elem
		q.append(elem)
		print(total / len(q))

moving_average([1], 9)
print()
moving_average([1, 2], 9)
print()
moving_average([1, 2], 1)
print()
moving_average([1, 2, 3], 3)
print()
moving_average([1, 2, 3], 2)
