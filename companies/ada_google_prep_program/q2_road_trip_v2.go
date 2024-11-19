package main

/*-
You are on a budgeted road trip through a country with a grid of cities. Each city is represented by a cell
in a 2D grid, and the value in each cell indicates the cost of staying in that city for a day. You start at
the top-left corner of the grid (0, 0), always staying the first day in that city. You also start with a given
amount of money, and your goal is to reach the bottom-right corner (n-1,m-1) which has a cost of 0 since it's
your final destination.

You can only make one move per day, and you can move right, down or diagonally right-down at each step. Write
an algorithm to find the minimum total cost, so you can maximize your remaining budget, from the top-left to
the bottom-right corner. If you cannot reach the final destination with the given amount of money, return -1.

---

time complexity is 3^numCities
*/

const impossible = -1

var directions = [][]int{
	{0, 1}, // RIGHT
	{1, 1}, // RIGHT DOWN
	{1, 0}, // DOWN
}

func findMinCostRec(
	citiesCost [][]int,
	nlines int,
	ncols int,
	budget int,
	spent int,
	i int,
	j int,
) int {
	if !(0 <= i && i < nlines && 0 <= j && j < ncols) {
		return impossible
	}
	spent += citiesCost[i][j]
	if spent > budget {
		return impossible
	}
	if i == nlines-1 && j == ncols-1 {
		return 0
	}
	minCost := impossible
	for _, direction := range directions {
		newI, newJ := i+direction[0], j+direction[1]
		cost := findMinCostRec(citiesCost, nlines, ncols, budget, spent, newI, newJ)
		if cost == impossible {
			continue
		}
		cost += citiesCost[i][j]
		if cost != impossible && (minCost == impossible || cost < minCost) {
			minCost = cost
		}
	}
	return minCost
}

func findMinCost(citiesCost [][]int, budget int) int {
	nlines, ncols := len(citiesCost), len(citiesCost[0])
	return findMinCostRec(citiesCost, nlines, ncols, budget, 0, 0, 0)
}

func main() {
	if findMinCost(
		[][]int{
			{1, 2, 3},
			{3, 1, 2},
			{2, 3, 0},
		},
		2,
	) != 2 {
		panic("")
	}

	if findMinCost(
		[][]int{
			{1, 2, 3},
			{3, 1, 2},
			{2, 3, 0},
		},
		1,
	) != -1 {
		panic("")
	}

	if findMinCost(
		[][]int{
			{1, 1, 1},
			{1, 4, 1},
			{1, 2, 0},
		},
		3,
	) != 3 {
		panic("")
	}

	if findMinCost(
		[][]int{
			{1, 1, 1},
			{1, 4, 2},
			{1, 1, 0},
		},
		3,
	) != 3 {
		panic("")
	}

	if findMinCost(
		[][]int{
			{1, 4, 9, 10},
			{4, 4, 9, 20},
			{8, 8, 1, 9},
			{8, 8, 9, 0},
		},
		9,
	) != 6 {
		panic("")
	}

	if findMinCost(
		[][]int{
			{1, 1, 1, 10},
			{1, 4, 9, 1},
			{1, 8, 1, 1},
			{8, 1, 1, 0},
		},
		5,
	) != 5 {
		panic("")
	}

	if findMinCost(
		[][]int{
			{1, 4, 20, 10},
			{2, 4, 20, 20},
			{2, 2, 9, 1},
			{2, 2, 9, 0},
		},
		300,
	) != 14 {
		panic("")
	}
}
