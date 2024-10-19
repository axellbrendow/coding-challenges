package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/task-scheduler/

0 1 3 4 6 7
A B A B A B

0 1 4 7 10 3
A B B B B  A

1 0 3 6 9 4
A B B B B A

n = 2
A B A A A A B B C D E F G H J W W W W I U H H H H H H H H H H H H H
{A: 5, B: 3, C: 1, D: 1, E: 1, F: 1, G: 1, H: 14, J: 1, W: 4, I: 1, U: 1}

n = 2
cycle = 1
                          *
A B A A A A B B C D E F G H J W W W W I U H H H H H H H H H H H H H
{A: 5, B: 3, C: 1, D: 1, E: 1, F: 1, G: 1, J: 1, W: 4, I: 1, U: 1} // H: 13 from priority queue and add back in cycle 1 + 3

before processing and element from the frequency heap, add to the frequency heap elements from the timestamp heap if possible

n = 2
cycle = 2
                          *
*
A B A A A A B B C D E F G H J W W W W I U H H H H H H H H H H H H H
{B: 3, C: 1, D: 1, E: 1, F: 1, G: 1, J: 1, W: 4, I: 1, U: 1} // A: 5 from priority queue and add back in cycle 2 + 3
*/

public class TaskSchedulerV1 {
  public static int leastInterval(char[] tasks, int n) {
    Map<Character, Long> frequencyMap = IntStream.range(0, tasks.length)
      .mapToObj(i -> tasks[i])
      .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

    Queue<Character> frequencyHeap = new PriorityQueue<>( // max-heap
      (task1, task2) -> { return (int)(frequencyMap.get(task2) - frequencyMap.get(task1)); }
    );
    frequencyHeap.addAll(frequencyMap.keySet());

    Map<Character, Integer> cooldownMap = new HashMap<>();

    Queue<Character> cooldownHeap = new PriorityQueue<>( // min-heap
      (task1, task2) -> { return cooldownMap.get(task1) - cooldownMap.get(task2); }
    );

    int cycle = 0;
    while (!frequencyMap.isEmpty()) {
      cycle++;
      if (frequencyHeap.isEmpty()) cycle = cooldownMap.get(cooldownHeap.peek());
      while (!cooldownHeap.isEmpty() && cooldownMap.get(cooldownHeap.peek()) <= cycle) {
        final var cooldownTask = cooldownHeap.poll();
        cooldownMap.remove(cooldownTask); // O(num of tasks)
        frequencyHeap.offer(cooldownTask); // O(num of tasks * log(count of unique tasks))
      }
      if (!frequencyHeap.isEmpty()) {
        final var task = frequencyHeap.poll(); // O(num of tasks * log(count of unique tasks))
        if (frequencyMap.get(task) == 1) frequencyMap.remove(task);
        else {
          frequencyMap.put(task, frequencyMap.get(task) - 1L);
          cooldownMap.put(task, cycle + n + 1);
          cooldownHeap.offer(task); // O(num of tasks * log(count of unique tasks))
        }
      }
      if (frequencyHeap.isEmpty() && cooldownHeap.isEmpty()) return cycle;
    }
    return cycle;
  }

  public static void main(String[] args) {
    assert leastInterval(new char[]{'A','A','A','B','B','B'}, 2) == 8;
    assert leastInterval(new char[]{'A','C','A','B','D','B'}, 1) == 6;
    assert leastInterval(new char[]{'A','A','A','B','B','B'}, 3) == 10;
    assert leastInterval(new char[]{'A','B','B','B','A'}, 2) == 7;
    assert leastInterval(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 1) == 12;
  }
}
