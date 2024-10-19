package myjava;

import java.util.*;

/*-
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

DFS in this question works only if all the edges are kept with the same order they came from the input
*/

public class RedundantConnectionV2 {
  private static record Edge(int endNode, int edgeIndex) {}

  private static List<List<Edge>> buildGraph(int[][] edges) {
    List<List<Edge>> graph = new ArrayList<>(edges.length + 1);
    for (int i = 0; i < edges.length + 1; i++) graph.add(new ArrayList<>());

    for (int i = 0; i < edges.length; i++) {
      graph.get(edges[i][0]).add(new Edge(edges[i][1], i));
      graph.get(edges[i][1]).add(new Edge(edges[i][0], i));
    }
    return graph;
  }

  private static int[] dfs(
    List<List<Edge>> graph,
    boolean[] visited,
    int parent,
    int node,
    int edgeIndex
  ) {
    if (visited[node]) return new int[]{node, edgeIndex};
    visited[node] = true;
    /**
     * int[] cycleInfo = {cycleStart, maxEdgeIndex}
     * - cycleStart will be the node where the cycle starts
     * - maxEdgeIndex will be the maximum edge index in the cycle
     */
    int[] cycleInfo = null;
    int cycleStart, maxEdgeIndex = -1;
    for (Edge edge : graph.get(node)) {
      if (edge.endNode() == parent) continue;
      cycleInfo = dfs(graph, visited, node, edge.endNode(), edge.edgeIndex());
      if (cycleInfo != null) {
        cycleStart = cycleInfo[0];
        if (cycleStart == -1) return cycleInfo;
        maxEdgeIndex = Math.max(maxEdgeIndex, Math.max(edge.edgeIndex(), cycleInfo[1]));
        return new int[]{node == cycleStart ? -1 : cycleStart, maxEdgeIndex};
      }
    }
    return cycleInfo;
  }

  public static int[] findRedundantConnection(int[][] edges) {
    List<List<Edge>> graph = buildGraph(edges);
    int[] cycleInfo = dfs(graph, new boolean[graph.size()], -1, 1, -1);
    int lastEdge = cycleInfo[1];
    return edges[lastEdge];
  }

  public static void main(String[] args) {
    assert Arrays.equals(findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}), new int[]{2, 3});

    assert Arrays.equals(
      findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}), new int[]{1, 4}
    );

    assert Arrays.equals(
      findRedundantConnection(
        new int[][]{{2, 7}, {7, 8}, {3, 6}, {2, 5}, {6, 8}, {4, 8}, {2, 8}, {1, 8}, {7, 1}, {3, 9}}
      ),
      new int[]{2, 8}
    );
  }
}
