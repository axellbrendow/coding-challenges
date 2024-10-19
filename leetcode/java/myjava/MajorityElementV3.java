package myjava;

import java.util.*;

/**-
https://leetcode.com/problems/majority-element/description/

Majority Element

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority
element always exists in the array.

1 1 1 2 2 1
1 1 1 1 2 2 (sort)
output: 1
The majority element will always be in the middle as it appears half+1 the times in the array

time complexity: O(nlogn) where n is the length of the array
space complexity: O(n)
*/

public class MajorityElementV3 {
  public static int majorityElement(int[] nums) {
    Arrays.sort(nums); // this call uses O(n) space in Java
    return nums[nums.length / 2];
  }

  public static void main(String[] args) {
    assert majorityElement(new int[]{2}) == 2;
    assert majorityElement(new int[]{1, 2, 2}) == 2;
    assert majorityElement(new int[]{1, 1, 1, 2, 2, 1}) == 1;
  }
}
