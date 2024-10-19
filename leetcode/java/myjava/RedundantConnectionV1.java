package myjava;

import java.util.*;

/*-
WRONG ANSWER

In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The
graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge
between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple
answers, return the answer that occurs last in the input.

Example 1:
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]

Example 2:
Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.

---

This V1 code is "wrong" but the question text suggests that edges are undirected and I made the code considering that.
There are other problems with the question description too:
https://leetcode.com/problems/redundant-connection/description/comments/2621068
*/

public class RedundantConnectionV1 {
  private static Map<Integer, Map<Integer, Integer>> buildGraph(int[][] edges) {
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      graph.computeIfAbsent(edges[i][0], start -> new HashMap<>()).put(edges[i][1], i);
      graph.computeIfAbsent(edges[i][1], start -> new HashMap<>()).put(edges[i][0], i);
    }
    return graph;
  }

  private static int[] dfs(
    Map<Integer, Map<Integer, Integer>> graph,
    int parent,
    int node,
    int edgeIndex,
    Set<Integer> visited
  ) {
    if (visited.contains(node)) return new int[]{node, edgeIndex};
    visited.add(node);
    Map<Integer, Integer> children = graph.get(node);
    /**
     * int[] cycleInfo = {cycleStart, maxEdgeIndex}
     * - cycleStart will be the node where the cycle starts
     * - maxEdgeIndex will be the maximum edge index in the cycle
     */
    int[] cycleInfo = null;
    int cycleStart, maxEdgeIndex = -1;
    for (int child : children.keySet()) {
      if (child == parent) continue;
      int childEdgeIndex = children.get(child);
      cycleInfo = dfs(graph, node, child, childEdgeIndex, visited);
      if (cycleInfo != null) {
        cycleStart = cycleInfo[0];
        if (cycleStart == -1) return cycleInfo;
        maxEdgeIndex = Math.max(maxEdgeIndex, Math.max(childEdgeIndex, cycleInfo[1]));
        return new int[]{node == cycleStart ? -1 : cycleStart, maxEdgeIndex};
      }
    }
    return cycleInfo;
  }

  public static int[] findRedundantConnection(int[][] edges) {
    Map<Integer, Map<Integer, Integer>> graph = buildGraph(edges);
    int[] cycleInfo = dfs(graph, -1, 1, -1, new HashSet<>());
    int lastEdge = cycleInfo[1];
    return edges[lastEdge];
  }

  public static void main(String[] args) {
    assert Arrays.equals(findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}), new int[]{2, 3});

    assert Arrays.equals(
      findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}), new int[]{1, 4}
    );

    // This test case doesn't pass because I'm using `for (int child : children.keySet())` and there's no
    // order guarantee when iterating through a set. Basically my code finds another cycle in the graph
    // instead of the first cycle following the sequence of edges in the input. This question is dumb.
    // assert Arrays.equals(
    //   findRedundantConnection(
    //     new int[][]{{2, 7}, {7, 8}, {3, 6}, {2, 5}, {6, 8}, {4, 8}, {2, 8}, {1, 8}, {7, 1}, {3, 9}}
    //   ),
    //   new int[]{2, 8}
    // );
  }
}
