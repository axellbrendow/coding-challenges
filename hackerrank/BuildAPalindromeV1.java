import java.util.*;

/*-
https://www.hackerrank.com/challenges/challenging-palindromes/problem

Possible optimized solution:
https://github.com/mariiaiurchenko/hackerrank/blob/master/HakerRank/src/main/java/com/spring/tutorial/HakerRank/strings/BuildAPalindrome.java

Brute Force V1

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

public class BuildAPalindromeV1 {
  public static List<String> generateSubstrings(String str) {
    List<String> substrs = new ArrayList<>();
    for (int i = 0; i < str.length(); i++) {
      StringBuilder strBuilder = new StringBuilder(str.length());
      strBuilder.append(str.charAt(i));
      substrs.add(strBuilder.toString());
      for (int j = i + 1; j < str.length(); j++) {
        strBuilder.append(str.charAt(j));
        substrs.add(strBuilder.toString());
      }
    }
    return substrs;
  }

  public static boolean isPalindrome(String str) {
    for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
      if (str.charAt(i) != str.charAt(j)) {
        return false;
      }
    }
    return true;
  }

  public static String buildAPalindrome(String a, String b) {
    String longest = "";
    List<String> aSubstrs = generateSubstrings(a);
    List<String> bSubstrs = generateSubstrings(b);
    for (String aSubstr : aSubstrs) {
      for (String bSubstr : bSubstrs) {
        String palindrome = aSubstr + bSubstr;
        if (!isPalindrome(palindrome)) continue;
        if (
          palindrome.length() > longest.length()
          || (palindrome.length() == longest.length() && palindrome.compareTo(longest) < 0)
        ) {
          longest = palindrome;
        }
      }
    }
    return longest.isEmpty() ? "-1" : longest;
  }

  public static void main(String[] args) {
    assert buildAPalindrome("abc", "aba").equals("ababa");
    assert buildAPalindrome("b", "a").equals("-1");
    assert buildAPalindrome("ba", "a").equals("aa");
  }
}
