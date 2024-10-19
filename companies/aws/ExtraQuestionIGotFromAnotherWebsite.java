package aws;

/*-
Amazon’s database doesn’t support very large numbers, so numbers are stored as a string of binary characters,
'0' and '1'. Accidentally, a '!' was entered at some positions, and it is unknown whether they should be '0' or '1'.

The string of incorrect data consists of the characters '0', '1', and '!' where '!' is the character that was
entered incorrectly. '!' can be replaced with either '0' or '1'. Due to some internal faults, some errors are
generated every time '0' and '1' occur together as '01' or '10' in any subsequence of the string. It is observed that:

The number of errors a subsequence '01' generates is x.
The number of errors a subsequence '10' generates is y.
Problem: Determine the minimum total errors generated. Since the answer can be very large, return
it modulo 109 + 710^9 + 7109 + 7.

Example:
errorString = "101!1"
x = 2
y = 3
If the '!' at index 3 is replaced with '0', the string becomes "10101". The number of times the subsequence '01'
occurs is 3 at indices (1, 2), (1, 4), and (3, 4). The number of times the subsequence '10' occurs is also 3 at
indices (1, 2), (1, 3), and (3, 4). The total number of errors is 3 * x + 3 * y = 6 + 9 = 15.

The minimum number of errors is min(9, 15) % (10^9 + 7).

Function Description: Complete the function getMinErrors in the editor below.
getMinErrors has the following parameters:
string errorString: The erroneous string containing '0', '1', and '!' characters.
int x: The error count for subsequence '01'.
int y: The error count for subsequence '10'.

Returns:
The minimum number of errors.
*/

public class ExtraQuestionIGotFromAnotherWebsite {
  public static void main(String[] args) {
    //
  }
}
