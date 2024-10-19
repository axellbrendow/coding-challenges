package myjava;

public class MaximumProductSubarrayV3 {
  public static int maxProduct(int[] nums) {
    long maxProduct = nums[0], minProduct = nums[0], result = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == 0) {
        result = Math.max(result, 0);
        maxProduct = minProduct = 1;
      } else {
        long maxProductTmp = maxProduct;
        maxProduct = Math.max(nums[i], Math.max(maxProduct * nums[i], minProduct * nums[i]));
        minProduct = Math.min(nums[i], Math.min(maxProductTmp * nums[i], minProduct * nums[i]));
        result = Math.max(result, maxProduct);
      }
    }
    return (int) result;
  }

  public static void main(String[] args) {
    assert maxProduct(new int[]{-1}) == -1;
    assert maxProduct(new int[]{-2, 0, -1}) == 0;
    assert maxProduct(new int[]{2, 3, -2, 4}) == 6;
    assert maxProduct(new int[]{-1, 2, -2, 0, 2, 2, -1, -1, 1, 2, 1024}) == 8192;
  }
}
