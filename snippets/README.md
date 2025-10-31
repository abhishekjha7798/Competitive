# Competitive Coding Snippets Library

## Overview
This directory contains **25+ reusable code snippets** for competitive programming covering all major algorithm categories. Simply copy the class or method you need and paste it into your Main.java file.

## Complete Snippet Index

### Core Algorithms (Original)

| # | Snippet | Algorithms | Time Complexity |
|---|---------|-----------|---|
| 1 | **Sieve.java** | Sieve of Eratosthenes, Segmented Sieve | O(n log log n), O(√n + k log log √n) |
| 2 | **GCD_LCM.java** | GCD (Euclidean), LCM | O(log min(a,b)) |
| 3 | **BinarySearch.java** | Binary Search (3 variants) | O(log n) |
| 4 | **UnionFind.java** | DSU with path compression & union by rank | O(α(n)) ≈ O(1) |
| 5 | **SegmentTree.java** | Range Sum Query, Point Update | O(log n) |
| 6 | **Sorting.java** | Merge Sort, Quick Sort | O(n log n) |
| 7 | **Graph.java** | BFS, DFS, Dijkstra, Bellman-Ford, Floyd-Warshall, Prim's, Kruskal's, Kosaraju (SCC), 2-SAT | O(V+E) to O(V³) |
| 8 | **DP.java** | 12+ DP patterns (Fibonacci, Knapsack, LCS, LIS, etc.) | O(n) to O(n²) |
| 9 | **StringAlgorithms.java** | KMP, Palindrome, LCS, Permutations | O(n+m) to O(n²) |
| 10 | **MathUtils.java** | Prime checking, Modular exponentiation, Modular inverse | O(√n) to O(log n) |
| 11 | **ArrayUtils.java** | Prefix Sum, Kadane's, Two Pointer, Majority Element | O(n) to O(n log n) |
| 12 | **Main_Template.java** | FastIO template for faster I/O | - |

### Advanced Algorithms (New!)

| # | Snippet | Algorithms | Time Complexity |
|---|---------|-----------|---|
| 13 | **LazySegmentTree.java** | Range Updates, Range Queries with Lazy Propagation | O(log n) |
| 14 | **Geometry.java** | Closest Pair, Convex Hull, Polygon Area, Point in Polygon, Line Intersection | O(n log n) to O(n²) |
| 15 | **GameTheory.java** | Nim, Sprague-Grundy, Fibonacci Nim, Wythoff's Game, Optimal Strategy | O(1) to O(n) |
| 16 | **MatrixAlgorithms.java** | Matrix Multiplication, Exponentiation, Chain Multiplication | O(n³) to O(n³ log n) |
| 17 | **Combinatorics.java** | nCr, nPr, Catalan, Stirling, Bell Numbers, Derangements | O(n) to O(n²) |
| 18 | **TreeAlgorithms.java** | LCA (Binary Lifting), Tree Diameter, Tree DP, Centroid, Path Sum | O(log n) to O(n) |
| 19 | **FenwickTree.java** | Fenwick Tree (BIT), 2D Fenwick Tree, Point Update, Range Sum | O(log n) |
| 20 | **Backtracking.java** | N-Queens, Sudoku, Permutations, Subsets, Rat in Maze, Word Search | O(n!) to O(2ⁿ) |

---

## Detailed Documentation

### 1. **Sieve.java** - Prime Number Generation
```java
boolean[] primes = Sieve.sieve(1000);           // All primes up to 1000
boolean[] rangePromes = Sieve.segmentedSieve(1000000000L, 1000001000L);  // Large range
```
- **Time**: O(n log log n), O(√n + k log log √n) for segmented
- **Space**: O(n), O(k) for segmented
- **Use Case**: Finding all primes in a range

---

### 2. **Graph.java** - Comprehensive Graph Algorithms
Contains **9 major graph algorithms**:

- **BFS/DFS**: Graph traversal - O(V + E)
- **Connected Components**: Count components - O(V + E)
- **Dijkstra**: Shortest path (non-negative) - O((V + E) log V)
- **Bellman-Ford**: Shortest path (negative allowed) - O(VE)
- **Floyd-Warshall**: All pairs shortest path - O(V³)
- **Prim's/Kruskal's**: Minimum spanning tree - O(E log V)
- **Kosaraju's**: Strongly connected components - O(V + E)
- **2-SAT**: Boolean satisfiability problem - O(V + E)

```java
List<List<Integer>> adj = new ArrayList<>();
Graph.bfs(adj, startNode);
Graph.dfs(adj, startNode, visited);
long[] distances = Graph.dijkstra(weightedAdj, source);
```

---

### 3. **Geometry.java** - Geometric Algorithms
```java
Geometry.Point[] points = new Geometry.Point[n];
double closestDist = Geometry.closestPair(points);          // O(n log n)
List<Geometry.Point> hull = Geometry.convexHull(points);    // O(n log n)
double area = Geometry.polygonArea(polygon);                // O(n)
boolean inside = Geometry.pointInPolygon(point, polygon);   // O(n)
boolean intersects = Geometry.segmentsIntersect(p1, p2, p3, p4); // O(1)
```

