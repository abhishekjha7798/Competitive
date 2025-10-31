# Competitive Coding Snippets Library

## Overview
This directory contains reusable code snippets for competitive programming. Simply copy the class or method you need and paste it into your Main.java file.

## Available Snippets

### 1. **Sieve.java** - Prime Number Generation
Prime number sieve using Sieve of Eratosthenes algorithm.
```java
boolean[] primes = Sieve.sieve(1000);
if (primes[7]) System.out.println("7 is prime");
```
- **Time**: O(n log log n)
- **Space**: O(n)

---

### 2. **GCD_LCM.java** - Greatest Common Divisor & Least Common Multiple
Calculate GCD and LCM using Euclidean algorithm.
```java
long g = GCD_LCM.gcd(12, 8);      // returns 4
long l = GCD_LCM.lcm(12, 8);      // returns 24
```

---

### 3. **BinarySearch.java** - Binary Search Variants
Three variants of binary search for different use cases.
```java
int idx = BinarySearch.binarySearch(arr, target);
int left = BinarySearch.binarySearchLeft(arr, target);   // First occurrence
int right = BinarySearch.binarySearchRight(arr, target); // Last occurrence
```
- **Time**: O(log n)

---

### 4. **UnionFind.java** - Disjoint Set Union (DSU)
Efficient data structure for cycle detection and connected components.
```java
UnionFind uf = new UnionFind(n);
uf.union(1, 2);
if (uf.find(1) == uf.find(2)) { /* connected */ }
```
- **Time**: Nearly O(1) with path compression and union by rank

---

### 5. **SegmentTree.java** - Range Queries & Point Updates
Segment tree for efficient range sum queries and point updates.
```java
SegmentTree st = new SegmentTree(arr);
st.update(0, 0, n-1, index, newValue);
int sum = st.query(0, 0, n-1, left, right);
```
- **Time**: O(log n) per operation

---

### 6. **Sorting.java** - Sorting Algorithms
Merge Sort and Quick Sort implementations.
```java
Sorting.mergeSort(arr, 0, arr.length - 1);
Sorting.quickSort(arr, 0, arr.length - 1);
```
- **Time**: O(n log n)

---

### 7. **Graph.java** - Graph Traversal
BFS and DFS implementations for graph exploration.
```java
List<List<Integer>> adj = new ArrayList<>();
Graph.bfs(adj, startNode);
Graph.dfs(adj, startNode, visited);
```

---

### 8. **DP.java** - Dynamic Programming Patterns
Comprehensive collection of DP algorithms:
- **fibonacci(n)**: Fibonacci with memoization
- **knapsack(weights, values, capacity)**: 0/1 Knapsack problem
- **lcs(s1, s2)**: Longest Common Subsequence
- **coinChange(coins, amount)**: Minimum coins needed
- **lis(arr)**: Longest Increasing Subsequence
- **editDistance(s1, s2)**: Levenshtein distance
- **climbStairs(n)**: Number of ways to reach nth stair
- **longestCommonSubstring(s1, s2)**: LCS length
- **unboundedKnapsack(...)**: Items can be reused
- **houseRobber(nums)**: Maximum money that can be robbed
- **maxProductSubarray(nums)**: Maximum product of subarray
- **canPartition(nums)**: Partition equal subset sum

```java
int coins = DP.coinChange(new int[]{1, 2, 5}, 5);  // returns 1
int distance = DP.editDistance("cat", "dog");       // returns 3
```

---

### 9. **StringAlgorithms.java** - String Manipulation
Advanced string algorithms and utilities.
- **kmpSearch(text, pattern)**: KMP pattern matching - O(n + m)
- **reverse(s)**: Reverse a string
- **isPalindrome(s)**: Check if palindrome
- **charFrequency(s)**: Get character frequency map
- **longestSubstringWithoutRepeat(s)**: Longest substring with unique chars
- **permute(s, l, r, result)**: Generate all permutations

```java
int idx = StringAlgorithms.kmpSearch("ababdabacdababcabab", "ababcabab");
boolean pal = StringAlgorithms.isPalindrome("racecar");
int len = StringAlgorithms.longestSubstringWithoutRepeat("abcabcbb");  // returns 3
```

