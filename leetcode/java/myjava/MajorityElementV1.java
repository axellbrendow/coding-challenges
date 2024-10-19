package myjava;

import java.util.*;

/**-
https://leetcode.com/problems/majority-element/description/

Majority Element

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority
element always exists in the array.

*1 1 1 2 2 1
output: 1
{ 1: 1 }

1 *1 1 2 2 1
output: 1
{ 1: 2 }

1 1 *1 2 2 1
output: 1
{ 1: 3 }

1 1 1 *2 2 1
output: 1
{ 1: 3, 2: 1 }

1 1 1 2 *2 1
output: 1
{ 1: 3, 2: 2 }

1 1 1 2 2 *1
output: 1
{ 1: 4, 2: 2 }

time complexity: O(n) where n is the length of the array
space complexity: O(n)
*/

public class MajorityElementV1 {
  public static int majorityElement(int[] nums) {
    Map<Integer, Integer> frequency = new HashMap<>();
    for (int num : nums) {
      int newFrequency = frequency.getOrDefault(num, 0) + 1;
      if (newFrequency > nums.length / 2) return num;
      frequency.put(num, newFrequency);
    }
    throw new IllegalStateException();
  }

  public static void main(String[] args) {
    assert majorityElement(new int[]{2}) == 2;
    assert majorityElement(new int[]{1, 2, 2}) == 2;
    assert majorityElement(new int[]{1, 1, 1, 2, 2, 1}) == 1;
  }
}