**Includes**: Closest pair, Convex hull (Graham scan), Polygon area (Shoelace), Point in polygon, Line intersection

---

### 4. **GameTheory.java** - Game Theory Algorithms
```java
int winner = GameTheory.nimWinner(piles);                   // 0 = P2 wins, 1 = P1 wins
int[] move = GameTheory.nimWinningMove(piles);              // Get winning move
boolean canWin = GameTheory.canWin(n, memo);                // DP-based game state
int grundy = GameTheory.computeGrundy(n, memo, moves);      // Sprague-Grundy
```

**Includes**: Nim game, Grundy numbers, Fibonacci Nim, Wythoff's game, Optimal game strategy, Box stacking

---

### 5. **MatrixAlgorithms.java** - Matrix Operations
```java
long[][] C = MatrixAlgorithms.matrixMultiply(A, B);         // A × B
long[][] A_n = MatrixAlgorithms.matrixPower(A, n);          // A^n
long[][] A_n_mod = MatrixAlgorithms.matrixPowerMod(A, n, MOD); // A^n % MOD
long[][] result = MatrixAlgorithms.chainMatrixMultiply(matrices); // A1×A2×...×An
```

**Includes**: Matrix multiplication, Exponentiation, Chain multiplication, Determinant, Addition, Subtraction, Transpose

---

### 6. **Combinatorics.java** - Combinatorial Algorithms
```java
long c = Combinatorics.nCr(n, r);                           // Combination C(n,r)
long p = Combinatorics.nPr(n, r);                           // Permutation P(n,r)
long cat = Combinatorics.catalan(n);                        // nth Catalan number
long[] bells = Combinatorics.bellNumbers(n);                // Bell numbers
long derang = Combinatorics.derangements(n);                // Derangements
```

**Includes**: nCr, nPr, Catalan numbers, Stirling numbers, Bell numbers, Derangements, Partitions, Pascal's triangle

---

### 7. **TreeAlgorithms.java** - Tree Algorithms
```java
LCA lca = new TreeAlgorithms.LCA(adj, root, n);
int ancestor = lca.getLCA(u, v);                            // Lowest Common Ancestor - O(log n)
int diameter = TreeAlgorithms.treeDiameter(adj, n);         // Tree diameter
long sum = TreeAlgorithms.subtreeSum(adj, node, parent, values); // Subtree sum
```

**Includes**: Tree diameter, LCA (Binary lifting), Tree DP, Centroid decomposition, Path queries, Distance calculations

---

### 8. **FenwickTree.java** - Binary Indexed Tree
```java
FenwickTree ft = new FenwickTree(n);
ft.update(idx, delta);                                       // Add delta to index
int sum = ft.query(idx);                                     // Prefix sum [0, idx] - O(log n)
int rangeSum = ft.rangeSum(l, r);                           // Sum [l, r] - O(log n)
```

**Includes**: 1D Fenwick Tree, 2D Fenwick Tree, Long version, Modular version

---

### 9. **LazySegmentTree.java** - Lazy Propagation
```java
LazySegmentTree lst = new LazySegmentTree(arr);
lst.updateRange(l, r, val);                                  // Add val to range [l, r] - O(log n)
long sum = lst.queryRange(l, r);                            // Query range sum [l, r] - O(log n)
```

**Optimized for**: Multiple range updates and range queries

---

### 10. **Backtracking.java** - Backtracking Algorithms
```java
List<List<String>> solutions = Backtracking.solveNQueens(n); // N-Queens
boolean solved = Backtracking.solveSudoku(board);            // Sudoku solver
List<List<Integer>> perms = Backtracking.generatePermutations(nums); // All permutations
List<List<Integer>> subsets = Backtracking.generateSubsets(nums);    // All subsets
```

**Includes**: N-Queens, Sudoku solver, Permutations, Subsets, Combination sum, Rat in maze, Word search, Partition palindromes

---

## How to Use

### Method 1: Copy Individual Methods
1. Open the required snippet file
2. Copy the specific method/class you need
3. Paste it into your Main.java

### Method 2: Copy Entire Class
1. Open the required snippet file
2. Copy the entire class (place before your Main class)
3. Use the static methods directly

### Method 3: Use FastIO Template
1. Copy the Main_Template.java structure
2. Add your snippet classes inside
3. Use FastIO for faster I/O

---

## Quick Selection Guide

**Problem Type → Use This Snippet**

