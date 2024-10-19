from typing import List

"""
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
