package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/3sum/description/

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Input: nums = [-4,-1,-1,0,1,2]
                   i        j  target = 4
                      i     j  target = 4
                        i   j  target = 4
                          i j  target = 4
                            ij  target = 4
Input: nums = [-4,-1,-1,0,1,2]
                      i     j  target = 1 (solution found -1, -1, 2)
                        i j    target = 1 (solution found -1, 0, 1)
Input: nums = [-4,-1,-1,0,1,2]
                          i j  target = 0
                          ij   target = 0

Input: nums = [-4,-3,-1,0,1,5,7]
                   i          j  target = 4 (solution found -4, -3, 7)
                      i     j    target = 4 (solution found -4, -1, 5)
                      i     j    target = 4 (while i and j are pointing to duplicates, move them)
                        i j    target = 4
                          ij    target = 4
*/

public class ThreeSum {
  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i - 1] == nums[i]) continue;
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        if (nums[i] + nums[j] + nums[k] > 0) k--;
        else if (nums[i] + nums[j] + nums[k] < 0) j++;
        else {
          result.add(List.of(nums[i], nums[j], nums[k]));
          while (j + 1 < nums.length && nums[j] == nums[j + 1]) j++;
          j++;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    assert threeSum(new int[]{-1, 0, 1, 2, -1, -4}).equals(List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
    assert threeSum(new int[]{0, 1, 1}).equals(List.of());
    assert threeSum(new int[]{0, 0, 0}).equals(List.of(List.of(0, 0, 0)));
  }
}
