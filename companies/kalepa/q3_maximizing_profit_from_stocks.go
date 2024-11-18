package main

func maximumProfit(price []int) int {
	var profit, maxPrice = 0, price[len(price)-1]
	for i := len(price) - 2; i >= 0; i-- {
		if price[i] >= maxPrice {
			maxPrice = price[i]
		} else {
			profit += maxPrice - price[i]
		}
	}
	return profit
}

func main() {
	if maximumProfit([]int{5, 3, 2}) != 0 {
		panic("")
	}

	if maximumProfit([]int{1, 2, 100}) != 197 {
		panic("")
	}

	if maximumProfit([]int{1, 3, 1, 2}) != 3 {
		panic("")
	}
}
