import heapq
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

=====================================

intervals = [(0, 7), (0, 5), (0, 7), (7, 10)]

0   5 7 10  15  20  30
3-----3
1----1
4-----4
      0--------------0
"""


class Interval:
    def __init__(self, start: int, end: int):
        self.start = start
        self.end = end


def min_meeting_rooms(intervals: List[Interval]) -> int:
    if len(intervals) == 0:
        return 0
    # time = O(nlogn), worst-space = O(n)
    intervals.sort(key=lambda interval: interval.start)
    heap = [intervals[0].end]
    for i in range(1, len(intervals)):  # O(n)
        if heap[0] <= intervals[i].start:  # if smallest_end <= smallest_start
            heapq.heappop(heap)  # O(logn)
        heapq.heappush(heap, intervals[i].end)  # O(logn)
    return len(heap)


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

intervals = [(0, 7), (0, 5), (0, 7), (7, 10)]
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 2

intervals = [[7, 15], [15, 20], [5, 10], [0, 7], [0, 30]]
interval_objects = [Interval(start, end) for start, end in intervals]
assert min_meeting_rooms(interval_objects) == 3
