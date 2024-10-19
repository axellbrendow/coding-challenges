def get_num_of_ways_you_can_paint(num_posts, num_colors):  # 1, 2
	cache = {}
	def dfs(post_num, prev_colors):  # 0, [-2, -1]
		if post_num >= num_posts: return 1
		count = 0  # 2
		for color in range(num_colors):  # color = 2
			if prev_colors[0] == prev_colors[1] and color == prev_colors[0]:
				continue
			if not (post_num, color, str(prev_colors)) in cache:
				cache[(post_num, color, str(prev_colors))] = dfs(
                    post_num + 1,
                    [prev_colors[1], color]
                ) # 1
			count += cache[(post_num, color, str(prev_colors))]
		return count  # 2
	return dfs(0, [-2, -1])  # 2

assert get_num_of_ways_you_can_paint(1, 1) == 1
assert get_num_of_ways_you_can_paint(2, 1) == 1
assert get_num_of_ways_you_can_paint(2, 2) == 4
assert get_num_of_ways_you_can_paint(2, 3) == 9
assert get_num_of_ways_you_can_paint(3, 2) == 6
assert get_num_of_ways_you_can_paint(7, 6) == 246750
