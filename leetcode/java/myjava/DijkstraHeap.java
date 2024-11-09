package myjava;

import java.util.*;
import java.util.stream.*;

public class DijkstraHeap {
  private static record VertexAndDistanceFromSource(char vertex, int distance) {
  }

  public static void dijkstra(Map<Character, Map<Character, Integer>> graph, char sourceVertex) {
    Set<Character> visited = new HashSet<>();
    Map<Character, Character> parent = graph.keySet().stream().collect(
      Collectors.toMap(vertex -> vertex, vertex -> vertex)
    ); // O(V)

    Map<Character, Integer> distance = graph.keySet().stream().collect(
      Collectors.toMap(vertex -> vertex, vertex -> Integer.MAX_VALUE)
    ); // O(V)
    distance.put(sourceVertex, 0);

    Queue<VertexAndDistanceFromSource> heap = new PriorityQueue<>(
      (dist1, dist2) -> dist1.distance() - dist2.distance()
    ); // O(V)
    heap.offer(new VertexAndDistanceFromSource(sourceVertex, 0));

    while (!heap.isEmpty()) { // At most E iterations
      final var closestVertex = heap.poll(); // O(logE). O(ElogE) considering all iterations
      if (visited.contains(closestVertex.vertex())) continue;
      // all the code below runs a single time for each node in the graph, so, O(V) in total
      // all the other E - V iterations will be skipped by the if above
      visited.add(closestVertex.vertex());
      // O(V - 1) when each vertex has V - 1 edges. O(V²) or O(E) considering all iterations as E ≈ V²
      graph.get(closestVertex.vertex()).forEach((childVertex, edgeWeight) -> {
        if (visited.contains(childVertex)) return;
        if (closestVertex.distance() + edgeWeight < distance.get(childVertex)) {
          parent.put(childVertex, closestVertex.vertex());
          distance.put(childVertex, closestVertex.distance() + edgeWeight);
          // O(logE). O(V²logE) considering all iterations which is the same as O(ElogE) as E ≈ V²
          heap.offer(
            new VertexAndDistanceFromSource(childVertex, distance.get(closestVertex.vertex()) + edgeWeight)
          );
        }
      });
    }

    // Time complexity:
    // = V * V * logE
    // = V² * logE
    // = E * logE (number of edges is near V² in the worst case)
    // = E * logV²
    // = E * 2logV (log property: log(a^m) = mlog(a))
    // = ElogV

    System.out.println("node  : " + graph.keySet().stream().sorted().toList().toString());
    System.out.println(
      "parent: " + parent.keySet().stream().sorted().map(node -> parent.get(node)).toList().toString()
    );
    System.out.println("dist  : " + distance.values().toString());
    /*-
    node  : [A, B, C, D, E, F, G]
    parent: [A, A, B, A, D, E, G]
    dist  : [0, 2, 3, 3, 5, 6, 2147483647]
    */
  }

  public static void main(String[] args) {
    Map<Character, Map<Character, Integer>> graph = Map.of(
      'A', Map.of('B', 2, 'D', 3),
      'B', Map.of('C', 1, 'E', 4),
      'C', Map.of('F', 5),
      'D', Map.of('E', 2),
      'E', Map.of('F', 1),
      'F', Map.of(),
      'G', Map.of()
    );

    dijkstra(graph, 'A');
  }
}
