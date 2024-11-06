package ada_google_prep_program;

import java.util.*;

public class Q1FindPatternsMatchingAnyWords {
  private static boolean matches(String word, String pattern) {
    if (word.length() != pattern.length()) return false;
    for (int i = 0; i < pattern.length(); i++)
      if (word.charAt(i) != pattern.charAt(i) && pattern.charAt(i) != '.') return false;
    return true;
  }

  public static String[] findPatternsThatMatchAnyWords(String[] words, String[] patterns) {
    Collection<String> patternsThatMatchAnyWords = new ArrayList<>();
    for (String pattern : patterns)
      for (String word : words)
        if (matches(word, pattern)) {
          patternsThatMatchAnyWords.add(pattern);
          break;
        }
    return patternsThatMatchAnyWords.toArray(String[]::new);
  }

  public static void main(String[] args) {
    assert Arrays.equals(
      findPatternsThatMatchAnyWords(
        new String[]{"apple", "aws"}, new String[]{"a..le", "...", "....", "aws", "aws.", "aw"}
      ),
      new String[]{"a..le", "...", "aws"}
    );
  }
}
