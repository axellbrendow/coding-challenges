package mindbody;

/*-
Prepare a notification of the given message which will be displayed on a mobile device. The message is made of
words separated by single spaces. The length of the notification is limited to K characters. If the message is
too long to be displayed fully, some words from the end of the message should be cropped, keeping in mind that:
- the notification should be as long as possible;
- only whole words can be cropped;
- if any words are cropped, the notification should end with '...', the dots should be separated from the last
  word by a single space;
- the notification may not exceed the K-character limit, including the dots.

If all the words need to be cropped, the notification is '...' (three dots without a space).
For example, for message = "And now here is my secret" and K = 15, the notification is "And now ...". Note that:
- the notification "And ..." would be incorrect, because there is a longer correct notification;
- the notification "And now her ..." would be incorrect, because the original message is cropped through the
  middle of a word;
- the notification "And now ... " would be incorrect, because it ends with a space;
- the notification "And now here..." would be incorrect, because there is no space before the three dots;
- the notification "And now here ..." would be incorrect, because it exceeds the 15-character limit.

Write a function

class Solution { public String solution(String message, int K); }

that given a string message and an integer K, returns the notification to display, which has no more than K
characters, as described above.

Examples:
1 - For message = "And now here is my secret" and K = 15, the function should return "And now ..."
2 - For message = "There is an animal with four legs" and K = 15, the function should return "There is an ...".
3 - For message = "super dog" and K = 4, the function should return "...".
3 - For message = "how are you" and K = 20, the function should return "how are you".

Assume that:
- K is an integer within the range [3..500];
- the length of string message is within the range [1...500];
- string message is made of English letters ('a'-'z','A'-'Z') and spaces (' ');
- message does not contain spaces at the beginning or at the end;
- words are separated by a single space (there are never two or more consecutive spaces).

In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
*/

public class LimitedLengthNotification {
  public static String solution(String message, int K) {
    if (message.length() <= K) return message;
    String[] words = message.split(" ");
    StringBuilder sb = new StringBuilder();
    if (words[0].length() + 4 <= K) sb.append(words[0]);
    else return "...";
    for (int i = 1; i < words.length; i++) {
      if (sb.length() + 1 + words[i].length() + 4 <= K) sb.append(" ").append(words[i]);
      else break;
    }
    return sb.append(" ...").toString();
  }

  public static void main(String[] args) {
    assert solution("And now here is my secret", 15).equals("And now ...");
    assert solution("There is an animal with four legs", 15).equals("There is an ...");
    assert solution("super dog", 4).equals("...");
    assert solution("how are you", 20).equals("how are you");
  }
}
