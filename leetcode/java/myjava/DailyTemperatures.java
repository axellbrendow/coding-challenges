package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/daily-temperatures/description/

Given an array of integers temperatures represents the daily temperatures, return an array answer such
that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
If there is no future day for which this is possible, keep answer[i] == 0 instead.

Constraints:

1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100

temperatures = [73, 74, 60, 61, 75, 74]
answer =       [2 , 3 , 1 , 1 , 0 , 0 ]

temperatures = [70, 71, 72, 73, 74, 75]
answer =       [2 , 3 , 1 , 1 , 0 , 0 ]

temperatures = [75, 74, 73, 72, 71, 70]
answer =       [2 , 3 , 1 , 1 , 0 , 0 ]

temperatures = [75, 73, 77, 71, 79, 69]
answer =       [2 , 2 , 2 , 2 , 0 , 0 ]
*/

public class DailyTemperatures {
  public static int[] dailyTemperatures(int[] temperatures) {
    int[] answer = new int[temperatures.length];
    Deque<Integer> stack = new LinkedList<>();
    for (int i = temperatures.length - 1; i >= 0; i--) {
      if (i != temperatures.length - 1) stack.push(i + 1);
      while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
        stack.pop();
      }
      answer[i] = stack.isEmpty() ? 0 : stack.peek() - i;
    }
    return answer;
  }

  public static void main(String[] args) {
    assert Arrays.equals(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}), new int[]{1,1,4,2,1,1,0,0});
    assert Arrays.equals(dailyTemperatures(new int[]{30,40,50,60}), new int[]{1,1,1,0});
    assert Arrays.equals(dailyTemperatures(new int[]{30,60,90}), new int[]{1,1,0});
  }
}
