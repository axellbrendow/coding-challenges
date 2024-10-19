# coding-challenges

LeetCode-style coding challenges

Other websites that I recommend: [LintCode](https://www.lintcode.com/), [NeetCode 150](https://neetcode.io/practice), [AlgoMonster](https://algo.monster/problems/stats) and [Tech Interview Handbook](https://www.techinterviewhandbook.org/coding-interview-prep/).

## How to run Java files ?

Use the JVM flag `-enableassertions` or `-ea` to run Java files. The VSCode config in this repository already includes this flag when running files through F5 key or run button.

```sh
cd leetcode/java
javac ./**/shared/*.java  # compiles common files
java -ea myjava/AddTwoNumbers.java  # works for java >= 11
javac myjava/AddTwoNumbers.java && java -ea myjava/AddTwoNumbers  # works for java < 11
```

## Some coding question topics:

- Two Pointers
  - Fast & Slow Pointers
    - Move slow by 1 and fast by 2
    - Move slow by 1 and fast by n
    - Move only fast until some point (e.g. distance between slow and fast is k)
- Cyclic Sort (consists of using the value as its own index to sort the array)
  - Find all Missing Numbers
  - Find all Duplicate Numbers
  - Find the Smallest Missing Positive Number
  - Find the First K Missing Positive Numbers
- Sliding Window
- Merge Intervals
- In-place Reversal of a LinkedList
- Doubly-Linked List
- Stacks
- Monotonic Stack
- Monotonic Queue
- Hash Maps
- Tree Breadth-First Search
- Tree Depth First Search
- Graphs
- Islands (Matrix Traversal)
- Two Heaps
- Design
- Subsets (permutations or combinations)
  - String Permutations by Changing Case
  - Unique Generalized Abbreviations
- Modified Binary Search
  - Ceiling of a number
  - Bitonic Array Maximum
- Mergesort
- Quicksort
- Quickselect
- Countingsort
- O(1) space
  - Majority Element
- Bit Manipulation
  - Two Single Numbers
  - Flip and Invert an Image
- Bitmask
- Top 'K' Elements
- K-way Merge
- Greedy Algorithms
- 0/1 Knapsack (Dynamic Programming)
  - Maximum Product Subarray (important problem, kadane's algorithm)
  - Equal Subset Sum Partition
  - Minimum Subset Sum Difference
  - Rod Cutting
  - Coin Change
- Backtracking
- Fibonacci Numbers
  - Staircase
  - House Thief
- Trie
- Topological Sort
- Palindromic Subsequence
  - Longest Palindromic Subsequence
  - Minimum Deletions in a String to make it a Palindrome
- Longest Common Substring
  - Maximum Sum Increasing Subsequence
  - Edit Distance
- Suffix Array
- Union Find
- Ordered Set
  - Longest Continuous Subarray
  - Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
- Prefix Sum
  - Find the Middle Index in Array
  - Maximum Size Subarray Sum Equals K
  - Subarray Sum Equals K
- Rolling Hash
- Data Stream
- Game Theory
- Number Theory
- Geometry
- Segment Tree
- Binary Indexed Tree
- Concurrency
- Probability and Statistics
- Bucket Sort
- Minimum Spanning Tree (Kruskal Algorithm)
- Strongly Connected Component
- Eulerian Circuit
- Radix Sort

## 150 questions selected by [NeetCode.io](https://neetcode.io/practice)

### Arrays & Hashing

- Contains Duplicate
- Valid Anagram
- Two Sum
- Group Anagrams
- Top K Frequent Elements
- Encode and Decode Strings
- Product of Array Except Self
- Valid Sudoku
- Longest Consecutive Sequence

### Two Pointers

- Valid Palindrome
- Two Sum II Input Array Is Sorted
- 3Sum
- Container With Most Water
- Trapping Rain Water

### Sliding Window

- Best Time to Buy And Sell Stock
- Longest Substring Without Repeating Characters
- Longest Repeating Character Replacement
- Permutation In String
- Minimum Window Substring
- Sliding Window Maximum

### Stack

- Valid Parentheses
- Min Stack
- Evaluate Reverse Polish Notation
- Generate Parentheses
- Daily Temperatures
- Car Fleet
- Largest Rectangle In Histogram

### Binary Search

- Binary Search
- Search a 2D Matrix
- Koko Eating Bananas
- Find Minimum In Rotated Sorted Array
- Search In Rotated Sorted Array
- Time Based Key Value Store
- Median of Two Sorted Arrays

### Linked List

- Reverse Linked List
- Merge Two Sorted Lists
- Reorder List
- Remove Nth Node From End of List
- Copy List With Random Pointer
- Add Two Numbers
- Linked List Cycle
- Find The Duplicate Number
- LRU Cache
- Merge K Sorted Lists
- Reverse Nodes In K Group

### Trees

- Invert Binary Tree
- Maximum Depth of Binary Tree
- Diameter of Binary Tree
- Balanced Binary Tree
- Same Tree
- Subtree of Another Tree
- Lowest Common Ancestor of a Binary Search Tree
- Binary Tree Level Order Traversal
- Binary Tree Right Side View
- Count Good Nodes In Binary Tree
- Validate Binary Search Tree
- Kth Smallest Element In a Bst
- Construct Binary Tree From Preorder And Inorder Traversal
- Binary Tree Maximum Path Sum
- Serialize And Deserialize Binary Tree

### Heap / Priority Queue

- Kth Largest Element In a Stream
- Last Stone Weight
- K Closest Points to Origin
- Kth Largest Element In An Array
- Task Scheduler
- Design Twitter
- Find Median From Data Stream

### Backtracking

- Subsets
- Combination Sum
- Permutations
- Subsets II
- Combination Sum II
- Word Search
- Palindrome Partitioning
- Letter Combinations of a Phone Number
- N Queens

### Tries

- Implement Trie Prefix Tree
- Design Add And Search Words Data Structure
- Word Search II

### Graphs

- Number of Islands
- Max Area of Island
- Clone Graph
- Walls And Gates
- Rotting Oranges
- Pacific Atlantic Water Flow
- Surrounded Regions
- Course Schedule
- Course Schedule II
- Graph Valid Tree
- Number of Connected Components In An Undirected Graph
- Redundant Connection
- Word Ladder

### Advanced Graphs

- Reconstruct Itinerary
- Min Cost to Connect All Points
- Network Delay Time
- Swim In Rising Water
- Alien Dictionary
- Cheapest Flights Within K Stops

### 1-D Dynamic Programming

- Climbing Stairs
- Min Cost Climbing Stairs
- House Robber
- House Robber II
- Longest Palindromic Substring
- Palindromic Substrings
- Decode Ways
- Coin Change
- Maximum Product Subarray
- Word Break
- Longest Increasing Subsequence
- Partition Equal Subset Sum

### 2-D Dynamic Programming

- Unique Paths
- Longest Common Subsequence
- Best Time to Buy And Sell Stock With Cooldown
- Coin Change II
- Target Sum
- Interleaving String
- Longest Increasing Path In a Matrix
- Distinct Subsequences
- Edit Distance
- Burst Balloons
- Regular Expression Matching

### Greedy

- Maximum Subarray
- Jump Game
- Jump Game II
- Gas Station
- Hand of Straights
- Merge Triplets to Form Target Triplet
- Partition Labels
- Valid Parenthesis String

### Intervals

- Insert Interval
- Merge Intervals
- Non Overlapping Intervals
- Meeting Rooms
- Meeting Rooms II
- Minimum Interval to Include Each Query

### Math & Geometry

- Rotate Image
- Spiral Matrix
- Set Matrix Zeroes
- Happy Number
- Plus One
- Pow(x, n)
- Multiply Strings
- Detect Squares

### Bit Manipulation

- Single Number
- Number of 1 Bits
- Counting Bits
- Reverse Bits
- Missing Number
- Sum of Two Integers
- Reverse Integer
