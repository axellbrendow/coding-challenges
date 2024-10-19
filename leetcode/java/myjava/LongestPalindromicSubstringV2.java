package myjava;

class LongestPalindromicSubstringV2 {
  public static String longestPalindrome(String s) {
    final var isPalindromeBetween = new boolean[s.length()][s.length()];
    var longestPalindrome = "";

    for (int currLength = 1; currLength <= isPalindromeBetween.length; currLength++) {
      for (int start = 0; start < isPalindromeBetween.length - currLength + 1; start++) {
        final var end = start + currLength - 1;
        isPalindromeBetween[start][end] = s.charAt(start) == s.charAt(end) && (currLength == 1 ||
          currLength == 2 ||
          isPalindromeBetween[start + 1][end - 1]);

        if (isPalindromeBetween[start][end] && currLength > longestPalindrome.length()) {
          longestPalindrome = s.substring(start, end + 1);
        }
      }
    }

    return longestPalindrome;
  }

  public static void main(String[] args) {
    assert longestPalindrome("").equals("");
    assert longestPalindrome("a").equals("a");
    assert longestPalindrome("ab").equals("a");
    assert longestPalindrome("abc").equals("a");
    assert longestPalindrome("abb").equals("bb");
    assert longestPalindrome("bab").equals("bab");
  }
}
