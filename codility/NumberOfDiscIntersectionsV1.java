package codility;

import java.util.*;

/*-
We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers,
specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common
point (assuming that the discs contain their borders).

The figure below shows discs drawn for N = 6 and A as follows:

  A[0] = 1
  A[1] = 5
  A[2] = 2
  A[3] = 1
  A[4] = 4
  A[5] = 0


There are eleven (unordered) pairs of discs that intersect, namely:

discs 1 and 4 intersect, and both intersect with all the other discs;
disc 2 also intersects with discs 0 and 3.
Write a function:

class Solution { public int solution(int[] A); }

that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of
intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.

Given array A shown above, the function should return 11, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..2,147,483,647].

---

A[1] = 5 (-4, 6)
A[0] = 1 (-1, 1)
A[2] = 2 (0, 4)
A[4] = 4 (0, 8)
A[3] = 1 (2, 4)
A[5] = 0 (5, 5)

5 + 2 + 2 + 2 = 11

-3 3
2 6

TIMEOUT ERROR
*/

public class NumberOfDiscIntersectionsV1 {
  private static class Circle {
    public long start;
    public long end;

    public Circle(long start, long end) {
      this.start = start;
      this.end = end;
    }
  }

  public static int solution(int[] radiuses) {
    List<Circle> circles = new ArrayList<>();
    for (int i = 0; i < radiuses.length; i++) {
      circles.add(new Circle((long) i - radiuses[i], (long) i + radiuses[i])); // converts i to long before adding
    }
    circles.sort((circle1, circle2) -> {
      if (circle1.start < circle2.start || (circle1.start == circle2.start && circle1.end <= circle2.end))
        return -1;
      return 1;
    });

    int pairs = 0;
    for (int i = 0; i < radiuses.length; i++) {
      Circle leftCircle = circles.get(i);
      for (int j = i + 1; j < radiuses.length; j++) {
        Circle rightCircle = circles.get(j);
        if (leftCircle.end >= rightCircle.start) pairs++;
        else break;

        if (pairs > 10_000_000) return -1;
      }
    }
    return pairs;
  }

  public static void main(String[] args) {
    assert solution(new int[]{0}) == 0;
    assert solution(new int[]{0, 0}) == 0;
    assert solution(new int[]{0, Integer.MAX_VALUE}) == 1;
    assert solution(new int[]{0, 1}) == 1;
    assert solution(new int[]{0, 0, 1, 2}) == 3;
  }
}
