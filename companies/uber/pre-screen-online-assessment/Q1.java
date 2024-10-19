/*-
Let's say a triple (a, b, c) is a zigzag if either a < b > c or a > b < c.

Given an array of integers numbers, your task is to check all the triples of its consecutive elements for
being a zigzag. More formally, your task is to construct an array of length numbers.length - 2, where the ith
element of the output array equals 1 if the triple (numbers[i], numbers[i + 1], numbers[i + 2]) is a zigzag,
and 0 otherwise.

Example

• For numbers = [1, 2, 1, 3, 4], the output should be solution(numbers) = [1, 1, 0].
o (numbers[0], numbers[1], numbers[2]) = (1, 2, 1) is a zigzag, because 1 < 2 > 1;
o (numbers[1], numbers[2] , numbers[3]) = (2, 1, 3) is a zigzag, because 2 > 1 < 3;
o (numbers[2], numbers[3] , numbers[4]) = (1, 3, 4) is not a zigzag, because 1 < 3 < 4;

• For numbers = [1, 2, 3, 4], the output should be solution(numbers) = [0, 0];
Since all the elements of numbers are increasing, there are no zigzags.

• For numbers = [1000000000, 1000000000, 1000000000], the output should be solution(numbers) = [0].

Since all the elements of numbers are the same, there are no zigzags.
*/

public class Q1 {
  public static void main(String[] args) {
    //
  }
}
