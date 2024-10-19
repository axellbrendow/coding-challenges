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

public class CourseScheduleII {
  private static boolean dfs(
    Map<Integer, Set<Integer>> graph,
    boolean[] globalVisited,
    List<Integer> topoSort,
    boolean[] visited,
    Integer node
  ) {
    if (globalVisited[node]) return true;
    if (visited[node]) return false;
    visited[node] = true;
    for (Integer child : graph.get(node)) {
      if (dfs(graph, globalVisited, topoSort, visited, child) == false) return false;
    }
    globalVisited[node] = true;
    topoSort.add(node);
    return true;
  }

  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (var prerequisite : prerequisites) {
      graph.computeIfAbsent(prerequisite[0], dependent -> new HashSet<>()).add(prerequisite[1]);
    }
    for (int i = 0; i < numCourses; i++) graph.putIfAbsent(i, new HashSet<>());

    List<Integer> topoSort = new ArrayList<>();
    boolean[] globalVisited = new boolean[graph.size()];
    for (Integer node : graph.keySet()) {
      if (dfs(graph, globalVisited, topoSort, new boolean[graph.size()], node) == false)
        return new int[]{};
    }
    return topoSort.stream().mapToInt(val -> val).toArray();
  }

  public static void main(String[] args) {
    assert Arrays.equals(findOrder(2, new int[][]{{1, 0}}), new int[]{0, 1});
    assert Arrays.equals(findOrder(2, new int[][]{{1, 0}, {0, 1}}), new int[]{});
    assert Arrays.equals(findOrder(1, new int[][]{}), new int[]{0});
  }
}
