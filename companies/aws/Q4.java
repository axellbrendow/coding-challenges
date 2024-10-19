package aws;

/*-
https://leetcode.com/discuss/interview-question/5619722/Amazon-or-Online-Assessment-or-2024/
https://leetcode.com/discuss/interview-question/5470838/Amazon-or-Online-Assessment-or-SDE-2-or-2024/
https://leetcode.com/problems/max-consecutive-ones-iii/description/
https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/
https://www.google.com/search?q=Amazon+has+a+cluster+of+n+servers+Given+a+binary+string+server_states+of+length&rlz=1C1ONGR_pt-PTBR1004BR1004&oq=Amazon+has+a+cluster+of+n+servers+Given+a+binary+string+server_states+of+length&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBCDEyNzJqMWo3qAIAsAIA&sourceid=chrome&ie=UTF-8

Amazon has a cluster of n servers. Some are in the OFF state while others are ON. The developers responsible
for maintaining the servers have access to a special operation to change the states. In one operation, they
can choose a contiguous sequence of servers and flip their states, i.e., ON to OFF and vice versa. Due to
operational constraints, this operation can be performed on the cluster a maximum of k times.

Given a binary string server_states, of length n, where '1' represents ON, '0' represents OFF, and an integer k,
find the maximum possible number of consecutive ON servers after at most k operations.

Example 1:
Suppose server_states = "1001", and k = 2.
The indices [1..2] can be chosen and flipped in one operation as follows:
Thus in one operation, the maximum number of consecutive ON servers is 4. It is optimal to stop after 1 operation.

Example 2:
server_states = "00010"
k = 1
Output: 4
Explanation
It is optimal to apply the special operations on consecutive indices (0-based indexing) as follows:
Flip indices [0..2] giving states = "11110"
After 1 operation, there are a maximum of 4 consecutive ON servers.

Example 3:
server_states : "11101010110011"
k : 2
Output : 8
Explanation
It is optimal to apply the special operations on consecutive indices (0-based indexing) as follows:
Flip indices [10..11] giving states = "11101010111111"
Flip indices [7..7] giving states = "11101011111111"
After 2 operations, a maximum of 8 consecutive ON servers can be obtained.

server_states : "11101010110011"
                 i
                 j
numBlocksOfZeros = 0

server_states : "11101010110011"
                 i
                  j
numBlocksOfZeros = 0

server_states : "11101010110011"
                 i
                   j
numBlocksOfZeros = 0

server_states : "11101010110011"
                 i
                    j
numBlocksOfZeros = 1

server_states : "11101010110011"
                 i
                     j
numBlocksOfZeros = 1

server_states : "11101010110011"
                 i
                      j (best solution until now = 6)
numBlocksOfZeros = 2

server_states : "11101010110011"
                 i
                       j (best solution until now = 7)
numBlocksOfZeros = 2

server_states : "11101010110011"
                 i (move i until numBlocksOfZeros < k)
                        j
numBlocksOfZeros = 2

server_states : "11101010110011"
                  i (move i until numBlocksOfZeros < k)
                        j
numBlocksOfZeros = 2

server_states : "11101010110011"
                   i (move i until numBlocksOfZeros < k)
                        j
numBlocksOfZeros = 2

server_states : "11101010110011"
                    i (move i while i is at a zero)
                        j
numBlocksOfZeros = 2

server_states : "11101010110011"
                     i (move i while i is at a zero)
                        j
numBlocksOfZeros = 2

server_states : "11101010110011"
                     i
                         j
numBlocksOfZeros = 2

server_states : "11101010110011"
                     i
                          j
numBlocksOfZeros = 2

server_states : "11101010110011"
                      i (move i while i is at a zero)
                           j
numBlocksOfZeros = 2

server_states : "11101010110011"
                       i
                           j (move j while j is at a zero)
numBlocksOfZeros = 2

server_states : "11101010110011"
                       i
                            j (move j while j is at a zero)
numBlocksOfZeros = 2

server_states : "11101010110011"
                       i
                             j (move j while j is at a one)
numBlocksOfZeros = 2

server_states : "11101010110011"
                       i
                              j (move j while j is at a one)
numBlocksOfZeros = 2

server_states : "11101010110011"
                       i
                               j
numBlocksOfZeros = 2

Constraints

1 ≤ numServers ≤ 2 * 10⁵
1 ≤ k ≤ 2 * 10⁵
server_states contains only 0s and 1s
*/

public class Q4 {
  private static int skip(String serverStates, char c, int from) {
    for (int i = from; i < serverStates.length(); i++)
      if (serverStates.charAt(i) != c) return i;
    return serverStates.length();
  }

  private static int getMaxConsecutiveON(String serverStates, int k) {
    int i = 0, j = 0, numBlocksOfZeros = 0, maxConsecutiveOnes = 0;
    while (j < serverStates.length()) {
      j = skip(serverStates, '1', j);
      j = skip(serverStates, '0', j);
      j = skip(serverStates, '1', j); // These 3 lines together add a block of 0s to the window
      maxConsecutiveOnes = Math.max(maxConsecutiveOnes, j - i);
      numBlocksOfZeros++;
      if (numBlocksOfZeros == k) {
        i = skip(serverStates, '1', i);
        i = skip(serverStates, '0', i); // These 2 lines together remove one block of 0s from the window
        numBlocksOfZeros--;
      }
    }
    return maxConsecutiveOnes;
  }

  public static void main(String[] args) {
    assert getMaxConsecutiveON("1", 1) == 1;
    assert getMaxConsecutiveON("0", 1) == 1;
    assert getMaxConsecutiveON("11101010110011", 1) == 6;
    assert getMaxConsecutiveON("11101010110011", 2) == 8;
  }
}