---

### 10. **MathUtils.java** - Mathematical Algorithms
Efficient math operations for competitive programming.
- **isPrime(n)**: Prime checking - O(√n)
- **modPow(base, exp, mod)**: Modular exponentiation - O(log exp)
- **modInverse(a, m)**: Modular multiplicative inverse
- **factorialMod(n, mod)**: Calculate n! % mod
- **nCr(n, r, fact, mod)**: Combination with modulo
- **power(base, exp)**: Fast exponentiation
- **digitSum(n)**: Sum of digits
- **countDigits(n)**: Count number of digits

```java
long result = MathUtils.modPow(2, 1000000007, 1000000009);
boolean prime = MathUtils.isPrime(1000000007);
```

---

### 11. **ArrayUtils.java** - Array Utilities
Common array operations and algorithms.
- **buildPrefixSum(arr)**: Build prefix sum array - O(n)
- **rangeSum(prefix, l, r)**: Get range sum - O(1)
- **twoSum(arr, target)**: Find pair with given sum - O(n)
- **maxSubarraySum(arr)**: Kadane's algorithm - O(n)
- **rotateRight(arr, k)**: Rotate array right by k - O(n)
- **findPeak(arr)**: Find peak element - O(log n)
- **mergeSorted(arr1, arr2)**: Merge two sorted arrays - O(n + m)
- **majorityElement(arr)**: Find majority element (>n/2) - O(n)

```java
long[] prefix = ArrayUtils.buildPrefixSum(arr);
long sum = ArrayUtils.rangeSum(prefix, 2, 5);  // Sum from index 2 to 5 in O(1)
long maxSum = ArrayUtils.maxSubarraySum(arr);
```

---

### 12. **Main_Template.java** - FastIO Template
Ready-to-use template with FastIO for faster input/output operations.

```java
public class Main {
    static FastIO io = new FastIO();
    
    public static void main(String[] args) throws IOException {
        // Your code here
        int n = io.nextInt();
        long x = io.nextLong();
        io.println("Answer: " + x);
        io.close();
    }
}
```

---

## How to Use

### Method 1: Copy Individual Methods
1. Open the required snippet file
2. Copy the specific method you need
3. Paste it into your Main.java

### Method 2: Copy Entire Class
1. Open the required snippet file
2. Copy the entire class (before your Main class or in separate file)
3. Use the static methods directly

### Method 3: Use FastIO Template
1. Copy the Main_Template.java structure
2. Add your snippet classes inside
3. Use FastIO for faster I/O operations

---

## Quick Reference

| Problem Type | Snippet | Function |
|---|---|---|
| Prime Numbers | Sieve.java | sieve(n) |
| GCD/LCM | GCD_LCM.java | gcd(), lcm() |
| Search | BinarySearch.java | binarySearch*() |
| Graphs | Graph.java, UnionFind.java | bfs(), dfs(), union() |
| Range Queries | SegmentTree.java, ArrayUtils.java | query(), rangeSum() |
| Dynamic Programming | DP.java | Various DP patterns |
| String Problems | StringAlgorithms.java | kmpSearch(), isPalindrome() |
| Math Problems | MathUtils.java | modPow(), isPrime() |
| Array Problems | ArrayUtils.java | maxSubarraySum(), twoSum() |
| Sorting | Sorting.java | mergeSort(), quickSort() |

---

## Time Complexity Analysis

### Quick Lookup by Operation

