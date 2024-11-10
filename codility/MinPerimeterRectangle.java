package codility;

/*-
An integer N is given, representing the area of some rectangle.

The area of a rectangle whose sides are of length A and B is A * B, and the perimeter is 2 * (A + B).

The goal is to find the minimal perimeter of any rectangle whose area equals N. The sides of this rectangle
should be only integers.

For example, given integer N = 30, rectangles of area 30 are:

(1, 30), with a perimeter of 62,
(2, 15), with a perimeter of 34,
(3, 10), with a perimeter of 26,
(5, 6), with a perimeter of 22.
Write a function:

class Solution { public int solution(int N); }

that, given an integer N, returns the minimal perimeter of any rectangle whose area is exactly equal to N.

For example, given an integer N = 30, the function should return 22, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..1,000,000,000].

---

N = 100
1 * 100
2 * 50
4 * 25
5 * 20
10 * 10

N = 30
1 * 30
2 * 15
3 * 10
5 * 6

N = 39
1 * 39
3 * 13

A * B = N
B = N / A
*/

public class MinPerimeterRectangle {
  public static int solution(int N) {
    for (int factor = (int) Math.sqrt(N); factor >= 1; factor--)
      if (N % factor == 0) return 2 * (factor + N / factor);
    throw new IllegalStateException("There must be a factor that is a side of the rectangle.");
  }

  public static void main(String[] args) {
    assert solution(1) == (1 + 1) * 2;
    assert solution(2) == (1 + 2) * 2;
    assert solution(3) == (1 + 3) * 2;
    assert solution(5) == (1 + 5) * 2;
    assert solution(7) == (1 + 7) * 2;
    assert solution(11) == (1 + 11) * 2;
    assert solution(30) == (5 + 6) * 2;
    assert solution(39) == (3 + 13) * 2;
  }
}
