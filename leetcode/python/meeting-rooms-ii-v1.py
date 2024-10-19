from collections import defaultdict
from typing import List


"""
intervals = [(0,30),(5,10),(15,20)]

0   5   10  15  20  30
0--------------------0
    1----1
            2----2

=====================================

intervals = [[7, 15], [15, 20], [5, 10], [0, 7], [0, 30]]

0   5 7 10  15  20  30
0--------------------0
3-----3
    1----1
      4-----4
            2----2

0 -> add 2 rooms, total 2 rooms
5 -> add 1 room, total 3 rooms
7 -> remove 1 room, add 1 room, total 3 rooms
10 -> remove 1 room, total 2 rooms
15 -> remove 1 room, add 1 room, total 2 rooms
20 -> remove 1 room, total 1 room
30 -> remove 1 room, total 0 rooms

n is the number of meetings
time = O(n) + O(nlogn) + O(n)
time = O(nlogn)
"""


class Interval:
    def __init__(self, start: int, end: int):
        self.start = start
        self.end = end


def min_meeting_rooms(intervals: List[Interval]) -> int:
    timestamps = set()
    start_time_to_meetings = defaultdict(int)
    end_time_to_meetings = defaultdict(int)
    for interval in intervals:
        timestamps.add(interval.start)
        timestamps.add(interval.end)
        start_time_to_meetings[interval.start] += 1
        end_time_to_meetings[interval.end] += 1

    timestamps = sorted(list(timestamps))
    curr_rooms = 0
    needed_rooms = 0
    for timestamp in timestamps:
        curr_rooms += start_time_to_meetings[timestamp] - \
            end_time_to_meetings[timestamp]
        needed_rooms = max(needed_rooms, curr_rooms)
    return needed_rooms


intervals = []
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 0

intervals = [[0, 1]]
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 1

intervals = [[0, 1], [0, 1]]
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 2

intervals = [[0, 1], [1, 2]]
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 1

intervals = [(5, 8), (6, 8)]
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 2

intervals = [[7, 15], [15, 20], [5, 10], [0, 7], [0, 30]]
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 3
