package myjava;

import java.util.*;

/*-
Given list of weights, list of values of n items, maximum weight capacity of backpack, maximize the value of
items in a backpack (return list of indexes as well)

Example 1:
value = [60, 100 ,120], weight = [10, 20, 30], capacity = 50 -> max_value: 220, items: [1,2]
value = [10, 20, 30], weight = [1, 1, 1], capacity = 2 -> max_value: 50, items: [1,2]

---

value = [3, 5, 6], weight = [1, 2, 3], capacity = 5 -> max_value = 11, items: [1,2]

capacity/value_index
    0   1   2 
0  []  []   [] 
1  [1] [1] [1 ]
2  [1] [2] [2 ]
3  [1] [8] [8 ]
4  [1] [8] [9 ]
5  [1] [8] [11]

dp[capacity - weight[item]][index-1]
dp[capacity][index-1]

*/

public class Knapsack {
  private static record Solution(int maxValue, List<Integer> indices) {
  }

  private static Solution knapsack(int[] values, int[] weights, int capacity) {
    Solution[][] dp = new Solution[capacity + 1][values.length + 1];
    for (int i = 0; i < values.length; i++) {
      dp[0][i] = new Solution(0, List.of());
    }

    for (int currCapacity = 1; currCapacity <= capacity; currCapacity++) {
      for (int i = 0; i < values.length; i++) {
        if (weights[i] <= currCapacity) {
          if (i == 0) {
            dp[currCapacity][i] = new Solution(values[i], List.of(i));
          } else if (
            values[i] + dp[currCapacity - weights[i]][i - 1].maxValue() > dp[currCapacity][i - 1].maxValue()
          ) {
            List<Integer> indices = new ArrayList<>(dp[currCapacity - weights[i]][i - 1].indices());
            indices.add(i);
            dp[currCapacity][i] = new Solution(
              values[i] + dp[currCapacity - weights[i]][i - 1].maxValue(), indices
            );
          } else {
            dp[currCapacity][i] = dp[currCapacity][i - 1];
          }
        } else {
          dp[currCapacity][i] = i == 0 ? new Solution(0, List.of()) : dp[currCapacity][i - 1];
        }
      }
    }

    return dp[capacity][values.length - 1];
  }

  public static void main(String[] args) {
    Solution solution;

    solution = knapsack(new int[]{3, 5, 6}, new int[]{1, 2, 3}, 5);
    assert solution.maxValue() == 11;
    assert solution.indices().equals(List.of(1, 2));
  }
}
