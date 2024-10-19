package myjava;

public class KnapsackProblem01 {
  private static int knapsack01(int[] itemValues, int[] itemWeights, int knapsackCapacity) {
    int[][] maxValue = new int[itemValues.length + 1][knapsackCapacity + 1];

    for (int nFirstItems = 1; nFirstItems <= itemValues.length; nFirstItems++) {
      for (int currentCapacity = 1; currentCapacity <= knapsackCapacity; currentCapacity++) {
        final var currentItemWeight = itemWeights[nFirstItems - 1];
        maxValue[nFirstItems][currentCapacity] = currentItemWeight <= currentCapacity
          ? Math.max(
            maxValue[nFirstItems - 1][currentCapacity],
            itemValues[nFirstItems - 1] + maxValue[nFirstItems - 1][currentCapacity - currentItemWeight]
          )
          : maxValue[nFirstItems - 1][currentCapacity];
      }
    }

    return maxValue[itemValues.length][knapsackCapacity];
  }

  public static void main(String[] args) {
    assert knapsack01(new int[]{4, 2, 10, 1, 2}, new int[]{12, 1, 4, 1, 2}, 15) == 15;
  }
}
