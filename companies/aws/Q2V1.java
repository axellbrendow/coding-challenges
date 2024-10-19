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

|**|*|*||||***** |  *  |  |  *  *  |  |  *
0122334444456789 9 10 10 10 11 12 12 12 13
*/

public class Q2V1 {
  private static int[] numberOfItems(String s, int[] startIndices, int[] endIndices) {
    int[] prefixSum = new int[s.length()];
    if (s.charAt(0) == '*') prefixSum[0] = 1;
    for (int i = 1; i < s.length(); i++)
      prefixSum[i] = s.charAt(i) == '*' ? prefixSum[i - 1] + 1 : prefixSum[i - 1];

    int[] output = new int[startIndices.length];
    for (int i = 0; i < startIndices.length; i++) {
      int leftPipe = s.indexOf('|', startIndices[i] - 1);
      if (leftPipe == -1) continue;
      int rightPipe = s.lastIndexOf('|', endIndices[i] - 1);
      output[i] = prefixSum[rightPipe] - prefixSum[leftPipe];
    }
    return output;
  }

  public static void main(String[] args) {
    assert Arrays.equals(numberOfItems("|*", new int[]{1}, new int[]{2}), new int[]{0});
    assert Arrays.equals(numberOfItems("|**|*|*", new int[]{1, 1}, new int[]{5, 6}), new int[]{2, 3});
  }
}
