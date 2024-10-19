package myjava;

import java.util.*;
import java.util.stream.*;

public class SubsetGenerator {
  public static void main(String[] args) {
    // *1 *2 *3 *NULL -> []
    // *1 *2 3 -> [], [3]
    // *1 2 3 -> [3], [3, 2], [], [2]
    // 1 2 3 -> [3], [3, 2], [], [2], [1, 3], [1, 3, 2], [1], [1, 2]
    // (2^n)/2 -> 2^(n-1)
    final var arr = new int[]{
      1, 2, 3
    };
    assert allSubsetsOf(arr, 0).equals(
      List.of(
        List.of(),
        List.of(3),
        List.of(2),
        List.of(3, 2),
        List.of(1),
        List.of(3, 1),
        List.of(2, 1),
        List.of(3, 2, 1)
      )
    );
  }

  private static List<List<Integer>> allSubsetsOf(int[] arr, int index) {
    if (index >= arr.length) return List.of(List.of());
    final List<List<Integer>> nextIndexSubsets = allSubsetsOf(arr, index + 1);
    final List<List<Integer>> allSubsets = new ArrayList<>();
    allSubsets.addAll(nextIndexSubsets); // O(2^n) = O(2^(n-1)) + O(2^(n-2)) + ... + O(2^1) + O(2^0)
    for (final List<Integer> nextIndexSubset : nextIndexSubsets) {
      final List<Integer> newSubset = new ArrayList<>();
      // O(2^n) --> 0, 1, 2 + 1, 4 + 1 + 1 + 2 --> 0, 1, 3, 7 --> 0, 1, 2^1 + 1, 2^2 + 3
      newSubset.addAll(nextIndexSubset);
      newSubset.add(arr[index]);
      allSubsets.add(newSubset);
    }
    return allSubsets;
  }
}
