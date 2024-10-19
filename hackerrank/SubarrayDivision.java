import java.util.*;

/*-
https://www.hackerrank.com/challenges/the-birthday-bar/problem

Subarray Division

Two children, Lily and Ron, want to share a chocolate bar. Each of the squares has an integer on it.

Lily decides to share a contiguous segment of the bar selected such that:
- The length of the segment matches Ron's birth month, and,
- The sum of the integers on the squares is equal to his birth day.

Determine how many ways she can divide the chocolate.

---

s = [2, 2, 1, 3, 2]
d = 4
m = 2

Lily wants to find segments summing to Ron's birth day, d=4 with a length equalling his birth month, m=2.
In this case, there are two segments meeting her criteria: [2, 2] and [1, 3].
*/

public class SubarrayDivision {
  public static int birthday(List<Integer> choco, int d, int m) {
    long currSum = 0;
    for (int i = 0; i < m - 1; i++) currSum += choco.get(i);

    int count = 0;
    for (int i = m - 1; i < choco.size(); i++) {
      int lastElem = i - m < 0 ? 0 : choco.get(i - m);
      currSum = currSum - lastElem + choco.get(i);
      if (currSum == d) count++;
    }
    return count;
  }

  public static void main(String[] args) {
    assert birthday(List.of(2, 2, 1, 3, 2), 4, 2) == 2;
  }
}
