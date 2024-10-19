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
*/

public class RedundantConnectionV3 {
  private static int find(int[] parent, int node) {
    while (node != parent[node]) node = parent[node] = parent[parent[node]];
    return node;
  }

  private static boolean unionWithCycle(int[] parent, int[] rank, int node1, int node2) {
    int parent1 = find(parent, node1);
    int parent2 = find(parent, node2);
    if (parent1 == parent2) return true;
    if (rank[parent1] >= rank[parent2]) {
      rank[parent1] += rank[parent2];
      parent[parent2] = parent1;
    } else {
      rank[parent2] += rank[parent1];
      parent[parent1] = parent2;
    }
    return false;
  }

  public static int[] findRedundantConnection(int[][] edges) {
    int[] parent = new int[edges.length + 1];
    for (int i = 1; i < parent.length; i++) parent[i] = i;

    int[] rank = new int[edges.length + 1];
    Arrays.fill(rank, 1);

    for (var edge : edges) {
      if (unionWithCycle(parent, rank, edge[0], edge[1])) return edge;
    }
    throw new IllegalStateException();
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
