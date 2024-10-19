package myjava;

/*-
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

target = 8
arr = 2 3 4 5 8 11 18
      l            r  (the sum is 20 currently, move right pointer to reduce the sum)
      l         r  (the sum is 13 currently, move right pointer to reduce the sum)
      l       r  (the sum is 10 currently, move right pointer to reduce the sum)
      l     r  (the sum is 7 currently, stop right pointer)
        l   r  (the sum is 7 currently, move left pointer to increase the sum)
        l   r  (the sum is 8, solution found)
*/

import java.util.Arrays;

public class TwoSumSorted {
  public static int[] twoSum(int[] numbers, int target) {
    int left = 0, right = numbers.length - 1;
    while (true) {
      while (numbers[left] + numbers[right] > target) right--;
      while (numbers[left] + numbers[right] < target) left++;
      if (numbers[left] + numbers[right] == target) {
        return new int[]{1 + left, 1 + right}; // leetcode uses a 1-indexed array for this problem
      }
    }
  }

  public static void main(String[] args) {
    assert Arrays.equals(twoSum(new int[]{2, 3, 4, 5, 8, 11, 18}, /*target*/ 8), new int[]{2, 4});
  }
}
