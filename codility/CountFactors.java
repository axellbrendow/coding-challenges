package codility;

/*-
https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/count_factors/

A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.

For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).

Write a function:

class Solution { public int solution(int N); }

that, given a positive integer N, returns the number of its factors.

For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8,
12, 24. There are no other factors of 24.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..2,147,483,647].

---

36 factors:
1 * 36
2 * 18
3 * 12
4 * 9
6 * 6 <--- special case
9 * 4 (repeated)
12 * 3 (repeated)
18 * 2 (repeated)
36 * 1 (repeated)

Take N = 36 as an example, it's obvious that after 6 * 6, if we try to find other factors that multiplied will
result in 36, we need to increase the first 6 and decrease the second 6, for example 9 * 4, but that's already
a multiplication that we considered when we did 4 * 9. This indicates that the highest value the first factor
can have is actually the square root of 36. Any value greater than the square root will force the second factor
to decrease and we know we would have already considered this multiplication.
*/

public class CountFactors {
  public static int solution(int N) {
    int factors = 0;
    double sqrt = Math.sqrt(N);
    for (int factor = 1; factor < sqrt; factor++)
      if (N % factor == 0) factors += 2;

    if ((int) sqrt * (int) sqrt == N) factors++;
    return factors;
  }

  public static void main(String[] args) {
    assert solution(1) == 1;
    assert solution(2) == 2;
    assert solution(3) == 2;
    assert solution(5) == 2;
    assert solution(7) == 2;
    assert solution(11) == 2;
    assert solution(24) == 8;
    assert solution(36) == 9;
    assert solution(41) == 2;
  }
}
