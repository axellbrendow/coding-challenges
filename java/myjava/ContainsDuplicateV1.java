package myjava;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/*-
https://leetcode.com/problems/contains-duplicate/description/

Given an integer array nums, return true if any value appears at least twice in the array, and return false if
every element is distinct.

Example 1:
Input: nums = [1,2,3,1]
Output: true
Explanation:
The element 1 occurs at the indices 0 and 3.

Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation:
All elements are distinct.

Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true

Constraints:

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/

public class ContainsDuplicateV1 {
  public static boolean containsDuplicate(int[] nums) {
    Set<Integer> numsSet = new HashSet<>();
    for (int num : nums)
      if (numsSet.contains(num)) return true;
      else numsSet.add(num);
    return false;
  }

  public static void main(String[] args) {
    assert containsDuplicate(new int[]{1, 2, 3, 4}) == false;
    assert containsDuplicate(new int[]{1, 1, 1, 3}) == true;
    assert containsDuplicate(new int[]{1, 2, 3, 1}) == true;
  }
}
