package myjava;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/*-
https://leetcode.com/problems/maximum-product-subarray/description/

Given an integer array nums, find a subarray that has the largest product, and return the product.

1 1 1 2 2 2 3 3 3
output: []

1 1 1 1 2 2 3 3 3
output: [1]

1 1 1 1 2 3 3 3 3
3 3 3 3 2 1 1 1 1
3 2 3 3 1 1 1 3 1
output: [1, 3]

3 3 2 3 5 1 1 1 3 1
2 5 3 3 3 1 1 1 3 1
output: [1, 3]

The test cases are generated so that the answer will fit in a 32-bit integer.

Constraints:

1 <= nums.length <= 2 * 10^4
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

arr[] = {6, -3, -10, 0, 2}

arr[] = {-1, -3, -10, 0, 60}

arr[] = {-1, 3, 0, 2, -1}
arr[] = {1, -3, -2, -2, 1}
I need to consider an even number of negative numbers in the left-right direction and an even number of
negative numbers in the right-left direction

arr[] = {-10}

arr[] = {-1, 2, -2, 0, 2, 2, -1, -1, -2, 1, 2, 64}
prefix[]{-1, -2, 4, 0, 2, 4, -4, 4, -8, -8, -16, -1024}
*/

public class MaximumProductSubarray {
  public static int maxProduct(int[] nums) {
    int maxProduct = nums[0];
    int leftToRight = 1, rightToLeft = 1;
    for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
      leftToRight *= nums[i];
      maxProduct = Math.max(maxProduct, leftToRight);
      if (leftToRight == 0) leftToRight = 1;

      rightToLeft *= nums[j];
      maxProduct = Math.max(maxProduct, rightToLeft);
      if (rightToLeft == 0) rightToLeft = 1;
    }
    return maxProduct;
  }

  public static void main(String[] args) {
    assert maxProduct(new int[]{-1, 2, -2, 0, 2, 2, -1, -1, 1, 2, 1024}) == 8192;
  }
}
