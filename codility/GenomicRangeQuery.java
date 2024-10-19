package codility;

import java.util.*;

/*-
A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the
types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer.
Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer
several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of
the given DNA sequence?

The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are
M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ≤ K < M)
requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions
P[K] and Q[K] (inclusive).

For example, consider string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
The answers to these M = 3 queries are as follows:

The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are
3 and 2 respectively, so the answer is 2.
The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose
impact factor is 1, so the answer is 1.
Write a function:

class Solution { public int[] solution(String S, int[] P, int[] Q); }

that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of
M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

Result array should be returned as an array of integers.

For example, given the string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
the function should return the values [2, 4, 1], as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
M is an integer within the range [1..50,000];
each element of arrays P and Q is an integer within the range [0..N - 1];
P[K] ≤ Q[K], where 0 ≤ K < M;
string S consists only of upper-case English letters A, C, G, T.

---

A: 1
C: 2
G: 3
T: 4

impact factor

---

1st attempt:

CAGCCTA
2132241
2112221 (smallest values of all increasing subarrays from left to right)
1122211 (smallest values of all increasing subarrays from right to left)

---

2nd attempt:

234432344323 443234432344323443234432344323443234432344 3234432344323443

---

3rd attempt:

CAGCCTA
0111112 (A)
1112333 (C)
0011111 (G)
0000010 (T)

---

4th attempt:

time: O(N + M)
space: O(N)

I can go to a different approach where, from the end of the window, I'll try to find the first
A, C, G or T to the left. If it is outside the window, I try the next letter.

0123456 (index)
CAGCCTA
-111116 (A)
0003444 (C)
--22222 (G)
-----55 (T)

100_000 * 50_000
5_000_000_000 TLE
*/

public class GenomicRangeQuery {
  public static int[] solution(String S, int[] P, int[] Q) {
    // prefix sum
    int[] firstAToTheLeft = new int[S.length()];
    Arrays.fill(firstAToTheLeft, -1);

    int[] firstCToTheLeft = new int[S.length()];
    Arrays.fill(firstCToTheLeft, -1);

    int[] firstGToTheLeft = new int[S.length()];
    Arrays.fill(firstGToTheLeft, -1);

    for (int i = 0; i < S.length(); i++) {
      if (i > 0) {
        firstAToTheLeft[i] = firstAToTheLeft[i - 1];
        firstCToTheLeft[i] = firstCToTheLeft[i - 1];
        firstGToTheLeft[i] = firstGToTheLeft[i - 1];
      }
      switch (S.charAt(i)) {
        case 'A':
          firstAToTheLeft[i] = i;
          break;
        case 'C':
          firstCToTheLeft[i] = i;
          break;
        case 'G':
          firstGToTheLeft[i] = i;
          break;
      }
    }

    // running the queries
    int[] output = new int[P.length];
    int outputCursor = 0;
    for (int i = 0; i < P.length; i++) {
      if (firstAToTheLeft[Q[i]] >= P[i]) output[outputCursor++] = 1;
      else if (firstCToTheLeft[Q[i]] >= P[i]) output[outputCursor++] = 2;
      else if (firstGToTheLeft[Q[i]] >= P[i]) output[outputCursor++] = 3;
      else output[outputCursor++] = 4;
    }
    return output;
  }

  public static void main(String[] args) {
    assert Arrays.equals(
      solution("CAGCCTA", new int[]{2, 5, 0}, new int[]{4, 5, 6}),
      new int[]{2, 4, 1}
    );
  }
}