| Problem | Snippet | Method |
|---------|---------|--------|
| Prime numbers | Sieve.java | sieve() or segmentedSieve() |
| GCD/LCM | GCD_LCM.java | gcd(), lcm() |
| Binary search | BinarySearch.java | binarySearch(), binarySearchLeft(), binarySearchRight() |
| Graph connectivity | Graph.java | bfs(), dfs(), kosarajuSCC() |
| Shortest path | Graph.java | dijkstra(), bellmanFord(), floydWarshall() |
| MST | Graph.java | prims(), kruskals() |
| Range queries (static) | SegmentTree.java, FenwickTree.java | query(), rangeSum() |
| Range updates | LazySegmentTree.java | updateRange(), queryRange() |
| Closest pair | Geometry.java | closestPair() |
| Convex hull | Geometry.java | convexHull() |
| Game theory | GameTheory.java | nimWinner(), computeGrundy() |
| Matrix problems | MatrixAlgorithms.java | matrixMultiply(), matrixPower() |
| Combinatorics | Combinatorics.java | nCr(), catalan(), bellNumbers() |
| Tree problems | TreeAlgorithms.java | getLCA(), treeDiameter() |
| N-Queens, Sudoku | Backtracking.java | solveNQueens(), solveSudoku() |
| Dynamic programming | DP.java | Various DP patterns |
| String matching | StringAlgorithms.java | kmpSearch() |

---

## Time Complexity Cheat Sheet

| Algorithm | Time | Space | When to Use |
|-----------|------|-------|-------------|
| Sieve | O(n log log n) | O(n) | Find all primes up to n |
| Binary Search | O(log n) | O(1) | Sorted array, find element |
| BFS/DFS | O(V+E) | O(V) | Graph traversal, connectivity |
| Dijkstra | O((V+E) log V) | O(V) | Non-negative shortest path |
| Floyd-Warshall | O(V³) | O(V²) | All pairs shortest path (small n) |
| Prim's/Kruskal's | O(E log V) | O(V) | Minimum spanning tree |
| Segment Tree | O(log n) | O(n) | Range queries/updates |
| Fenwick Tree | O(log n) | O(n) | Simpler alternative to Seg Tree |
| Merge Sort | O(n log n) | O(n) | Stable sorting |
| Quick Sort | O(n log n) avg | O(log n) | Fast in practice |
| Convex Hull | O(n log n) | O(n) | Geometric hull |
| Matrix Mult | O(n³) | O(n²) | Matrix operations |
| Catalan | O(n) | O(n) | Combinatorial sequences |
| N-Queens | O(n!) | O(n) | Permutation problems |

---

## Complexity Classes

```
Time Complexity Hierarchy (fastest → slowest):
O(1)      - Constant time
O(log n)  - Logarithmic (Binary search, Segment tree)
O(√n)     - Square root (Prime checking)
O(n)      - Linear (Array scan, BFS/DFS)
O(n log n)- Linearithmic (Sorting, merge, quicksort)
O(n²)     - Quadratic (Nested loops, Floyd-Warshall small)
O(n³)     - Cubic (Matrix mult, Floyd-Warshall)
O(2ⁿ)     - Exponential (Backtracking, subsets)
O(n!)     - Factorial (Permutations)

Rule of Thumb:
n = 10⁶  → O(n), O(n log n) ✓ | O(n²) ✗
n = 10⁴  → O(n²) ✓ | O(n³) ✗
n = 500  → O(n³) ✓ | O(n⁴) ✗
n = 20   → O(2ⁿ) ✓ | O(3ⁿ) ✗
```

---

## File Organization

```
snippets/
├── README.md                    ← You are here
├── SEGMENTED_SIEVE_EXPLANATION.txt
├── Main_Template.java           
├── Sieve.java                   
├── GCD_LCM.java                 
├── BinarySearch.java            
├── UnionFind.java               
├── SegmentTree.java             
├── LazySegmentTree.java         ← NEW
├── Sorting.java                 
├── Graph.java                   ← Enhanced with 9 algorithms
├── Geometry.java                ← NEW
├── GameTheory.java              ← NEW
├── MatrixAlgorithms.java        ← NEW
├── Combinatorics.java           ← NEW
├── TreeAlgorithms.java          ← NEW
├── FenwickTree.java             ← NEW
├── Backtracking.java            ← NEW
├── DP.java                      
├── StringAlgorithms.java        
├── MathUtils.java               
└── ArrayUtils.java              
```

---

## Usage Tips

1. **Always test with sample inputs** before submitting
2. **Handle edge cases**: empty inputs, single elements, negative numbers
3. **Use FastIO** for problems with large input/output
4. **Remember modulo** operations for large number problems
5. **Initialize arrays properly** to avoid null pointer exceptions
6. **Use 1-indexed arrays** for easier implementation in some algorithms (especially trees)
7. **Copy and adapt** - modify snippets as needed for your specific problem

---

## Recent Updates

✅ Added **8 new advanced algorithm files**
✅ Filled Graph.java with **9 comprehensive graph algorithms**
✅ Added **time complexity analysis** section
✅ Created **quick selection guide** for algorithm choices

---

## Last Updated
November 1, 2025

**Total Snippets: 20 files | 50+ algorithms | 5000+ lines of production-ready code**

Happy Coding! 🚀

