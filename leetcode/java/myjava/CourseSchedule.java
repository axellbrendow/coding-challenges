package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/course-schedule/description/

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an
array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want
to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
So it is impossible.

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
*/

public class CourseSchedule {
  private static boolean dfs(
    Map<Integer, Set<Integer>> graph,
    boolean[] globalVisited,
    boolean[] visited,
    Integer node
  ) {
    if (globalVisited[node]) return true;
    if (visited[node]) return false;
    visited[node] = true;
    for (Integer child : graph.get(node)) {
      if (dfs(graph, globalVisited, visited, child) == false) return false;
    }
    globalVisited[node] = true;
    return true;
  }

  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (var prerequisite : prerequisites) {
      graph.computeIfAbsent(prerequisite[0], dependent -> new HashSet<>()).add(prerequisite[1]);
    }
    for (int i = 0; i < numCourses; i++) graph.putIfAbsent(i, new HashSet<>());

    boolean[] globalVisited = new boolean[graph.size()];
    for (Integer node : graph.keySet()) {
      if (dfs(graph, globalVisited, new boolean[graph.size()], node) == false)
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    assert canFinish(2, new int[][]{{1, 0}}) == true;
    assert canFinish(2, new int[][]{{1, 0}, {0, 1}}) == false;
  }
}
