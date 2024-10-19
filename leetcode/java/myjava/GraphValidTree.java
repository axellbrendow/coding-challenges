package myjava;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

/*-
https://www.lintcode.com/problem/178/description

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.

Example 1:
Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
Output: true.

Example 2:
Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
Output: false.
*/

public class GraphValidTree {
  private static boolean dfs(
    Map<Integer, Set<Integer>> graph,
    boolean[] visited,
    Integer parent,
    Integer node
  ) {
    visited[node] = true;
    for (Integer child : graph.get(node)) {
      if (child == parent) continue;
      if (visited[child]) return false;
      if (dfs(graph, visited, node, child) == false) return false;
    }
    return true;
  }

  public static boolean validTree(int n, int[][] edges) {
    if (edges.length != n - 1) return false;
    Map<Integer, Set<Integer>> graph = IntStream.range(0, n).boxed().collect(
      toMap(i -> i, i -> new HashSet<>())
    );
    for (var edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    boolean[] visited = new boolean[n];
    return dfs(graph, visited, null, 0) &&
      IntStream.range(0, visited.length)
        .mapToObj(i -> visited[i])
        .allMatch(vis -> vis);
  }

  public static void main(String[] args) {
    assert validTree(
      5,
      new int[][]{
        new int[]{0, 1},
        new int[]{0, 2},
        new int[]{0, 3},
        new int[]{1, 4},
      }
    );

    assert !validTree(
      5,
      new int[][]{
        new int[]{0, 1},
        new int[]{1, 2},
        new int[]{2, 3},
        new int[]{1, 3},
        new int[]{1, 4},
      }
    );
  }
}
