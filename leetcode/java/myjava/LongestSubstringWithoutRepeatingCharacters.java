package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0;
        int j = 0;
        int lengthOfLongestSubstr = 0;
        final var set = new HashSet<Character>();
        while (j < s.length()) {
            while (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i++));
            }
            set.add(s.charAt(j));
            lengthOfLongestSubstr = Math.max(lengthOfLongestSubstr, set.size());
            j++;
        }
        return lengthOfLongestSubstr;
    }
}
