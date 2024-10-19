def get_amount_of_trapped_water(elevation_map):
	stack = [0]
	amount = 0
	for i in range(1, len(elevation_map)):
		while len(stack) > 0 and elevation_map[stack[-1]] < elevation_map[i]:
			top = stack.pop()
			if len(stack) == 0: break
			distance = i - stack[-1] - 1
			fill_height = min(
				elevation_map[i],
				elevation_map[stack[-1]]
			) - elevation_map[top]
			amount += distance * fill_height
		stack.append(i)
	return amount

assert get_amount_of_trapped_water([7,0,5,3,2,9]) == 18
assert get_amount_of_trapped_water([0,1,0,2,1,0,1,3,2,1,2,1]) == 6
