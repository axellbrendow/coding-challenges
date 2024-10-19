def max_rectangle_area(heights):
	heights.append(0)
	stack = [-1]
	max_area = 0
	for i, height in enumerate(heights):
		while len(stack) > 0 and height < heights[stack[-1]]:
			h = heights[stack.pop()]
			w = i - stack[-1] - 1
			max_area = max(max_area, h * w)
		stack.append(i)
	heights.pop()
	return max_area

max_rectangle_area([2, 1, 0, 2])
