package myjava;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*-
https://leetcode.com/problems/k-closest-points-to-origin/description/

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

Example 1:
Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 

Constraints:

1 <= k <= points.length <= 10^4
-10^4 <= xi, yi <= 10^4

---

Quickselect can be used too
*/

public class KClosestPointsToOrigin {
  public static int[][] kClosest(int[][] points, int k) {
    int[][] closestPoints = new int[k][];
    Queue<int[]> heap = new PriorityQueue<>(
      (point1, point2) -> {
        int dist1 = point1[0] * point1[0] + point1[1] * point1[1]; // x² + y²
        int dist2 = point2[0] * point2[0] + point2[1] * point2[1]; // x² + y²
        return dist2 - dist1;
      }
    );
    for (int[] point : points) {
      heap.offer(point);
      if (heap.size() > k) heap.poll();
    }
    for (int i = 0; i < k; i++) closestPoints[i] = heap.poll();
    return closestPoints;
  }

  public static void main(String[] args) {
    assert Arrays.deepEquals(
      kClosest(new int[][]{{1,3}, {-2,2}}, 1),
      new int[][]{{-2,2}}
    );

    assert Arrays.deepEquals(
      kClosest(new int[][]{{3,3}, {5,-1}, {-2,4}}, 2),
      new int[][]{{-2,4}, {3,3}}
    );
  }
}
