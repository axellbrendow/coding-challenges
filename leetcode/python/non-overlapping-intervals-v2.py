from typing import List

"""
The heuristic is: always pick the interval with the earliest end time. Then you can get
the maximal number of non-overlapping intervals. (or minimal number to remove). That
happens because the interval with the earliest end time produces the maximal capacity.
E.g. Suppose current earliest end time is x. Then available time slot left for other
intervals is [x:]. If we choose another interval with end time y, then available time
slot would be [y:]. Since x <= y, there is no way [y:] can hold more intervals then [x:].
Thus, the heuristic holds.
"""


def eraseOverlapIntervals(intervals: List[List[int]]) -> int:
    earlist_end, count = float('-inf'), 0
    for start, end in sorted(intervals, key=lambda interval: interval[1]):
        if earlist_end <= start:
            earlist_end = end
        else:
            count += 1
    return count


intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]
assert eraseOverlapIntervals(intervals) == 1

intervals = [[1, 2], [1, 2], [1, 2]]
assert eraseOverlapIntervals(intervals) == 2

intervals = [[1, 2], [2, 3]]
assert eraseOverlapIntervals(intervals) == 0

intervals = [[0, 2], [1, 3], [1, 3], [2, 4], [3, 5], [3, 5], [4, 6]]
assert eraseOverlapIntervals(intervals) == 4
