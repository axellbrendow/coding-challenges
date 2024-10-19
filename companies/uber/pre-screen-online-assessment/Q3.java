/*-
Similar to https://leetcode.com/problems/design-memory-allocator/ but has some differences

int[] func(int[] memory, int[][] queries)

memory has 0's and 1's only
0 means free memory
1 means memory in use

two types of queries, alloc and erase
alloc 3
erase 1

alloc 3 should find the first block of 8 units and try to alloc 3 units starting from the first position, if
it is possible to allocate all units, assign an incremental id to this allocation. Allocations should always
start from an index that is divisible by 8. If it's not possible to allocate all units, place -1 in the output.
Otherwise place the index where the allocation starts.

erase 1 should deallocate the memory associated with the allocation of id 1. If there's no allocation with id 1,
place -1 in the output. Otherwise, place the allocated length in the output.
*/

public class Q3 {
  public static void main(String[] args) {
    //
  }
}
