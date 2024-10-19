package codility;

/*-
https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/

Write a function:

class Solution { public int solution(int A, int B, int K); }

that, given three integers A, B and K, returns the number of integers within the range [A..B] that are
divisible by K, i.e.:

{ i : A ≤ i ≤ B, i mod K = 0 }

For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers
divisible by 2 within the range [6..11], namely 6, 8 and 10.

Write an efficient algorithm for the following assumptions:

A and B are integers within the range [0..2,000,000,000];
K is an integer within the range [1..2,000,000,000];
A ≤ B.

---

6..11 k=2

5..11 k=2

5 % 2 != 0
5 / 2 = 2

first_elem = (
    if A % k != 0 -> k * A / k + k
    else -> A
)

second_elem = (
    if 11 % 2 != 0 -> B / k = something -> something * k = 10
    else -> B
)

if first_elem == second_elem -> 1
6 10
2 2
----
3 5
5 - 3 + 1 = 3
second_elem / k - first_elem / k + 1
*/

public class CountDiv {
  public static int solution(int A, int B, int K) {
    int firstElem = A % K != 0 ? K * (A / K) + K : A;
    int lastElem = B % K != 0 ? K * (B / K) : B;
    if (firstElem == lastElem) return 1;
    else return lastElem / K - firstElem / K + 1;
  }

  public static void main(String[] args) {
    assert solution(6, 11, 2) == 3;
    assert solution(5, 11, 2) == 3;
    assert solution(5, 10, 2) == 3;
    assert solution(5, 9, 2) == 2;
  }
}
