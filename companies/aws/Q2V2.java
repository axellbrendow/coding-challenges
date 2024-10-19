package aws;

import java.util.*;

/*-
Similar problem on leetcode https://leetcode.com/problems/plates-between-candles/description/

https://github.com/StevenSYT/leetcode-summary/blob/main/amazon_oa.md#items-in-containers

Amazon would like to know how much inventory exists in their closed inventory compartments. Given a string s
consisting of items as "*" and closed compartments as an open and close "|", an array of starting indices
startIndices, and an array of ending indices endIndices, determine the number of items in closed compartments
within the substring between the two indices, inclusive.

An item is represented as an asterisk ('*' = ascii decimal 42) A compartment is represented as a pair of pipes
that may or may not have items between them ('|' = ascii decimal 124).

Example

s = '|**|*|*'
startIndices = [1, 1]
endIndices = [5, 6]
The string has a total of 2 closed compartments, one with 2 items and one with 1 item. For the first pair of
indices, (1, 5), the substring is '|**|*'. There are 2 items in a compartment.
For the second pair of indices, (1, 6), the substring is '|**|*|' and there are 2 + 1 = 3 items in compartments.
Both of the answers are returned in an array, [2, 3].

Constraints:

1 ≤ m, n ≤ 10^5
1 ≤ startIndices[i] ≤ endIndices[i] ≤ n
Each character of s is either '*' or '|'

at every position I just need to know where is the first candle going to the right and going to the left
                        0123456789 10 11 12 13 14 15 16
                        ***||**|*| |  *  *  *  *  |  *
firstCandleToTheRight = 3333477799 10 15 15 15 15 15 --
firstCandleToTheLeft =  ---3444779 10 10 10 10 10 15 15
*/

public class Q2V2 {
  private static int[] numberOfItems(String s, int[] startIndices, int[] endIndices) {
    int[] prefixSum = new int[s.length()];
    int[] firstPipeToTheLeft = new int[s.length()];
    int[] firstPipeToTheRight = new int[s.length()];

    prefixSum[0] = s.charAt(0) == '*' ? 1 : 0;
    firstPipeToTheLeft[0] = s.charAt(0) == '*' ? -1 : 0;
    firstPipeToTheRight[s.length() - 1] = s.charAt(s.length() - 1) == '*' ? -1 : s.length() - 1;

    for (int i = 1, j = s.length() - 2; j >= 0; i++, j--) {
      prefixSum[i] = prefixSum[i - 1] + (s.charAt(i) == '*' ? 1 : 0);
      firstPipeToTheLeft[i] = s.charAt(i) == '*' ? firstPipeToTheLeft[i - 1] : i;
      firstPipeToTheRight[j] = s.charAt(j) == '*' ? firstPipeToTheRight[j + 1] : j;
    }

    int[] output = new int[startIndices.length];
    for (int i = 0; i < startIndices.length; i++) {
      int leftPipe = firstPipeToTheRight[startIndices[i] - 1];
      int rightPipe = firstPipeToTheLeft[endIndices[i] - 1];
      output[i] = leftPipe != -1 && leftPipe < rightPipe
        ? prefixSum[rightPipe] - prefixSum[leftPipe]
        : 0;
    }
    return output;
  }

  public static void main(String[] args) {
    assert Arrays.equals(numberOfItems("|*", new int[]{1}, new int[]{2}), new int[]{0});
    assert Arrays.equals(numberOfItems("|**|*|*", new int[]{1, 1}, new int[]{5, 6}), new int[]{2, 3});
  }
}
