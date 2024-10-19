# WRONG ANSWER

from collections import defaultdict
from typing import List

"""
This is a greedy approach that tries to remove the meetings that have the most conflicts
but that doesn't give the correct answer for all inputs. It fails for this case:

[[0, 2], [1, 3], [1, 3], [2, 4], [3, 5], [3, 5], [4, 6]]

0   1   2   3   4   5   6
---------
    ---------
    ---------
        ---------
            ---------
            ---------
                ----------

It says you should remove 5 meetings but actually you can remove 4.
"""


def conflict(interval1: List[int], interval2: List[int]):
    return max(interval1[0], interval2[0]) < min(interval1[1], interval2[1])


def eraseOverlapIntervals(intervals: List[List[int]]) -> int:
    meetings_and_conflicts = defaultdict(set)
    for i in range(len(intervals)):
        for j in range(i + 1, len(intervals)):
            if conflict(intervals[i], intervals[j]):
                meetings_and_conflicts[i].add(j)
                meetings_and_conflicts[j].add(i)
    count = 0
    while any([len(meetings_and_conflicts[meeting]) > 0 for meeting in meetings_and_conflicts]):
        _, meeting = max([(len(meetings_and_conflicts[meeting]), meeting)
                         for meeting in meetings_and_conflicts])
        for conflicting_meeting in meetings_and_conflicts[meeting]:
            meetings_and_conflicts[conflicting_meeting].remove(meeting)
        del meetings_and_conflicts[meeting]
        count += 1
    return count


intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]
assert eraseOverlapIntervals(intervals) == 1

intervals = [[1, 2], [1, 2], [1, 2]]
assert eraseOverlapIntervals(intervals) == 2

intervals = [[1, 2], [2, 3]]
assert eraseOverlapIntervals(intervals) == 0

# intervals = [[0, 2], [1, 3], [1, 3], [2, 4], [3, 5], [3, 5], [4, 6]]
# assert eraseOverlapIntervals(intervals) == 4
