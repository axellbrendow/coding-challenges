from collections import deque

def subarray_with_sum_at_least_k(nums, k):
	if len(nums) == 0: return -1
	if k == 0: return 0
	prefix_sum = [0] * (len(nums) + 1)
	for i in range(1, len(nums) + 1):
		# prefix_sum[i] is the prefix sum until i - 1 on nums
		prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1]
	queue = deque([0])
	curr_sum, min_len = 0, float('inf')
	for i in range(len(nums)):
		curr_sum += nums[i]
		while len(queue) > 0 and curr_sum - prefix_sum[queue[0]] >= k:
			min_len = min(min_len, i + 1 - queue.popleft())
		while len(queue) > 0 and curr_sum <= prefix_sum[queue[-1]]:
			'''
			If current sum is smaller, I can remove the previos bigger sums because
			when I subtract future values with the current sum, I will get bigger differences that
			help me achieve the goal K
			prefix_sum[queue[-1]] <- prefix_sum[i] <- ... <- prefix_sum[future id]
			prefix_sum[future id] - prefix_sum[queue[-1]] >= k && prefix_sum[queue[-1]] >= prefix_sum[i]
			prefix_sum[future id] - prefix_sum[i] >= k too
			'''
			queue.pop()
		queue.append(i + 1)
	return -1 if min_len == float('inf') else min_len

assert subarray_with_sum_at_least_k([], 0) == -1
assert subarray_with_sum_at_least_k([1], 0) == 0
assert subarray_with_sum_at_least_k([1], 1) == 1
assert subarray_with_sum_at_least_k([1], 2) == -1
assert subarray_with_sum_at_least_k([1, 1], 2) == 2
assert subarray_with_sum_at_least_k([1, 1, 4], 5) == 2
assert subarray_with_sum_at_least_k([2, -1, 4], 5) == 3
assert subarray_with_sum_at_least_k([2, -1, 4], 4) == 1
assert subarray_with_sum_at_least_k([2, -1, 4, 1], 5) == 2

# Exceptional comment https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C++JavaPython-O(N)-Using-Deque/195904
