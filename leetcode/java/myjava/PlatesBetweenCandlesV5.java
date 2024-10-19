package myjava;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;
import java.util.*;

import myjava.shared.PythonInterpreter;

/*-
https://leetcode.com/problems/plates-between-candles/description/

There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed
string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.

You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the
substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles
that are in the substring. A plate is considered between candles if there is at least one candle to its left and
at least one candle to its right in the substring.

For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between
candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left
and right. Return an integer array answer where answer[i] is the answer to the ith query.

Example 1:
ex-1
Input: s = "**|**|***|", queries = [[2,5],[5,9]]
Output: [2,3]
Explanation:
- queries[0] has two plates between candles.
- queries[1] has three plates between candles.

Example 2:
Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
Output: [9,0,0,0,0]
Explanation:
- queries[0] has nine plates between candles.
- The other queries have zero plates between candles.

Constraints:

3 <= s.length <= 10^5
s consists of '*' and '|' characters.
1 <= queries.length <= 10^5
queries[i].length == 2
0 <= lefti <= righti < s.length

at every position I just need to know where is the first candle going to the right and going to the left
                        0123456789 10 11 12 13 14 15 16
                        ***||**|*| |  *  *  *  *  |  *
firstCandleToTheRight = 3333477799 10 15 15 15 15 15 --
firstCandleToTheLeft =  ---3444779 10 10 10 10 10 15 15
*/

public class PlatesBetweenCandlesV5 {
  public static int[] platesBetweenCandles(String s, int[][] queries) {
    int[] prefixSum = new int[s.length()];
    int[] firstCandleToTheLeft = new int[s.length()];
    int[] firstCandleToTheRight = new int[s.length()];

    prefixSum[0] = s.charAt(0) == '*' ? 1 : 0;
    firstCandleToTheLeft[0] = s.charAt(0) == '*' ? -1 : 0;
    firstCandleToTheRight[s.length() - 1] = s.charAt(s.length() - 1) == '*' ? -1 : s.length() - 1;

    for (int i = 1, j = s.length() - 2; j >= 0; i++, j--) {
      prefixSum[i] = prefixSum[i - 1] + (s.charAt(i) == '*' ? 1 : 0);
      firstCandleToTheLeft[i] = s.charAt(i) == '*' ? firstCandleToTheLeft[i - 1] : i;
      firstCandleToTheRight[j] = s.charAt(j) == '*' ? firstCandleToTheRight[j + 1] : j;
    }

    int[] output = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int leftCandle = firstCandleToTheRight[queries[i][0]];
      int rightCandle = firstCandleToTheLeft[queries[i][1]];
      output[i] = leftCandle != -1 && leftCandle < rightCandle
        ? prefixSum[rightCandle] - prefixSum[leftCandle]
        : 0;
    }
    return output;
  }

  public static void main(String[] args) throws IOException {
    assert Arrays.equals(
      platesBetweenCandles(
        "**|**|***|",
        new int[][]{
          new int[]{2, 5},
          new int[]{5, 9}
        }
      ),
      new int[]{2, 3}
    );

    assert Arrays.equals(
      platesBetweenCandles(
        "|**|***",
        new int[][]{
          new int[]{0, 3},
          new int[]{4, 6},
          new int[]{0, 3}
        }
      ),
      new int[]{2, 0, 2}
    );

    assert Arrays.equals(
      platesBetweenCandles(
        "***|**|*****|**||**|*",
        new int[][]{
          new int[]{1, 17},
          new int[]{4, 5},
          new int[]{14, 17},
          new int[]{5, 11},
          new int[]{15, 16}
        }
      ),
      new int[]{9, 0, 0, 0, 0}
    );

    try (final var br = Files.newBufferedReader(Paths.get("leetcode/java/myjava/PlatesBetweenCandles.in"))) {
      String str = (String) PythonInterpreter.parse(br.readLine());

      @SuppressWarnings("unchecked")
      List<List<Integer>> queries = (List<List<Integer>>) PythonInterpreter.parse(br.readLine());
      int[][] primitiveQueries = new int[queries.size()][queries.get(0).size()];
      for (int i = 0; i < primitiveQueries.length; i++)
        for (int j = 0; j < primitiveQueries[0].length; j++)
          primitiveQueries[i][j] = queries.get(i).get(j);

      @SuppressWarnings("unchecked")
      List<Integer> expected = (List<Integer>) PythonInterpreter.parse(br.readLine());
      int[] primitiveExpected = expected.stream().mapToInt(val -> val).toArray();

      assert Arrays.equals(platesBetweenCandles(str, primitiveQueries), primitiveExpected);
    }
  }
}
