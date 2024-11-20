import java.util.*;
import java.util.stream.*;

public class DataStructures {
  public static void main(String[] args) {
    arrayList();
    System.out.println();
    stack();
    System.out.println();
    map();
    System.out.println();
    set();
    System.out.println();
    queue();
    System.out.println();
    deque();
    System.out.println();
    minHeap();
    System.out.println();
    maxHeap();
    System.out.println();
  }

  private static void arrayList() {
    System.out.println("--- arrayList start");
    List.of(1, 2, 3).forEach(System.out::println);
    // To remember, List.equals() works by comparing the elements and not the lists'
    // references.
    System.out.println("--- finish");
  }

  private static void stack() {
    System.out.println("--- stack start");
    // ArrayDeque could be used instead of LinkedList if you want to pre allocate
    // space for the stack
    final Deque<Integer> stack = new LinkedList<>();
    final var numElems = 3;

    for (int i = 1; i <= numElems; i++)
      stack.push(i);

    System.out.println(stack);

    for (int i = 1; i <= numElems; i++)
      System.out.printf("Removing %s %s\n", stack.pop(), stack);

    System.out.println("--- finish");
  }

  // private static void stack() {
  // System.out.println("--- stack start");
  // final Stack<Integer> stack = new Stack<>();
  // final var numElems = 3;

  // for (int i = 1; i <= numElems; i++)
  // stack.push(i);

  // System.out.println(stack);

  // for (int i = 1; i <= numElems; i++)
  // System.out.printf("Removing %s %s\n", stack.pop(), stack);

  // System.out.println("--- finish");
  // }

  private static void map() {
    System.out.println("--- map start");

    final Map<Integer, Integer> map1 = Map.of(1, 2, 2, 4, 3, 6);
    System.out.printf("Map.of constants -> %s\n", map1);

    final Map<Integer, Integer> map2 = IntStream.range(1, 5 + 1)
      .boxed()
      .collect(Collectors.toMap(k -> k, k -> k * 2));
    System.out.printf("IntStream.range & Collectors.toMap k * 2 -> %s\n", map2);

    final Map<Integer, List<Integer>> map2_2 = IntStream.of(1, 1, 2, 3, 3, 3)
      .boxed()
      .collect(
        Collectors.toMap(
          k -> k,
          k -> List.of(k * 2),
          (val1, val2) -> Stream.concat(val1.stream(), val2.stream()).toList()
        )
      );
    System.out.printf("IntStream.of(1, 1, 2, 3, 3, 3) & Collectors.toMap k * 2 -> %s\n", map2_2);

    final Map<Integer, List<Integer>> map3 = IntStream.range(1, 5 + 1)
      .boxed()
      .collect(Collectors.groupingBy(k -> k % 2));
    System.out.printf("IntStream.range & Collectors.groupingBy %% 2 -> %s\n", map3);

    final Map<Integer, Long> map4 = IntStream.range(1, 5 + 1)
      .boxed()
      .collect(Collectors.groupingBy(k -> k % 2, Collectors.counting()));
    System.out.printf("IntStream.range & Collectors.groupingBy %% 2 and counting -> %s\n", map4);

    System.out.println("--- finish");
  }

  private static void set() {
    System.out.println("--- set start");

    final Set<Integer> set1 = Set.of(1, 2, 3, 4, 5);
    System.out.printf("Set.of constants -> %s\n", set1);

    final Set<Integer> set2 = new HashSet<>(Set.of(1, 2, 3, 4, 5));
    set2.removeAll(Set.of(2, 3, 4));
    System.out.printf("set.removeAll(Set.of(2, 3, 4)) -> %s\n", set2);

    final Set<Integer> set3 = new HashSet<>(Set.of(2, 3, 4));
    set3.retainAll(Set.of());
    System.out.printf("Intersection of Set.of(2, 3, 4) and Set.of() -> %s\n", set3);

    final Set<Integer> set4 = new HashSet<>(Set.of(2, 4, 6));
    set4.retainAll(Set.of(3, 6, 9));
    System.out.printf("Intersection of Set.of(2, 4, 6) and Set.of(3, 6, 9) -> %s\n", set4);

    System.out.println("--- finish");
  }

  private static void queue() {
    System.out.println("--- queue start");
    final Queue<Integer> queue = new LinkedList<>();
    final var numElems = 3;

    for (int i = 1; i <= numElems; i++)
      queue.offer(i);

    System.out.println(queue);

    for (int i = 1; i <= numElems; i++)
      System.out.printf("Removing %s %s\n", queue.poll(), queue);

    System.out.println("--- finish");
  }

  private static void deque() {
    System.out.println("--- deque start");
    final Deque<Integer> deque = new LinkedList<>();
    final var numElems = 3;

    for (int i = 1; i <= numElems; i++)
      deque.offer(i);

    System.out.println(deque);

    for (int i = 1; i <= numElems; i++) {
      if (i % 2 == 0) {
        System.out.printf("Removing first %s %s\n", deque.pollFirst(), deque);
      } else {
        System.out.printf("Removing last %s %s\n", deque.pollLast(), deque);
      }
    }

    System.out.println("--- finish");
  }

  private static void minHeap() {
    System.out.println("--- minHeap start");
    final Queue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
    final var numElems = 3;

    for (int i = numElems; i >= 1; i--)
      minHeap.offer(i);

    System.out.println(minHeap);

    for (int i = 1; i <= numElems; i++)
      System.out.printf("Removing %s %s\n", minHeap.poll(), minHeap);

    System.out.println("--- finish");
  }

  private static void maxHeap() {
    System.out.println("--- maxHeap start");
    final Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    final var numElems = 3;

    for (int i = 1; i <= numElems; i++)
      maxHeap.offer(i);

    System.out.println(maxHeap);

    for (int i = 1; i <= numElems; i++)
      System.out.printf("Removing %s %s\n", maxHeap.poll(), maxHeap);

    System.out.println("--- finish");
  }
}
