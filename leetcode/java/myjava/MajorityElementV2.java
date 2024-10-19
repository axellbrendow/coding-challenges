package myjava;

import java.util.*;

/**-
https://leetcode.com/problems/majority-element/description/

Majority Element

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority
element always exists in the array.

1 1 1 2 2 1
1 *1 1 1 2 2 (sort)
output: 1

time complexity: O(nlogn) where n is the length of the array
space complexity: O(n)
*/

public class MajorityElementV2 {
  public static int majorityElement(int[] nums) {
    if (nums.length == 1) return nums[0];
    Arrays.sort(nums); // this call uses O(n) space in Java
    int currWindowLength = 1;
    for (int i = 1; i < nums.length; i++) {
      currWindowLength = nums[i] == nums[i - 1] ? currWindowLength + 1 : 1;
      if (currWindowLength > nums.length / 2) return nums[i];
    }
    throw new IllegalStateException();
  }

  public static void main(String[] args) {
    assert majorityElement(new int[]{2}) == 2;
    assert majorityElement(new int[]{1, 2, 2}) == 2;
    assert majorityElement(new int[]{1, 1, 1, 2, 2, 1}) == 1;
  }
}
