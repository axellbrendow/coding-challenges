package myjava;

/*-
https://leetcode.com/problems/kth-largest-element-in-an-array/description/

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
 

Constraints:

1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
*/

public class KthLargestElementInAnArray {
  public static void quickselect(int i, int j, int[] nums, int k) {
    int left = i, right = j, pivot = nums[i + (j - i) / 2];
    while (left <= right) {
      while (nums[left] < pivot) left++;
      while (nums[right] > pivot) right--;
      if (left <= right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
        left++;
        right--;
      }
    }
    if (i < right && i <= k && k <= right) quickselect(i, right, nums, k);
    else if (left < j && left <= k && k <= j) quickselect(left, j, nums, k);
  }

  public static int findKthLargest(int[] nums, int k) {
    quickselect(0, nums.length - 1, nums, nums.length - k);
    return nums[nums.length - k];
  }

  public static void main(String[] args) {
    assert findKthLargest(new int[]{3,2,1,5,6,4}, 2) == 5;
    assert findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4) == 4;
  }
}
