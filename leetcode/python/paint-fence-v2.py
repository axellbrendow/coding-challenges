# https://www.youtube.com/watch?v=deh7UpSRaEY

def get_num_of_ways_you_can_paint(num_posts, num_colors):  # 1, 2
	if num_posts == 0: return 0
	if num_posts == 1: return num_colors
	if num_colors == 0: return 0
	same = num_colors  # Number of ways I can paint the posts with the same color
	diff = num_colors * (num_colors - 1)  # Number of ways I can paint the posts with different colors
	for _ in range(3, num_posts + 1):
		# The number of ways I could paint with different colors in the past will
		# be exactly the number of ways I can paint with the same color now because
		# for each of them I have only 1 choice to make the colors the same
		new_same = diff * 1
		# The number of ways I can paint with different colors the next post is just num_colors - 1
		diff = (num_colors - 1) * (diff + same)
		same = new_same
	# 'diff' counts all the ways to paint differently and 'same' counts all the ways to paint the same
	return diff + same

assert get_num_of_ways_you_can_paint(1, 1) == 1
assert get_num_of_ways_you_can_paint(2, 1) == 1
assert get_num_of_ways_you_can_paint(2, 2) == 4
assert get_num_of_ways_you_can_paint(2, 3) == 9
assert get_num_of_ways_you_can_paint(3, 2) == 6
assert get_num_of_ways_you_can_paint(7, 6) == 246750
