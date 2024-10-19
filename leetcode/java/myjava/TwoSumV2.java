package myjava;

import java.util.*;
import java.util.stream.*;

class TwoSumV2 {
  public static int[] twoSum(int[] nums, int target) {
    final var numToIndices = IntStream.range(0, nums.length).boxed()
      .collect(Collectors.groupingBy(index -> nums[index]));

    return Arrays.stream(nums)
      .filter(
        num -> num + num == target
          ? numToIndices.get(num).size() > 1
          : numToIndices.containsKey(target - num)
      )
      .mapToObj(
        num -> new int[]{
          numToIndices.get(num).get(0),
          numToIndices.get(target - num).get(num + num == target ? 1 : 0)
        }
      )
      .findFirst().get();
  }

  public static void main(String[] args) {
    assert Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1});
    assert Arrays.equals(twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2});
    assert Arrays.equals(twoSum(new int[]{3, 3}, 6), new int[]{0, 1});
  }
}
