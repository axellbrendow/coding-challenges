package myjava;

/*-
https://leetcode.com/problems/valid-palindrome/description/

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include
letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

---

So the permitted characters are 0-9 a-z
A-Z should be transformed to a-z

toLowerCase -> c - 'A' + 'a'

1 <= s.length <= 2 * 10^5
s consists only of printable ASCII characters.

s = ""
return true

s = ".."
return true

s = "a"
return true

s = "ab"
return false

s = "aba"
return true

s = "abba"
return true

s = " a. b? A"
return true

s = " a. b? A"
     i      j (Increase i while it's not 0-9a-zA-Z)
      i     j (Increase i while it's not 0-9a-zA-Z)
      i     j (Decrease j while it's not 0-9a-zA-Z)
      i     j lowerCase(a) == lowerCase(A)
       i   j  increase i and decrease j
         i j  (Increase i while it's not 0-9a-zA-Z)
         i    (Decrease j while it's not 0-9a-zA-Z)
         j    
         i    stop as pointers crossed
         j    
return true

*/

public class ValidPalindrome {
  public static char lowerCase(char c) {
    return 'a' <= c && c <= 'z' ? c : (char)(c - 'A' + 'a');
  }

  public static boolean alphanumeric(char c) {
    return (
      'a' <= c && c <= 'z' ||
      'A' <= c && c <= 'Z' ||
      '0' <= c && c <= '9'
    );
  }

  public static boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
      while (i < j && !alphanumeric(s.charAt(i))) i++;
      while (i < j && !alphanumeric(s.charAt(j))) j--;
      if (i < j && lowerCase(s.charAt(i)) != lowerCase(s.charAt(j))) {
        return false;
      } else {
        i++;
        j--;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    assert isPalindrome("") == true;
    assert isPalindrome("..") == true;
    assert isPalindrome("a") == true;
    assert isPalindrome("ab") == false;
    assert isPalindrome("aba") == true;
    assert isPalindrome("abba") == true;
    assert isPalindrome(" a. b? A") == true;
  }
}
