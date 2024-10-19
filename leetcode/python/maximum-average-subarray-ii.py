from collections import deque

def get_max_average(nums, k):
	queue = deque()
	curr_sum = 0
	curr_size = k
	for i in range(k):
		curr_sum += nums[i]
		queue.append(nums[i])

	max_average = curr_sum / curr_size

	for i in range(k, len(nums)):
		while (
			curr_size > k
			and (curr_sum - queue[0]) / (curr_size - 1) >= curr_sum / curr_size
		):
			curr_sum -= queue.popleft()
			curr_size -= 1
			max_average = max(max_average, curr_sum / curr_size)
		queue.append(nums[i])
		curr_sum += nums[i]
		curr_size += 1
		max_average = max(max_average, curr_sum / curr_size)
	return max_average

assert get_max_average([1,12,-5,-6,50,3], 4) == 12.75
