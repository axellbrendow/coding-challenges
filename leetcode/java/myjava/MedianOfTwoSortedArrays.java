package myjava;

/*-
https://leetcode.com/problems/median-of-two-sorted-arrays/description/
https://www.youtube.com/watch?v=q6IEA26hvXc

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-10^6 <= nums1[i], nums2[i] <= 10^6

---

Key points about this question:
- binary search is NOT USED to find the median directly
- left and right pointers in the binary search will move to an **index** in nums2 and NOT a value or the median
- binary search is used to find the **index** in nums2 which, together with the index in nums1, separate
the arrays into two parts where the smallest elements are on the indices from 0...nums1Index and 0..nums2Index.

nums1 = [1,1,1,1,1]
nums2 = [1,1,1,1,1]

nums1 = [1,2,3]
nums2 = [5,6,7,8,9]

nums1 = [5,6,7,8,9]
nums2 = [1,2,3]

nums1 = [5,6,7,8,9]
nums2 = [1]

5 6 7 7 8 8 9 9 10 10 11 11 12 12 13 13
nums1 = [5,6,7,8,9,10,11,12,13]
nums2 = [7,8,9,10,11,12,13]

nums1 = [5,6,7,8,9]
nums2 = [7,8,9]

nums1 = [1,2,3,4,5]
nums2 = [5,6,7,8,9]

nums1 = [1,2,3,4,5]
nums2 = [1,2,3,4,5]

nums1 = [1,2,3,4,5]
nums2 = [6,7,8,9,10]

nums1 = [6,7,8,9,10]
nums2 = [1,2,3,4,5]

nums1 = [6,7,8,9,10]
nums2 = [1,2,3,4,5]

nums1 = []
nums2 = []

nums1 = []
nums2 = [1,2,3,4,5]

nums1 = [1,2,3,4,5]
nums2 = []
*/

public class MedianOfTwoSortedArrays {
  public static void main(String[] args) {
    //
  }
}
