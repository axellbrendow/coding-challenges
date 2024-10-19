package aws;

import java.util.*;

/*-
1. Optimizing Box Weights

An Amazon Fulfillment Associate has a set of items that need to be packed into two boxes. Given an integer
array of the item weights (arr) to be packed, divide the item weights into two subsets, A and B, for packing
into the associated boxes, while respecting the following conditions:

- The intersection of A and B is null.
- The union A and B is equal to the original array.
- The number of elements in subset A is minimal.
- The sum of A's weights is greater than the sum of B's weights.
- Return the subset A in increasing order where the sum of A's weights is greater than the sum of B's weights.
- If more than one subset A exists, return the one with the maximal total weight.

Example:
arr = [3, 7, 5, 6, 2]

The 2 subsets in arr that satisfy the conditions for A are [5, 7] and [6, 7]

A is minimal (size 2)

Sum(A)=(5+7)=12 > Sum(B)=(2+3+6)=11

Sum(A)(6+7)=13 > Sum(B)=(2+3+5)-10

The intersection of A and B is null and their union is equal to arr.

The subset A where the sum of its weight is maximal is [6, 7].

Constraints

1 <= n <= 10^5
1 <= arr[t] <= 10^5 (where 0 <= t < n)
10^5*10^5 = 10^10 = 10 000 000 000

---

input = [4,1,1,1]
output = [4]

input = [1,1,1]
output = [1,1]

input = [1,1]
output = [1,1]

input = [1]
output = [1]

arr = [3, 7, 5, 6, 2], sum = 23, 23 / 2 + 1 = 11 + 1 = 12
setA = [3]

You need to find a subset where the sum of all elements is >= sum(arr)/2 + 1

4 1 1 1
{0}
{0, 4} (add 4 to all elements)
{0, 4, 1, 5} (add 1 to all elements)
{0, 4, 1, 5, 2, 6} (add 1 to all elements)
{0, 4, 1, 5, 2, 6, 3, 7} (add 1 to all elements)
*/

public class Q1 {
  private static int[] minimalHeaviestSetA(int[] arr) {
    //
  }

  public static void main(String[] args) {
    assert Arrays.equals(minimalHeaviestSetA(new int[]{1}), new int[]{1});
    assert Arrays.equals(minimalHeaviestSetA(new int[]{1, 1}), new int[]{1, 1});
    assert Arrays.equals(minimalHeaviestSetA(new int[]{1, 1, 1}), new int[]{1, 1});
    assert Arrays.equals(minimalHeaviestSetA(new int[]{4, 1, 1, 1}), new int[]{4});
    assert Arrays.equals(minimalHeaviestSetA(new int[]{3, 7, 5, 6, 2}), new int[]{7, 6});
  }
}
