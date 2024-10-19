package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/product-of-array-except-self/description/

Given an integer array nums, return an array answer such that answer[i] is equal to the product of
all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Constraints:

2 <= nums.length <= 10^5
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

-2, 3, 0, 4, 9
0 , 0, -216, 0, 0

left to right -> -2, -6, 0, 0, 0
right to left -> 0, 0, 0, 36, 9

-2, 2, 0, 2, 0, 2, 2
0 , 0, 0, 0, 0, 0, 0

pad arrays with ones

left to right -> -2, 4, 0, 0, 0, 0, 0
right to left -> 0, 0, 0, 0, 0, 4, 2
*/

public class ProductOfArrayExceptSelfV1 {
  public static int[] productExceptSelf(int[] nums) {
    int[] leftToRight = new int[nums.length + 1]; // 1-index
    int[] rightToLeft = new int[nums.length + 1]; // 0-index
    leftToRight[0] = 1;
    rightToLeft[nums.length] = 1;
    for (int i = 0; i < nums.length; i++) {
      leftToRight[i + 1] = leftToRight[i] * nums[i];
      rightToLeft[nums.length - 1 - i] = rightToLeft[nums.length - i] * nums[nums.length - i - 1];
    }

    int[] productExceptSelf = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      productExceptSelf[i] = leftToRight[i] * rightToLeft[i + 1];
    }
    return productExceptSelf;
  }

  public static void main(String[] args) {
    assert Arrays.equals(productExceptSelf(new int[]{}), new int[]{});
    assert Arrays.equals(productExceptSelf(new int[]{1, 2}), new int[]{2, 1});
    assert Arrays.equals(productExceptSelf(new int[]{1, 0}), new int[]{0, 1});
    assert Arrays.equals(productExceptSelf(new int[]{2, 0, 2}), new int[]{0, 4, 0});
  }
}
