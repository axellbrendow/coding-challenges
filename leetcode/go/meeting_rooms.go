package main

import "slices"

/*-
https://www.lintcode.com/problem/920/

Given an array of meeting time intervals consisting of start and end times [(s1,e1),(s2,e2),...] (si < ei),
determine if a person could attend all meetings.

Example1
Input: intervals = [(0,30),(5,10),(15,20)]
Output: false
Explanation:
(0,30), (5,10) and (0,30),(15,20) will conflict

Example2
Input: intervals = [(5,8),(9,15)]
Output: true
Explanation:
Two times will not conflict
*/

type Interval struct {
	start int
	end   int
}

func canAttendMeetings(intervals []Interval) bool {
	slices.SortFunc(intervals, func(interval1, interval2 Interval) int { return interval1.start - interval2.start })
	for i := 0; i < len(intervals)-1; i++ {
		if !(intervals[i].end <= intervals[i+1].start) {
			return false
		}
	}
	return true
}

func main() {
	if canAttendMeetings([]Interval{}) != true {
		panic("")
	}

	if canAttendMeetings([]Interval{{5, 8}, {9, 15}}) != true {
		panic("")
	}

	if canAttendMeetings([]Interval{{0, 30}, {5, 10}, {15, 20}}) != false {
		panic("")
	}
}
