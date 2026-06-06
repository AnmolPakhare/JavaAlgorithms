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

### Practice Problems
26. [Aggressive Cows — Binary Search on Answer](#26-aggressive-cows--binary-search-on-answer)
27. [Array Reverse — Two Pointers](#27-array-reverse--two-pointers)
28. [Trapping Rain Water — Two Pointer](#28-trapping-rain-water--two-pointer)
29. [Trapping Rain Water — Prefix/Suffix Arrays](#29-trapping-rain-water--prefixsuffix-arrays)
30. [Rotting Oranges — Multi-source BFS](#30-rotting-oranges--multi-source-bfs)
31. [Knight Moves — BFS Shortest Path](#31-knight-moves--bfs-shortest-path)
32. [Max Stack — O(1) getMax](#32-max-stack--o1-getmax)
33. [Calendar Booking — Conflict Detection](#33-calendar-booking--conflict-detection)
34. [Group Anagrams](#34-group-anagrams)
35. [Stock Price — Min/Max Heap](#35-stock-price--minmax-heap)
36. [Stock Data Structure — TreeMap](#36-stock-data-structure--treemap)
37. [Large File Sort — External Sort (Simple)](#37-large-file-sort--external-sort-simple)
38. [Large File Sorter — External Sort (Parallel)](#38-large-file-sorter--external-sort-parallel)
39. [Monster Game — Simulation](#39-monster-game--simulation)
40. [Vendor Placement — Binary Search on Answer](#40-vendor-placement--binary-search-on-answer)
41. [Email Regex Validator](#41-email-regex-validator)

### Reference
42. [Complexity Cheat Sheet](#quick-complexity-reference)

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

## PRACTICE PROBLEMS

---

### 26. Aggressive Cows — Binary Search on Answer

**Problem:** Given N stalls and C cows, place cows so the minimum distance between any two is maximized.  
**Complexity:** O(n log n) time | O(1) space

```java
package com.practise;

import java.util.*;

public class AggressiveCow {

    public static boolean canPlaceCows(int[] stalls, int cows, int minDist) {
        int count = 1;
        int lastPlaced = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPlaced >= minDist) {
                count++;
                lastPlaced = stalls[i];
                if (count == cows) return true;
            }
        }
        return false;
    }

    public static int aggressiveCows(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0], bestDist = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlaceCows(stalls, cows, mid)) {
                bestDist = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return bestDist;
    }
}
// Input: stalls={1,2,8,4,9}, cows=3  →  Output: 3
```

---

### 27. Array Reverse — Two Pointers

**Complexity:** O(n) time | O(1) space

```java
package com.practise;

public class ArrayReverse {

    public int[] solve(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
        return nums;
    }
}
// Input: {3,6,5,2,7,8}  →  Output: {8,7,2,5,6,3}
```

---

### 28. Trapping Rain Water — Two Pointer

**Complexity:** O(n) time | O(1) space

```java
package com.practise;

public class TrappingRainWater {

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) leftMax = height[left];
                else water += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax) rightMax = height[right];
                else water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}
// Input: {0,1,0,2,1,0,1,3,2,1,2,1}  →  Output: 6
```

---

### 29. Trapping Rain Water — Prefix/Suffix Arrays

**Complexity:** O(n) time | O(n) space

```java
package com.practise;

public class TrappingRainWater2 {

    public static int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n], rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++)
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);

        int water = 0;
        for (int i = 0; i < n; i++)
            water += Math.min(leftMax[i], rightMax[i]) - height[i];

        return water;
    }
}
// Input: {0,1,0,2,1,0,1,3,2,1,2,1}  →  Output: 6
```

---

### 30. Rotting Oranges — Multi-source BFS

**Problem:** Grid with fresh (1) and rotten (2) oranges. Each minute rotten spreads to adjacent fresh. Return minutes until all rot, or -1.  
**Complexity:** O(rows × cols) time | O(rows × cols) space

```java
package com.practise;

import java.util.*;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
                else if (grid[i][j] == 1) fresh++;
            }

        if (fresh == 0) return 0;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int minutes = 0;

        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            minutes++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : directions) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                        fresh--;
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }
}
// Input: {{2,1,1},{1,1,0},{0,1,1}}  →  Output: 4
```

---

### 31. Knight Moves — BFS Shortest Path

**Problem:** Find minimum moves for a knight to travel from (sx,sy) to (tx,ty) on an N×N board.  
**Complexity:** O(N²) time | O(N²) space

```java
package com.practise;

import java.util.*;

class KnightMoves {

    private static final int[][] MOVES = {
        {-2,-1},{-2,1},{2,-1},{2,1},{-1,-2},{-1,2},{1,-2},{1,2}
    };

    public static int minKnightMoves(int N, int sx, int sy, int tx, int ty) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tx && cur[1] == ty) return cur[2];

            for (int[] move : MOVES) {
                int nx = cur[0] + move[0], ny = cur[1] + move[1];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    queue.add(new int[]{nx, ny, cur[2] + 1});
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }
}
// 8×8 board, (0,0) → (7,7)  →  Output: 6 moves
```

---

### 32. Max Stack — O(1) getMax

**Problem:** Stack that supports push, pop, peekMax (O(1)), and popMax.  
**Complexity:** push/pop/peekMax O(1) | popMax O(n)

```java
package com.practise;

import java.util.Stack;

public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack; // tracks running max at each level

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        maxStack.push(maxStack.isEmpty() ? x : Math.max(x, maxStack.peek()));
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int peekMax() { return maxStack.peek(); }

    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> temp = new Stack<>();

        while (stack.peek() != max) {
            temp.push(stack.pop());
            maxStack.pop();
        }
        stack.pop();
        maxStack.pop();

        while (!temp.isEmpty()) push(temp.pop());
        return max;
    }
}
```

---

### 33. Calendar Booking — Conflict Detection

**Problem:** Book time slots without overlaps using a TreeMap for O(log n) lookups.  
**Complexity:** O(log n) per booking | O(n) space

```java
package com.practise;

import java.util.TreeMap;

public class CalendarBooking {
    private TreeMap<Integer, Integer> bookings = new TreeMap<>();

    public boolean bookStage(int start, int end) {
        if (start >= end) return false;

        Integer prev = bookings.floorKey(start);
        if (prev != null && bookings.get(prev) > start) return false;

        Integer next = bookings.ceilingKey(start);
        if (next != null && next < end) return false;

        bookings.put(start, end);
        return true;
    }
}
// bookStage(10,20)→true, bookStage(15,25)→false, bookStage(20,30)→true
```

---

### 34. Group Anagrams

**Problem:** Group words that are anagrams of each other.  
**Complexity:** O(n × k log k) time | O(n) space  (k = avg word length)

```java
package com.practise;

import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(map.values());
    }
}
// Input: {"bat","tab","cat","act","tac","dog","god"}
// Output: [[bat,tab],[cat,act,tac],[dog,god]]
```

---

### 35. Stock Price — Min/Max Heap

**Problem:** Track stock prices by timestamp; support `update`, `current` (latest), `maximum`, `minimum` in O(log n).  
**Complexity:** O(log n) update | O(log n) max/min (lazy deletion) | O(n) space

```java
package com.practise;

import java.util.*;

class StockPrice {
    private Map<Integer, Integer> timestampToPrice = new HashMap<>();
    private PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    private PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    private int latestTimestamp = 0;

    public void update(int timestamp, int price) {
        timestampToPrice.put(timestamp, price);
        if (timestamp > latestTimestamp) latestTimestamp = timestamp;
        maxHeap.offer(new int[]{price, timestamp});
        minHeap.offer(new int[]{price, timestamp});
    }

    public int current() { return timestampToPrice.get(latestTimestamp); }

    public int maximum() {
        while (timestampToPrice.get(maxHeap.peek()[1]) != maxHeap.peek()[0]) maxHeap.poll();
        return maxHeap.peek()[0];
    }

    public int minimum() {
        while (timestampToPrice.get(minHeap.peek()[1]) != minHeap.peek()[0]) minHeap.poll();
        return minHeap.peek()[0];
    }
}
```

---

### 36. Stock Data Structure — TreeMap

**Problem:** Same as Stock Price but uses a TreeMap for O(log n) insert/delete/max.  
**Complexity:** O(log n) all operations | O(n) space

```java
package com.practise;

import java.util.*;

class StockDataStructure {
    private TreeMap<Integer, Integer> stockData = new TreeMap<>();    // timestamp → price
    private TreeMap<Integer, Integer> priceCount = new TreeMap<>();   // price → frequency

    public void insertOrUpdate(int timestamp, int price) {
        if (stockData.containsKey(timestamp)) removePrice(stockData.get(timestamp));
        stockData.put(timestamp, price);
        priceCount.merge(price, 1, Integer::sum);
    }

    public int getRecentStockPrice()  { return stockData.get(stockData.lastKey()); }
    public int getMaxStockPrice()     { return priceCount.lastKey(); }

    public void deleteStock(int timestamp) {
        if (!stockData.containsKey(timestamp)) return;
        removePrice(stockData.remove(timestamp));
    }

    private void removePrice(int price) {
        if (priceCount.get(price) == 1) priceCount.remove(price);
        else priceCount.put(price, priceCount.get(price) - 1);
    }
}
```

---

### 37. Large File Sort — External Sort (Simple)

**Problem:** Sort a file too large to fit in memory using chunk-sort + k-way merge with a PriorityQueue.  
**Complexity:** O(n log n) | O(chunk_size + k) memory

```java
// Strategy:
// 1. Read CHUNK_SIZE numbers at a time, sort in memory, write to temp file
// 2. k-way merge all temp files using a min-heap (Node holds value + BufferedReader)
// See com.practise.LargeFileSort for full implementation

private static class Node {
    int value;
    BufferedReader reader;
}
// Heap always pops the smallest value across all chunk files
// Reads the next line from the same file to refill the heap
```

---

### 38. Large File Sorter — External Sort (Parallel)

**Problem:** Same as above but chunks are sorted concurrently using `CompletableFuture` and a fixed thread pool.  
**Complexity:** O(n log n / p) where p = available processors | O(chunk_size × p) memory

```java
// Key difference from LargeFileSort:
// - Uses FileChannel + ByteBuffer for fast I/O
// - Each chunk sort is submitted as CompletableFuture.supplyAsync(...)
// - CompletableFuture.allOf(...).join() waits for all chunks before merging
// See com.practise.LargeFileSorter for full implementation
ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
```

---

### 39. Monster Game — Simulation

**Problem:** Each turn deal `attackPower` damage to all monsters still alive. Count total turns.  
**Complexity:** O(turns × monsters) time | O(1) space

```java
package com.practise;

public class MonsterGame {

    public static int countTurnsToDefeatAll(int[] monsters, int attackPower) {
        int turns = 0;
        boolean allDead = false;

        while (!allDead) {
            allDead = true;
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] > 0) {
                    monsters[i] -= attackPower;
                    allDead = false;
                }
            }
            if (!allDead) turns++;
        }
        return turns;
    }
}
// Input: monsters={10,20,15}, attackPower=5  →  Output: 4 turns (ceil(20/5))
```

---

### 40. Vendor Placement — Binary Search on Answer

**Problem:** Same pattern as Aggressive Cows — place K shops in available spaces so the minimum distance between any two is at least `distance`.  
**Complexity:** O(n log n) time | O(1) space

```java
package com.practise;

import java.util.Arrays;

public class VendorPlacement {

    public static boolean allocateSpace(int[] spaces, int shopCount, int distance) {
        Arrays.sort(spaces);
        int lastPosition = spaces[0], cnt = 1;

        for (int i = 1; i < spaces.length; i++) {
            if (spaces[i] - lastPosition >= distance) {
                cnt++;
                lastPosition = spaces[i];
                if (cnt == shopCount) return true;
            }
        }
        return false;
    }
}
// Input: spaces={1,2,8,4,9}, shopCount=3, distance=3  →  Output: true
```

---

### 41. Email Regex Validator

**Problem:** Validate email addresses using a compiled regex pattern.

```java
package com.practise;

import java.util.regex.*;

public class RegexExpr {

    private static final String EMAIL_REGEX =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return pattern.matcher(email).matches();
    }
}
// "example@example.com" → true
// "invalid.email.com"   → false
// "invalid@.com"        → false
```

**Regex breakdown:**
| Part | Meaning |
|---|---|
| `^[_A-Za-z0-9-\\+]+` | Local part: letters, digits, `_`, `-`, `+` |
| `(\\.[_A-Za-z0-9-]+)*` | Optional dotted sub-parts in local |
| `@` | Separator |
| `[A-Za-z0-9-]+` | Domain name |
| `(\\.[A-Za-z0-9]+)*` | Optional subdomains |
| `(\\.[A-Za-z]{2,})$` | TLD (at least 2 letters) |

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
| Aggressive Cows / Vendor Placement | O(n log n) | O(n log n) | O(1) | Binary search on answer |
| Array Reverse | O(n) | O(n) | O(1) | Two pointers |
| Trapping Rain Water (two-pointer) | O(n) | O(n) | O(1) | |
| Trapping Rain Water (prefix/suffix) | O(n) | O(n) | O(n) | |
| Rotting Oranges | O(V+E) | O(V+E) | O(V) | Multi-source BFS |
| Knight Moves | O(N²) | O(N²) | O(N²) | BFS on grid |
| Max Stack peekMax | O(1) | O(1) | O(n) | Auxiliary max-stack |
| Max Stack popMax | O(n) | O(n) | O(n) | |
| Calendar Booking | O(log n) | O(log n) | O(n) | TreeMap floor/ceiling |
| Group Anagrams | O(n·k log k) | O(n·k log k) | O(n) | Sort key |
| Stock Price (heap) | O(log n) | O(log n) | O(n) | Lazy deletion |
| Stock Data Structure (TreeMap) | O(log n) | O(log n) | O(n) | |
| Large File Sort | O(n log n) | O(n log n) | O(chunk) | External sort |
| Large File Sorter (parallel) | O(n log n / p) | O(n log n) | O(chunk×p) | CompletableFuture |
| Monster Game | O(turns×m) | O(turns×m) | O(1) | Simulation |
