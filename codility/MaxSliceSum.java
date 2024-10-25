package codility;

/*-
https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_slice_sum/
*/

public class MaxSliceSum {
  public static int solution(int[] A) {
    int sum = 0, maxSum = Integer.MIN_VALUE;
    for (int val : A) {
      sum = Math.max(val, sum + val);
      maxSum = Math.max(maxSum, sum);
    }
    return maxSum;
  }

  public static void main(String[] args) {
    assert solution(new int[]{1}) == 1;
    assert solution(new int[]{-1}) == -1;
    assert solution(new int[]{1, -1}) == 1;
    assert solution(new int[]{-1, 1}) == 1;
    assert solution(new int[]{3, 1, 2}) == 6;
    assert solution(new int[]{3, -1, 2}) == 4;
    assert solution(new int[]{-3, -2, -1}) == -1;
    assert solution(new int[]{-6, 2, 2, 10}) == 14;
    assert solution(new int[]{2, 3, 5, 2, -6, -5, 6, 2, 5}) == 14;
    assert solution(new int[]{2, 3, 5, 2, -6, -5, 6, 0, 5}) == 12;
    assert solution(new int[]{2, 3, 5, 2, -6, -6, 6, 2, 5}) == 13;
  }
}
