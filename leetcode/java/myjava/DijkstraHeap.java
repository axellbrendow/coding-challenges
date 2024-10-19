package myjava;

import java.util.Arrays;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;

public class DijkstraHeap {
  public static void dijkstra(Map<Integer, List<Edge>> graph, int initialVertex) {
    // V -> num of vertices
    // E -> num of edges
    final var dist = new int[graph.size()]; // O(V)
    Arrays.fill(dist, Integer.MAX_VALUE); // O(V)
    dist[initialVertex] = 0;

    final var parent = new Integer[graph.size()]; // O(V)
    final var visited = new boolean[graph.size()]; // O(V)

    final Queue<NodeDist> heap = new PriorityQueue<>(
      (nodeDist1, nodeDist2) -> nodeDist1.dist() - nodeDist2.dist()
    );
    heap.offer(new NodeDist(initialVertex, 0)); // O(1)

    while (!heap.isEmpty()) { // O(1). O(E) considering all iterations
      final var nodeDist = heap.poll(); // O(logE). O(ElogE) considering all iterations
      if (visited[nodeDist.node()]) continue;
      // all the code below runs a single time for each node in the graph, so, O(V) in total
      // all the other E - V iterations will be skipped by the if above
      visited[nodeDist.node()] = true;

      // O(V - 1) when each vertex has V - 1 edges. O(V²) or O(E) considering all iterations as E ≈ V²
      for (final var edge : graph.get(nodeDist.node())) {
        if (visited[edge.endNode()]) continue;
        if (dist[nodeDist.node()] + edge.weight() < dist[edge.endNode()]) {
          dist[edge.endNode()] = dist[nodeDist.node()] + edge.weight();
          parent[edge.endNode()] = nodeDist.node();
        }
        // O(logE). O(V²logE) considering all iterations which is the same as O(ElogE) as E ≈ V²
        heap.offer(new NodeDist(edge.endNode(), dist[edge.endNode()]));
      }
    }

    // Time complexity:
    // = V * V * logE
    // = V² * logE
    // = E * logE (number of edges is near V² in the worst case)
    // = E * logV²
    // = E * 2logV (log property: log(a^m) = mlog(a))
    // = ElogV

    System.out.println("node  : " + Arrays.toString(new String[]{
      "  A ", "B", "C", "D", "E", "F"
    }));
    System.out.println(
      "parent: " + Arrays.toString(
        Arrays.stream(parent).map(DijkstraHeap::toChar).toArray(Character[]::new)
      )
    );
    System.out.println("dist  : " + Arrays.toString(dist));
    /*
    node  : [  A , B, C, D, E, F]
    parent: [null, A, B, A, D, E]
    dist  : [  0 , 2, 3, 3, 5, 6]
    */
  }

  private static Integer toIndex(Character node) {
    return node == null ? null : node - 'A';
  }

  private static Character toChar(Integer index) {
    return index == null ? null : (char) ('A' + index);
  }

  public static void main(String[] args) {
    final Map<Integer, List<Edge>> graph = Map.of(
      toIndex('A'), List.of(new Edge(toIndex('B'), 2), new Edge(toIndex('D'), 3)),
      toIndex('B'), List.of(new Edge(toIndex('C'), 1), new Edge(toIndex('E'), 4)),
      toIndex('C'), List.of(new Edge(toIndex('F'), 5)),
      toIndex('D'), List.of(new Edge(toIndex('E'), 2)),
      toIndex('E'), List.of(new Edge(toIndex('F'), 1)),
      toIndex('F'), List.of()
    );

    dijkstra(graph, toIndex('A'));
  }
}

record Edge(int endNode, int weight) {
}

/**
 * Stores a node and the distance between it and the initial vertex.
 */
record NodeDist(int node, int dist) {
}
