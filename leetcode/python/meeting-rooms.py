from typing import List

"""
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

---

intervals = [(0,30),(5,10),(15,20)]

0   5   10  15  20  30
0--------------------0
    1----1
            2----2
"""


class Interval:
    def __init__(self, start, end):
        self.start = start
        self.end = end


def can_attend_meetings(intervals: List[Interval]) -> bool:
    intervals.sort(key=lambda interval: interval.start)
    for i in range(len(intervals) - 1):
        if not (intervals[i].end <= intervals[i + 1].start):
            return False
    return True


intervals = [(0, 30), (5, 10), (15, 20)]
interval_objects = [Interval(start, end) for start, end in intervals]
assert can_attend_meetings(interval_objects) == False

intervals = [(5, 8), (9, 15)]
interval_objects = [Interval(start, end) for start, end in intervals]
assert can_attend_meetings(interval_objects) == True
