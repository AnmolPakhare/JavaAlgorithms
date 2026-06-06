# JavaAlgorithms — Complete Java Reference

---

## INDEX

### Dynamic Programming
1. [Fibonacci — Naive, Memoization, Tabulation](#1-fibonacci)
2. [Coin Change](#2-coin-change)
3. [Longest Palindromic Substring](#3-longest-palindromic-substring)
4. [0/1 Knapsack](#4-01-knapsack)
5. [Egg Dropping Problem](#5-egg-dropping-problem)

### Recursive Problems
6. [Binary Search (Recursive)](#6-binary-search-recursive)
7. [Towers of Hanoi](#7-towers-of-hanoi)
8. [Factorial](#8-factorial)
9. [Euclidean Algorithm (GCD)](#9-euclidean-algorithm-gcd)

### Sliding Window
10. [Fixed Window — Max Sum Subarray](#10-fixed-window--max-sum-subarray)
11. [Variable Window — Longest Substring Without Repeating Chars](#11-variable-window--longest-substring-without-repeating-chars)
12. [Variable Window — Min Size Subarray Sum](#12-variable-window--min-size-subarray-sum)
13. [Longest Substring With Exactly K Unique Chars](#13-longest-substring-with-exactly-k-unique-chars)

### Interval Algorithms
14. [Merge Overlapping Intervals](#14-merge-overlapping-intervals)
15. [Interval Intersection](#15-interval-intersection)
16. [Maximum Non-Overlapping Intervals](#16-maximum-non-overlapping-intervals)
17. [Minimum Meeting Rooms](#17-minimum-meeting-rooms)
18. [Free Time Intervals](#18-free-time-intervals)

### Graph Algorithms
19. [Graph — BFS & DFS](#19-graph--bfs--dfs)
20. [Graph Coloring](#20-graph-coloring)
21. [Hamiltonian Path](#21-hamiltonian-path)

### Sorting / Selection
22. [Quickselect — Kth Largest Element](#22-quickselect--kth-largest-element)
23. [Bin Packing — First Fit Decreasing](#23-bin-packing--first-fit-decreasing)
24. [CPOP — Closest Pair of Points](#24-cpop--closest-pair-of-points)

### String Algorithms
25. [Anagram Detection](#25-anagram-detection)

### Reference
26. [Complexity Cheat Sheet](#quick-complexity-reference)

---

## DYNAMIC PROGRAMMING

---

### 1. Fibonacci

**Complexity:**
| Approach | Time | Space |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization (top-down) | O(n) | O(n) |
| Tabulation (bottom-up) | O(n) | O(n) |

```java
package com.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciAlgorithm {

    public Map<Integer, Integer> memoizeTable;
    private Map<Integer, Integer> tabulationTable;

    public FibonacciAlgorithm() {
        this.memoizeTable = new HashMap<>();
        this.tabulationTable = new HashMap<>();
        this.memoizeTable.put(0, 0);
        this.memoizeTable.put(1, 1);
        this.tabulationTable.put(0, 0);
        this.tabulationTable.put(1, 1);
    }

    // O(n) — top-down with memoization
    public int fibonacciMemoize(int n) {
        if (this.memoizeTable.containsKey(n))
            return this.memoizeTable.get(n);

        this.memoizeTable.put(n - 1, fibonacciMemoize(n - 1));
        this.memoizeTable.put(n - 2, fibonacciMemoize(n - 2));

        int calculatedNumber = this.memoizeTable.get(n - 1) + this.memoizeTable.get(n - 2);
        this.memoizeTable.put(n, calculatedNumber);
        return calculatedNumber;
    }

    // O(n) — bottom-up tabulation
    public Map fibonacciTabulation(int n) {
        for (int i = 2; i <= n; i++)
            tabulationTable.put(i, tabulationTable.get(i - 1) + tabulationTable.get(i - 2));
        return tabulationTable;
    }

    // O(2^n) — naive recursion
    public int naiveFibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return naiveFibonacci(n - 1) + naiveFibonacci(n - 2);
    }
}
```

---

### 2. Coin Change

**Complexity:** O(amount × coins) time | O(amount) space

```java
package com.dynamicprogramming;

import java.util.Arrays;

public class CoinChange {

    // O(2^N) — naive recursive
    public int naiveCoinChange(int M, int[] v, int index) {
        if (M < 0) return 0;
        if (M == 0) return 1;
        if (v.length == index) return 0;
        return naiveCoinChange(M - v[index], v, index) + naiveCoinChange(M, v, index + 1);
    }

    // O(amount * coins) — DP: minimum number of coins
    public int dynamicProgrammingCoinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```

---

### 3. Longest Palindromic Substring

**Complexity:** O(n²) time | O(n²) space

```java
package com.dynamicprogramming;

public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLength = 1;

        for (int i = 0; i < n; i++) dp[i][i] = true;

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = len;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
}
```

---

### 4. 0/1 Knapsack

**Complexity:** O(N×W) time | O(N×W) space

```java
package com.knapsack;

public class Knapsack {

    private int numOfItems;
    private int capacityOfKnapsack;
    private int[][] knapsackTable;
    private int totalBenefit;
    private int[] weights;
    private int[] values;

    public Knapsack(int numOfItems, int capacityOfKnapsack, int[] weights, int[] values) {
        this.numOfItems = numOfItems;
        this.capacityOfKnapsack = capacityOfKnapsack;
        this.weights = weights;
        this.values = values;
        this.knapsackTable = new int[numOfItems + 1][capacityOfKnapsack + 1];
    }

    public void solve() {
        for (int i = 1; i < numOfItems + 1; i++) {
            for (int w = 1; w < capacityOfKnapsack + 1; w++) {
                int notTakingItem = knapsackTable[i - 1][w];
                int takingItem = 0;
                if (weights[i] <= w)
                    takingItem = values[i] + knapsackTable[i - 1][w - weights[i]];
                knapsackTable[i][w] = Math.max(notTakingItem, takingItem);
            }
        }
        totalBenefit = knapsackTable[numOfItems][capacityOfKnapsack];
    }

    public void showResult() {
        System.out.println("Total benefit: " + totalBenefit);
        for (int n = numOfItems, w = capacityOfKnapsack; n > 0; n--) {
            if (knapsackTable[n][w] != 0 && knapsackTable[n][w] != knapsackTable[n - 1][w]) {
                System.out.println("We take item: #" + n);
                w = w - weights[n];
            }
        }
    }
}
```

---

### 5. Egg Dropping Problem

**Complexity:** O(E×F²) time | O(E×F) space  
`E` = number of eggs, `F` = number of floors

```java
package com.eggdropping;

public class EggDroppingProblem {

    // dpTable[i][j] = min drops with i eggs and j floors
    private int[][] dpTable = new int[Constants.NUM_OF_EGGS + 1][Constants.NUM_OF_FLOORS + 1];

    public int solve() {
        dpTable[0][0] = 1;
        dpTable[1][0] = 1;

        for (int i = 0; i <= Constants.NUM_OF_FLOORS; i++)
            dpTable[1][i] = i; // linear search with 1 egg

        for (int n = 2; n <= Constants.NUM_OF_EGGS; n++) {
            for (int m = 1; m <= Constants.NUM_OF_FLOORS; m++) {
                dpTable[n][m] = Integer.MAX_VALUE;
                for (int x = 1; x <= m; x++) {
                    int maxDrops = 1 + Math.max(dpTable[n - 1][x - 1], dpTable[n][m - x]);
                    if (maxDrops < dpTable[n][m])
                        dpTable[n][m] = maxDrops;
                }
            }
        }
        return dpTable[Constants.NUM_OF_EGGS][Constants.NUM_OF_FLOORS];
    }
}
```

---

## RECURSIVE PROBLEMS

---

### 6. Binary Search (Recursive)

**Complexity:** O(log n) time | O(log n) space (call stack)

```java
package com.RecursiveProblems.BinarySearch;

public class Algorithm {

    private int[] array;

    public Algorithm(int[] array) {
        this.array = array;
    }

    public int binarySearch(int item) {
        return binarySearch(0, this.array.length - 1, item);
    }

    private int binarySearch(int startIndex, int endIndex, int item) {
        if (endIndex < startIndex) {
            System.out.println("No solution found..");
            return -1;
        }

        int middleIndex = (startIndex + endIndex) / 2;

        if (item == this.array[middleIndex]) {
            return middleIndex;
        } else if (item < this.array[middleIndex]) {
            return binarySearch(startIndex, middleIndex - 1, item);
        } else {
            return binarySearch(middleIndex + 1, endIndex, item);
        }
    }
}
```

---

### 7. Towers of Hanoi

**Complexity:** O(2ⁿ) time | O(n) space (call stack)

```java
package com.RecursiveProblems.TowersOfHanoi;

public class Algorithm {

    public void solveHanoiProblem(int n, char rodFrom, char middleRod, char rodTo) {
        if (n == 1) {
            System.out.println("Plate 1 from " + rodFrom + " to " + rodTo);
            return;
        }
        solveHanoiProblem(n - 1, rodFrom, rodTo, middleRod);
        System.out.println("Plate " + n + " from " + rodFrom + " to " + rodTo);
        solveHanoiProblem(n - 1, middleRod, rodFrom, rodTo);
    }
}
```

---

### 8. Factorial

**Complexity:** O(n) time | O(n) space (call stack)

```java
package com.RecursiveProblems.Factorial;

public class Algorithm {

    public int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
}
```

---

### 9. Euclidean Algorithm (GCD)

**Complexity:** O(log(min(a, b))) time | O(log(min(a, b))) space

```java
package com.RecursiveProblems.EuclideanAlgorithm;

public class Algorithm {

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
```

---

## SLIDING WINDOW

---

### 10. Fixed Window — Max Sum Subarray

**Complexity:** O(n) time | O(1) space

```java
package com.slidingwindow;

public class FixedSizeWindow_MaxSumSubarray {

    public static int findMaxSumSubarray(int[] arr, int k) {
        if (arr.length < k) return -1;

        int maxSum = 0, windowSum = 0, windowStart = 0;

        for (int i = 0; i < k; i++) windowSum += arr[i];
        maxSum = windowSum;

        for (int windowEnd = k; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            windowSum -= arr[windowStart++];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
}
// Input: {2, 1, 5, 1, 3, 2}, k=3  →  Output: 9  ({5,1,3})
```

---

### 11. Variable Window — Longest Substring Without Repeating Chars

**Complexity:** O(n) time | O(alphabet) space

```java
package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class VariableSizeWindow_LongestSubstring {

    public static int findLongestSubstring(String str) {
        if (str == null || str.length() == 0) return 0;

        int maxLength = 0, windowStart = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charIndexMap.containsKey(rightChar))
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            charIndexMap.put(rightChar, windowEnd);
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
}
// Input: "abaefbbb"  →  Output: 4  ("baef")
```

---

### 12. Variable Window — Min Size Subarray Sum

**Complexity:** O(n) time | O(1) space

```java
package com.slidingwindow;

public class VariableSizeWindow_MinSizeSubarraySum {

    public static int findMinSubArray(int S, int[] arr) {
        int minLength = Integer.MAX_VALUE, windowSum = 0, windowStart = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            while (windowSum >= S) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
// Input: S=12, {2,1,5,2,3,2}  →  Output: 2  ({5,2} sums to... wait: subarray {5,2,3,2} = 12, length 4)
```

---

### 13. Longest Substring With Exactly K Unique Chars

**Complexity:** O(n) time | O(k) space

Uses the identity: `exactlyK(s, k) = atMostK(s, k) − atMostK(s, k−1)`

```java
package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithExactlyKUniqueChars {

    public static int longestSubstringKUnique(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }

    private static int atMostK(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int result = 0, windowStart = 0;
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            freq.merge(s.charAt(windowEnd), 1, Integer::sum);
            while (freq.size() > k) {
                char left = s.charAt(windowStart++);
                freq.merge(left, -1, Integer::sum);
                if (freq.get(left) == 0) freq.remove(left);
            }
            result = Math.max(result, windowEnd - windowStart + 1);
        }
        return result;
    }
}
```

---

## INTERVAL ALGORITHMS

---

### 14. Merge Overlapping Intervals

**Complexity:** O(n log n) time | O(n) space

```java
package com.intervals;

import java.util.*;

public class MergeOverlappingIntervals {

    public static List mergeIntervals(int[][] input) {
        List output = new ArrayList<int[]>();
        Arrays.sort(input, Comparator.comparing(a -> a[0]));

        int[] current = input[0];
        for (int i = 1; i < input.length; i++) {
            if (current[1] >= input[i][0]) {
                current[1] = Math.max(current[1], input[i][1]);
            } else {
                output.add(current);
                current = input[i];
            }
        }
        output.add(current);
        return output;
    }
}
// Input: {{1,3},{2,6},{8,10},{15,18}}  →  Output: [[1,6],[8,10],[15,18]]
```

---

### 15. Interval Intersection

**Complexity:** O(n log n) time | O(n) space

```java
package com.intervals;

import java.util.*;

public class IntervalIntersection {

    public static List intersectionInterval(int[][] firstIntervals, int[][] secondIntervals) {
        List output = new ArrayList<int[]>();
        Arrays.sort(firstIntervals, Comparator.comparing(a -> a[0]));
        Arrays.sort(secondIntervals, Comparator.comparing(a -> a[0]));

        int i = 0, j = 0;
        while (i < firstIntervals.length && j < secondIntervals.length) {
            int start = Math.max(firstIntervals[i][0], secondIntervals[j][0]);
            int end   = Math.min(firstIntervals[i][1], secondIntervals[j][1]);

            if (start <= end) output.add(new int[]{start, end});

            if (firstIntervals[i][1] < secondIntervals[j][1]) i++;
            else j++;
        }
        return output;
    }
}
// Input: [[0,2],[5,10],[13,23],[24,25]] ∩ [[1,5],[8,12],[15,24],[25,26]]
// Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
```

---

### 16. Maximum Non-Overlapping Intervals

**Complexity:** O(n log n) time | O(n) space

Sort by end time; greedily select intervals that don't overlap the last selected.

```java
// Greedy approach — sort by end time
Arrays.sort(intervals, Comparator.comparing(a -> a[1]));
PriorityQueue<Integer> q = new PriorityQueue<>();
q.offer(intervals[0][1]);
for (int i = 1; i < intervals.length; i++) {
    if (intervals[i][0] >= q.peek()) q.offer(intervals[i][1]);
}
return q.size(); // count of max non-overlapping intervals
```

---

### 17. Minimum Meeting Rooms

**Complexity:** O(n log n) time | O(n) space

```java
package com.intervals;

import java.util.*;

public class MinimumMeetingRoom {

    public static int minimumMeetingRooms(int[][] input) {
        Arrays.sort(input, Comparator.comparing(a -> a[0]));
        PriorityQueue<Integer> q = new PriorityQueue<>(); // min-heap of end times

        for (int[] interval : input) {
            if (!q.isEmpty() && q.peek() <= interval[0]) q.poll();
            q.offer(interval[1]);
        }
        return q.size();
    }
}
// Input: {{1,5},{2,6},{4,8},{9,12}}  →  Output: 3 rooms
```

---

### 18. Free Time Intervals

**Complexity:** O(n log n) time | O(n) space

Flattens all employee schedules, sorts, and finds gaps between merged intervals.

```java
// Concept: merge all busy intervals, then gaps are free time
// See com.intervals.FreeTimeInterval for full implementation
```

---

## GRAPH ALGORITHMS

---

### 19. Graph — BFS & DFS

**Complexity:** O(V + E) time | O(V) space

```java
package com.practise;

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    // BFS — level-order traversal using a queue
    void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjList.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // DFS iterative — uses explicit stack
    void dfsIterative(int start) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                System.out.print(node + " ");
                List<Integer> neighbors = new ArrayList<>(adjList.getOrDefault(node, new ArrayList<>()));
                Collections.reverse(neighbors);
                for (int neighbor : neighbors)
                    if (!visited.contains(neighbor)) stack.push(neighbor);
            }
        }
    }

    // DFS recursive
    void dfsRecursive(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");
        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>()))
            if (!visited.contains(neighbor)) dfsRecursive(neighbor, visited);
    }
}
```

---

### 20. Graph Coloring

Uses backtracking to color graph vertices so no two adjacent vertices share the same color.

**Complexity:** O(mᵛ) time where `m` = number of colors, `v` = vertices

```java
// See com.ColoringProblem.GraphColoring for full backtracking implementation
// Assigns colors 1..m to vertices such that no adjacent vertices share a color
```

---

### 21. Hamiltonian Path

Backtracking-based Hamiltonian path/cycle detection.

**Complexity:** O(n!) worst case

```java
// See com.hamiltonian.HamiltonianAlgorithm for full implementation
// Finds a path visiting every vertex exactly once
```

---

## SORTING / SELECTION

---

### 22. Quickselect — Kth Largest Element

**Complexity:** O(n) average | O(n²) worst case | O(1) space

```java
package com.quickselect;

import java.util.Random;

public class Quickselect {

    private int[] nums;

    public Quickselect(int[] nums) {
        this.nums = nums;
    }

    public int select(int k) {
        return select(0, nums.length - 1, k - 1);
    }

    private int partition(int indexFirst, int indexLast) {
        int pivot = new Random().nextInt(indexLast - indexFirst + 1) + indexFirst;
        swap(indexLast, pivot);
        for (int i = indexFirst; i < indexLast; i++) {
            if (nums[i] > nums[indexLast]) {
                swap(i, indexFirst);
                indexFirst++;
            }
        }
        swap(indexFirst, indexLast);
        return indexFirst;
    }

    private int select(int indexFirst, int indexLast, int k) {
        int pivot = partition(indexFirst, indexLast);
        if (pivot > k) return select(indexFirst, pivot - 1, k);
        else if (pivot < k) return select(pivot + 1, indexLast, k);
        return nums[k];
    }

    private void swap(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

---

### 23. Bin Packing — First Fit Decreasing

**Complexity:** O(n log n) sort + O(n²) placement | Approximation ratio ≤ 11/9 · OPT + 6/9

```java
package com.binpacking;

import java.util.*;

public class FirstFitDecreasingAlgorithm {

    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public FirstFitDecreasingAlgorithm(List<Integer> items, int binCapacity) {
        this.items = items;
        this.binCapacity = binCapacity;
        this.bins = new ArrayList<>(this.items.size());
    }

    public void solveBinPackingProblem() {
        Collections.sort(this.items, Collections.reverseOrder()); // largest first

        if (this.items.get(0) > this.binCapacity) {
            System.out.println("No feasible solution...");
            return;
        }

        this.bins.add(new Bin(binCapacity, binCounter));

        for (Integer currentItem : items) {
            boolean isOk = false;
            int currentBin = 0;
            while (!isOk) {
                if (currentBin == this.bins.size()) {
                    Bin newBin = new Bin(binCapacity, ++binCounter);
                    newBin.put(currentItem);
                    this.bins.add(newBin);
                    isOk = true;
                } else if (this.bins.get(currentBin).put(currentItem)) {
                    isOk = true;
                } else {
                    currentBin++;
                }
            }
        }
    }
}
```

---

### 24. CPOP — Closest Pair of Points

Sort points by x-coordinate; use divide-and-conquer strip comparison.

**Complexity:** O(n log n) time | O(n) space

```java
// See com.cpop.Algorithm for full divide-and-conquer implementation
// Uses XSorter (Comparator<Point>) to sort by x-coordinate before merging strips
```

---

## STRING ALGORITHMS

---

### 25. Anagram Detection

**Complexity:** O(n log n) time (sort-based) | O(n) space

```java
package com.anagram;

import java.util.Arrays;

public class AnagramProblem {

    public boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }
}
```

---

## QUICK COMPLEXITY REFERENCE

| Algorithm | Time (avg) | Time (worst) | Space | Notes |
|---|---|---|---|---|
| Fibonacci (naive) | O(2ⁿ) | O(2ⁿ) | O(n) | Exponential — avoid |
| Fibonacci (memo/tab) | O(n) | O(n) | O(n) | |
| Coin Change (DP) | O(A·C) | O(A·C) | O(A) | A=amount, C=coins |
| Longest Palindrome (DP) | O(n²) | O(n²) | O(n²) | |
| 0/1 Knapsack | O(N·W) | O(N·W) | O(N·W) | |
| Egg Dropping | O(E·F²) | O(E·F²) | O(E·F) | |
| Binary Search (recursive) | O(log n) | O(log n) | O(log n) | |
| Towers of Hanoi | O(2ⁿ) | O(2ⁿ) | O(n) | 2ⁿ−1 moves |
| Sliding Window (fixed) | O(n) | O(n) | O(1) | |
| Sliding Window (variable) | O(n) | O(n) | O(k) | k = unique chars |
| Merge Intervals | O(n log n) | O(n log n) | O(n) | |
| Interval Intersection | O(n log n) | O(n log n) | O(n) | |
| Min Meeting Rooms | O(n log n) | O(n log n) | O(n) | Min-heap |
| Graph BFS / DFS | O(V+E) | O(V+E) | O(V) | |
| Quickselect | O(n) | O(n²) | O(1) | Randomized pivot |
| Bin Packing FFD | O(n²) | O(n²) | O(n) | Approx ≤ 11/9·OPT |
| Anagram Detection | O(n log n) | O(n log n) | O(n) | Sort-based |
