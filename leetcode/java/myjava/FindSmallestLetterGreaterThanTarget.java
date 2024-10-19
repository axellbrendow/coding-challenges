package myjava;

/*-
https://leetcode.com/problems/find-smallest-letter-greater-than-target/
You are given an array of characters letters that is sorted in non-decreasing order, and a character target.
There are at least two different characters in letters. Return the smallest character in letters that is
lexicographically greater than target. If such a character does not exist, return the first character in letters.

Theory base: https://algo.monster/problems/binary_search_boundary
  An array of boolean values is divided into two sections: The left section consists of all false, and the
  right section consists of all true. Find the First True in a Sorted Boolean Array of the right section, i.e.,
  the index of the first true element. If there is no true element, return -1.
  Input: arr = [false, false, true, true, true]
  Output: 2
  Explanation: The first true's index is 2.

*/

public class FindSmallestLetterGreaterThanTarget {
  public static char findSmallestLetterGreaterThanTarget(char[] letters, char target) {
    int left = 0;
    int right = letters.length - 1;
    char smallestLetter = letters[0];
    while (left <= right) {
      int middle = (left + right) / 2;
      if (letters[middle] > target) {
        smallestLetter = letters[middle];
        right = middle - 1;
      } else {
        left = middle + 1;
      }
    }
    return smallestLetter;
  }

  public static void main(String[] args) {
    assert findSmallestLetterGreaterThanTarget(
      new char[]{
        'c', 'f', 'j'
      },
      /*target*/ 'a'
    ) == 'c';

    assert findSmallestLetterGreaterThanTarget(
      new char[]{
        'c', 'f', 'j'
      },
      /*target*/ 'c'
    ) == 'f';

    assert findSmallestLetterGreaterThanTarget(
      new char[]{
        'x', 'x', 'y', 'y'
      },
      /*target*/ 'z'
    ) == 'x';
  }
}
