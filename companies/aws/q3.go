package main

import "math"

/*-
Data analysts at Amazon are studying the trends in movies and shows popular on Prime Video in order to
enhance the user experience.

They have identified the best critic-rated and the best audience-rated web series, represented by integers
IDs series1, and series2. They also define the watch score of a contiguous period of some days as the
number of distinct series watched by a viewer during that period.

Given an array watch_history, of size n, that represents the web series watched by a viewer over a period
of n days, and two integers, series1 and series2, report the minimum watch score of a contiguous period of
days in which both series1 and series2 have been viewed at least once. If series1 and series2 are the same
value, one occurrence during the period is sufficient.

Example 1:
n = 5
series1 = 1
series2 = 2
watch_history = [1,3,2,1,4]
The contiguous periods of days in which series1 and series2 have been viewed at least once are:
[1,3,2] -> 3
[3,2,1] -> 3
[2,1] -> 2 (min watch score)
[1,3,2,1] -> 3
[2,1,4] -> 3
[1,3,2,1,4] -> 4

watch_history = [1,3,1,2,1,4]
                 i
                 j
window = { 1: [0] }

watch_history = [1,3,1,2,1,4]
                 i
                   j
window = { 1: [0], 3: [1] }

watch_history = [1,3,1,2,1,4]
                 i
                     j
window = { 1: [0, 2], 3: [1] }

watch_history = [1,3,1,2,1,4]
                 i
                       j
window = { 1: [0, 2], 3: [1], 2: [3] }

watch_history = [1,3,1,2,1,4]
                   i
                       j
window = { 1: [2], 3: [1], 2: [3] }

watch_history = [1,3,1,2,1,4]
                     i
                       j
window = { 1: [2], 2: [3] }

watch_history = [1,3,1,2,1,4]
                       i
                       j
window = { 2: [3] }

watch_history = [1,3,1,2,1,4]
                       i
                         j
window = { 1: [4], 2: [3] }

watch_history = [1,3,1,2,1,4]
                         i
                         j
window = { 1: [4] }

watch_history = [1,3,1,2,1,4]
                         i
                           j
window = { 1: [4], 4: [5] }

watch_history = [1,3,1,2,1,4]
                         i
                             j
window = { 1: [4], 4: [5] }

Example 2:
n = 5
series1 = 1
series2 = 3
watch_history = [1,2,2,2,3]
The contiguous periods of days in which series1 and series2 have been viewed at least once are:
[1,2,2,2,3] -> 3
min watch score = 3
*/

func getMinScore(watchHistory []int, series1 int, series2 int) int {
	seriesMap := make(map[int][]int)
	i, j, minWatchScore := 0, 0, math.MaxInt32

	for j < len(watchHistory) {
		if _, inMap := seriesMap[watchHistory[j]]; !inMap {
			seriesMap[watchHistory[j]] = make([]int, 0)
		}
		seriesMap[watchHistory[j]] = append(seriesMap[watchHistory[j]], j)

		_, series1Exists := seriesMap[series1]
		_, series2Exists := seriesMap[series2]
		for series1Exists && series2Exists {
			minWatchScore = min(minWatchScore, len(seriesMap))

			serieQueue := seriesMap[watchHistory[i]]
			if len(serieQueue) == 1 {
				delete(seriesMap, watchHistory[i])
			} else {
				seriesMap[watchHistory[i]] = serieQueue[1:]
			}

			i++
			_, series1Exists = seriesMap[series1]
			_, series2Exists = seriesMap[series2]
		}

		j++
	}

	if minWatchScore != math.MaxInt32 {
		return minWatchScore
	} else {
		return 0
	}
}

func main() {
	if getMinScore([]int{1, 3, 2, 1, 4}, 4, 4) != 1 {
		panic("")
	}
	if getMinScore([]int{1, 3, 2, 1, 4}, 1, 2) != 2 {
		panic("")
	}
	if getMinScore([]int{1, 3, 1, 0, 2, 1, 4}, 1, 2) != 2 {
		panic("")
	}
	if getMinScore([]int{1, 2, 2, 2, 3}, 1, 3) != 3 {
		panic("")
	}
}
