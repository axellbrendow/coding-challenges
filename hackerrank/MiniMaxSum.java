import java.util.*;
import java.util.stream.*;

/*-
https://www.hackerrank.com/challenges/mini-max-sum/problem

Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly
four of the five integers. Then print the respective minimum and maximum values as a single line of two
space-separated long integers.
*/

public class MiniMaxSum {
  public static List<Long> miniMaxSum(List<Integer> arr) {
    arr.sort(Comparator.naturalOrder());
    long sum = arr.stream().collect(Collectors.summingLong(val -> val));
    int last = arr.get(arr.size() - 1);
    int first = arr.get(0);
    return List.of((sum - last), (sum - first));
    // System.out.println((sum - last) + " " + (sum - first));
  }

  public static void main(String[] args) {
    assert miniMaxSum(new ArrayList<>(List.of(1, 3, 5, 7, 9))).equals(List.of(16L, 24L));

    assert miniMaxSum(
      new ArrayList<>(
        List.of(Integer.MAX_VALUE, 1, 1, 1, Integer.MAX_VALUE)
      )
    ).equals(List.of(Integer.MAX_VALUE + 3L, Integer.MAX_VALUE * 2L + 2L));
  }
}
