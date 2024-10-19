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

Algorithm:

          *
starts = [0, 0, 5, 7, 15]
        *
ends = [7, 10, 15, 20, 30]

start < end, so add 1 rooms

---

             *
starts = [0, 0, 5, 7, 15]
        *
ends = [7, 10, 15, 20, 30]

start < end, so add 1 rooms

---

                *
starts = [0, 0, 5, 7, 15]
        *
ends = [7, 10, 15, 20, 30]

start < end, so add 1 rooms

---

                   *
starts = [0, 0, 5, 7, 15]
        *
ends = [7, 10, 15, 20, 30]

start == end, move both pointers

---

                      *
starts = [0, 0, 5, 7, 15]
           *
ends = [7, 10, 15, 20, 30]

end < start, remove 1 room

---

                      *
starts = [0, 0, 5, 7, 15]
               *
ends = [7, 10, 15, 20, 30]

end == start, move both pointers

END

---

n is the number of meetings
time = 2 O(nlogn) + 2 O(n)
time = O(nlogn)
"""


class Interval:
    def __init__(self, start: int, end: int):
        self.start = start
        self.end = end


def min_meeting_rooms(intervals: List[Interval]) -> int:
    starts_pos, ends_pos = 0, 0
    starts = sorted([interval.start for interval in intervals])
    ends = sorted([interval.end for interval in intervals])
    curr_rooms = 0
    needed_rooms = 0
    while starts_pos < len(starts):
        if starts[starts_pos] == ends[ends_pos]:
            starts_pos += 1
            ends_pos += 1
        elif starts[starts_pos] < ends[ends_pos]:
            curr_rooms += 1
            starts_pos += 1
        else:
            curr_rooms -= 1
            ends_pos += 1
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
