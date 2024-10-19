def get_x_of_middle_line(points):
	n = len(points)
	if n % 2 == 1:
		return points[n // 2 + 1][0]
	else:
		return (points[n // 2][0] + points[n // 2 - 1][0]) / 2

def reflects_all_the_points(points, x_of_middle_line):
	points_set = {(x, y) for x, y in points}
	for x, y in points:
		if x >= x_of_middle_line: return True
		reflected_x = x_of_middle_line + (x_of_middle_line - x)
		if not (reflected_x, y) in points_set: return False
	return True

def find_line_that_reflects_the_points(points):
	if len(points) == 0: return True
	points.sort()
	x_of_middle_line = get_x_of_middle_line(points)
	return reflects_all_the_points(points, x_of_middle_line)

points = [[-1,1],[1,1]]
assert find_line_that_reflects_the_points(points) == True

points = [[1,1],[-1,-1]]
assert find_line_that_reflects_the_points(points) == False

points = [[1,1],[-3,1]]
assert find_line_that_reflects_the_points(points) == True
