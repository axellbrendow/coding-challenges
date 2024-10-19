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

***||**|*||****|*
candles = [3,4,7,9,10,15]
*/

public class PlatesBetweenCandlesV2 {
  private static int findCandleToTheRight(List<Integer> candles, int fromIndex) {
    int candleIndex = -1, left = 0, right = candles.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (fromIndex <= candles.get(mid)) {
        candleIndex = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return candleIndex != -1 ? candles.get(candleIndex) : -1;
  }

  private static int findCandleToTheLeft(List<Integer> candles, int fromIndex) {
    int candleIndex = -1, left = 0, right = candles.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (candles.get(mid) <= fromIndex) {
        candleIndex = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return candleIndex != -1 ? candles.get(candleIndex) : -1;
  }

  public static int[] platesBetweenCandles(String s, int[][] queries) {
    List<Integer> candles = new ArrayList<>();
    int[] prefixSum = new int[s.length()];
    if (s.charAt(0) == '*') prefixSum[0] = 1;
    for (int i = 0; i < s.length(); i++) {
      if (i > 0) prefixSum[i] = prefixSum[i - 1];
      if (s.charAt(i) == '*') prefixSum[i]++;
      else candles.add(i);
    }

    int[] output = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int leftCandle = findCandleToTheRight(candles, queries[i][0]);
      if (leftCandle == -1 || leftCandle > queries[i][1]) continue;
      int rightCandle = findCandleToTheLeft(candles, queries[i][1]);
      if (rightCandle == -1 || rightCandle < queries[i][0]) continue;
      output[i] = prefixSum[rightCandle] - prefixSum[leftCandle];
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
