import java.util.*;

/*-
https://www.hackerrank.com/challenges/challenging-palindromes/problem

Possible optimized solution:
https://github.com/mariiaiurchenko/hackerrank/blob/master/HakerRank/src/main/java/com/spring/tutorial/HakerRank/strings/BuildAPalindrome.java

Brute Force V2

a = "abc"
b = "abc"
s possibilities are a, b, c, aa, bb, cc, aba, bcb, bab, cbc

a = "aaa"
b = "ccc"
s possibilities are a, c, aaa, ccc

a = "aaa"
b = "aaa"
s possibilities are a, aa, aaa, aaaa, aaaaa, aaaaaa

a = "abc"
b = "aba"
s possibilities are a, b, c, aa, aba, abba, abcba, bb, bab, bcb
*/

public class BuildAPalindromeV2 {
  public static String buildAPalindrome(String a, String b) {
    String longest = "";
    for (int ai = 0; ai < a.length(); ai++) {
      StringBuilder aBuilder = new StringBuilder(a.length());
      for (int aj = ai; aj < a.length(); aj++) {
        aBuilder.append(a.charAt(aj));
        for (int bi = 0; bi < b.length(); bi++) {
          StringBuilder bBuilder = new StringBuilder(b.length());
          for (int bj = bi; bj < b.length(); bj++) {
            bBuilder.append(b.charAt(bj));
            String palindrome = new StringBuilder(aBuilder.length() + bBuilder.length())
              .append(aBuilder)
              .append(bBuilder)
              .toString();
            if (!palindrome.equals(new StringBuilder(palindrome).reverse().toString())) continue;
            if (
              palindrome.length() > longest.length()
              || (palindrome.length() == longest.length() && palindrome.compareTo(longest) < 0)
            ) {
              longest = palindrome;
            }
          }
        }
      }
    }
    return longest.isEmpty() ? "-1" : longest.toString();
  }

  public static void main(String[] args) {
    assert buildAPalindrome("abc", "aba").equals("ababa");
    assert buildAPalindrome("b", "a").equals("-1");
    assert buildAPalindrome("ba", "a").equals("aa");
  }
}
