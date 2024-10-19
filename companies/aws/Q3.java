package aws;

import java.util.*;

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

public class Q3 {
  private static int getMinScore(int[] watchHistory, int series1, int series2) {
    Map<Integer, Queue<Integer>> seriesMap = new HashMap<>();
    int i = 0, j = 0, minWatchScore = Integer.MAX_VALUE;
    while (j < watchHistory.length) {
      seriesMap.computeIfAbsent(watchHistory[j], serie -> new LinkedList<>()).offer(j);
      while (seriesMap.containsKey(series1) && seriesMap.containsKey(series2)) {
        minWatchScore = Math.min(minWatchScore, seriesMap.size());
        Queue<Integer> queue = seriesMap.get(watchHistory[i]);
        if (queue.size() == 1) seriesMap.remove(watchHistory[i]);
        else queue.poll();
        i++;
      }
      j++;
    }
    return minWatchScore != Integer.MAX_VALUE ? minWatchScore : 0;
  }

  public static void main(String[] args) {
    assert getMinScore(new int[]{1, 3, 2, 1, 4}, 4, 4) == 1;
    assert getMinScore(new int[]{1, 3, 2, 1, 4}, 1, 2) == 2;
    assert getMinScore(new int[]{1, 3, 1, 0, 2, 1, 4}, 1, 2) == 2;
    assert getMinScore(new int[]{1, 2, 2, 2, 3}, 1, 3) == 3;
  }
}