| Operation | Time Complexity | Space | Notes |
|---|---|---|---|
| **Sieve of Eratosthenes** | O(n log log n) | O(n) | Fastest way to find all primes up to n |
| **Segmented Sieve** | O(√n + k log log √n) | O(√n + k) | k = range size; optimal for large ranges |
| **GCD (Euclidean)** | O(log min(a,b)) | O(1) | Very fast, even for huge numbers |
| **Binary Search** | O(log n) | O(1) | Requires sorted array |
| **Union-Find (DSU)** | O(α(n)) ≈ O(1) | O(n) | With path compression & union by rank |
| **Segment Tree Build** | O(n) | O(n) | Build once, then query/update in O(log n) |
| **Segment Tree Query** | O(log n) | - | Range sum query |
| **Segment Tree Update** | O(log n) | - | Point update |
| **Merge Sort** | O(n log n) | O(n) | Stable, consistent performance |
| **Quick Sort** | O(n log n) avg, O(n²) worst | O(log n) | Fast in practice, worst case rare |
| **BFS** | O(V + E) | O(V) | V=vertices, E=edges |
| **DFS** | O(V + E) | O(V) | Recursive depth = O(V) space |
| **Fibonacci (DP)** | O(n) | O(n) | Iterative; memoization prevents recalculation |
| **0/1 Knapsack** | O(n × capacity) | O(n × capacity) | Classic DP, polynomial time |
| **Unbounded Knapsack** | O(n × capacity) | O(capacity) | Space optimized 1D DP |
| **LCS (Longest Common Subsequence)** | O(m × n) | O(m × n) | m,n = string lengths |
| **Edit Distance** | O(m × n) | O(m × n) | Levenshtein distance |
| **LIS (Longest Increasing Subseq)** | O(n²) basic, O(n log n) optimized | O(n) | Binary search optimization available |
| **Coin Change** | O(amount × coins) | O(amount) | 1D DP array |
| **KMP String Matching** | O(n + m) | O(m) | n=text length, m=pattern length |
| **Palindrome Check** | O(n) | O(1) | Two-pointer approach |
| **Longest Substring (no repeat)** | O(n) | O(min(n, charset)) | Sliding window with hash map |
| **Prime Checking** | O(√n) | O(1) | Trial division up to √n |
| **Modular Exponentiation** | O(log exp) | O(1) | Fast power by squaring |
| **Prefix Sum Build** | O(n) | O(n) | Build once for O(1) range queries |
| **Range Sum Query** | O(1) | - | Using precomputed prefix sums |
| **Kadane's Algorithm** | O(n) | O(1) | Maximum subarray sum |
| **Majority Element** | O(n) | O(1) | Boyer-Moore voting algorithm |
| **Two Pointer (sorted)** | O(n) | O(1) | Find pair with target sum |

---

### Complexity Class Reference

```
Best case scenarios:
┌─────────────────────────┐
│ O(1)      - Constant    │ (fastest)
│ O(log n)  - Logarithmic │
│ O(n)      - Linear      │
│ O(n log n)- Linearithmic│
│ O(n²)     - Quadratic   │
│ O(2ⁿ)     - Exponential │ (slowest)
└─────────────────────────┘

General Rules:
- n = 10⁶  → O(n) and O(n log n) are fast, O(n²) is too slow
- n = 10⁴  → O(n²) is acceptable, O(n³) is too slow
- n = 500  → O(n³) is acceptable, O(n⁴) is too slow
```

---

### Choosing the Right Algorithm

**Finding Primes:**
- Single prime check: `O(√n)` with `isPrime()`
- All primes up to n: `O(n log log n)` with `sieve()`
- Primes in range [L, R]: `O(√R + (R-L) log log √R)` with `segmentedSieve()`

**Range Queries:**
- One-time query: `O(n)` with linear scan
- Multiple queries: `O(n log n)` with Segment Tree or `O(n)` with Prefix Sum (if only sums)
- Dynamic updates: `O(log n)` per operation with Segment Tree

**String Matching:**
- Single occurrence: `O(n + m)` with KMP
- Multiple searches: Preprocess with KMP, then `O(n + m)` each time

**Graph Problems:**
- Connected components: Use DSU `O(n × α(n))`
- Path finding: Use BFS/DFS `O(V + E)`

---

## File Organization

```
snippets/
├── README.md                    (This file)
├── Main_Template.java           (FastIO template)
├── Sieve.java                   (Prime numbers)
├── GCD_LCM.java                 (Number theory)
├── BinarySearch.java            (Searching)
├── UnionFind.java               (DSU)
├── SegmentTree.java             (Data structures)
├── Sorting.java                 (Sorting)
├── Graph.java                   (Graph algorithms)
├── DP.java                      (Dynamic programming)
├── StringAlgorithms.java        (String algorithms)
├── MathUtils.java               (Math operations)
└── ArrayUtils.java              (Array operations)
```

---

## Last Updated
October 31, 2025

Happy Coding! 🚀
