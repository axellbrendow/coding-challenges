package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/course-schedule-iii/description/

There are n different online courses numbered from 1 to n. You are given an array courses where
courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for
durationi days and must be finished before or on lastDayi.

You will start on the 1st day and you cannot take two or more courses simultaneously.

Return the maximum number of courses that you can take.

Example 1:
Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
Output: 3
Explanation: 
There are totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take
the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take
the next course on the 1101st day. 
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.

Example 2:
Input: courses = [[1,2]]
Output: 1

Example 3:
Input: courses = [[3,2],[4,3]]
Output: 0

Constraints:

1 <= courses.length <= 10^4
1 <= durationi, lastDayi <= 10^4

---

courses = [[2,3],[3,5],[3,6],[10,15]]

--------------------------------------
                    ----------
        ----------
------
01  02  03  04  05  06  07  08  09  10  11  12  13  14  15
*/

public class CourseScheduleIII {
  public static int scheduleCourse(int[][] courses) {
    Arrays.sort(courses, (course1, course2) -> course1[1] - course2[1]);
    Queue<Integer> heap = new PriorityQueue<>((duration1, duration2) -> duration2 - duration1);
    int elapsedTime = 0;
    for (int[] course : courses) {
      if (course[0] + elapsedTime <= course[1]) {
        heap.offer(course[0]);
        elapsedTime += course[0];
      } else if (!heap.isEmpty() && course[0] < heap.peek()) {
        elapsedTime -= heap.poll();
        heap.offer(course[0]);
        elapsedTime += course[0];
      }
    }
    return heap.size();
  }

  public static void main(String[] args) {
    assert scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}) == 3;
  }
}
