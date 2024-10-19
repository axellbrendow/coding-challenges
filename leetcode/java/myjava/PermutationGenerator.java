package myjava;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class PermutationGenerator {
  private static void permutationsOf(
    List<Integer> list,
    List<Integer> currPermutation,
    List<List<Integer>> result,
    Set<Integer> used
  ) {
    if (list.size() == used.size()) {
      result.add(new ArrayList<>(currPermutation));
      return;
    }

    for (Integer integer : list) {
      if (!used.contains(integer)) {
        used.add(integer);
        currPermutation.add(integer);
        permutationsOf(list, currPermutation, result, used);
        currPermutation.remove(currPermutation.size() - 1);
        used.remove(integer);
      }
    }
  }

  private static List<List<Integer>> permutationsOf(List<Integer> list) {
    List<List<Integer>> result = new ArrayList<>();
    permutationsOf(list, new ArrayList<>(), result, new HashSet<>());
    return result;
  }

  public static void main(String[] args) {
    assert permutationsOf(List.of(1, 2, 3)).equals(
      List.of(
        List.of(1, 2, 3),
        List.of(1, 3, 2),
        List.of(2, 1, 3),
        List.of(2, 3, 1),
        List.of(3, 1, 2),
        List.of(3, 2, 1)
      )
    );
  }
}
