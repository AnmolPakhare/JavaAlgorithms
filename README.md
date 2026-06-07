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

**DSA Pattern:** `Dynamic Programming — Overlapping Subproblems`

**How we identified it:**
The naive recursion `fib(n) = fib(n-1) + fib(n-2)` recomputes the same values many times (e.g., `fib(3)` is computed dozens of times for `fib(10)`). Two hallmarks of DP are present:
- **Optimal substructure** — the answer to `fib(n)` is fully determined by answers to smaller subproblems.
- **Overlapping subproblems** — those smaller subproblems repeat. Once we spot this, we either cache answers as we recurse down (memoization / top-down) or fill a table from the base case upward (tabulation / bottom-up). Both reduce O(2ⁿ) to O(n).

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

**DSA Pattern:** `Dynamic Programming — Unbounded Knapsack variant`

**How we identified it:**
We need to find the minimum number of coins that sum to `amount`. The key observation: the answer for amount `i` depends on the answer for `i - coin` for every coin denomination. This is a classic **unbounded knapsack** — each coin can be reused any number of times. The brute-force recursion branches at every coin choice (O(2ᴺ)), but the sub-answers repeat, so we fill a 1D DP table `dp[i]` = min coins for amount `i`, building up from `dp[0] = 0`.

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

**DSA Pattern:** `Dynamic Programming — Interval DP`

**How we identified it:**
Whether a substring `s[i..j]` is a palindrome depends on whether `s[i+1..j-1]` is a palindrome AND `s[i] == s[j]`. This **inner-to-outer dependency** is the signature of interval DP — the answer for a larger interval is built from the answer for a smaller interval nested inside it. We fill a 2D boolean table `dp[i][j]`, starting from length-1 substrings and expanding outward. Brute force would check every substring in O(n³); DP reduces it to O(n²).

**Complexity:** O(n²) time | O(n²) space

```java
package com.dynamicprogramming;

public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLength = 1;

        // Every single character is a palindrome
        for (int i = 0; i < n; i++) dp[i][i] = true;

        // Check two-character palindromes
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Expand to longer substrings — uses already-solved inner results
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

**DSA Pattern:** `Dynamic Programming — 0/1 Knapsack`

**How we identified it:**
Each item has two choices: **take it** or **leave it** (0 or 1 — hence "0/1"). The value of taking item `i` with remaining capacity `w` depends on the best value achievable with items `1..i-1` and capacity `w - weight[i]`. This choice structure with two dimensions (items × capacity) and reusable sub-answers is the textbook 0/1 Knapsack DP. The 2D table `dp[i][w]` stores the best value using the first `i` items with capacity `w`, built row by row.

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
                int notTakingItem = knapsackTable[i - 1][w];           // skip item i
                int takingItem = 0;
                if (weights[i] <= w)
                    takingItem = values[i] + knapsackTable[i - 1][w - weights[i]]; // take item i
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

**DSA Pattern:** `Dynamic Programming — Min-Max DP`

**How we identified it:**
We want to minimise the number of drops **in the worst case** — this "minimax" structure is the giveaway. When we drop an egg from floor `x`:
- If it **breaks** → the critical floor is below; we have `n-1` eggs and `x-1` floors left.
- If it **survives** → the critical floor is above; we still have `n` eggs and `m-x` floors left.

We must prepare for the **worst** of these two cases (`Math.max`), then choose the floor `x` that **minimises** that worst case (`Math.min` across all `x`). Sub-problems are indexed by `(eggs, floors)`, and they repeat across choices of `x`, so a 2D DP table eliminates recomputation.

**Complexity:** O(E×F²) time | O(E×F) space — `E` = eggs, `F` = floors

```java
package com.eggdropping;

public class EggDroppingProblem {

    // dpTable[i][j] = minimum drops needed with i eggs and j floors
    private int[][] dpTable = new int[Constants.NUM_OF_EGGS + 1][Constants.NUM_OF_FLOORS + 1];

    public int solve() {
        dpTable[0][0] = 1;
        dpTable[1][0] = 1;

        // 1 egg → must try every floor linearly (worst case = floor number)
        for (int i = 0; i <= Constants.NUM_OF_FLOORS; i++)
            dpTable[1][i] = i;

        for (int n = 2; n <= Constants.NUM_OF_EGGS; n++) {
            for (int m = 1; m <= Constants.NUM_OF_FLOORS; m++) {
                dpTable[n][m] = Integer.MAX_VALUE;
                for (int x = 1; x <= m; x++) {
                    // worst case at floor x, then minimise over all x
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

**DSA Pattern:** `Divide and Conquer — Binary Search`

**How we identified it:**
The array is **sorted**, which is the precondition for binary search. At each step we compare the target with the middle element and **eliminate half** the search space — either the left or right half is provably irrelevant. This "divide the problem in half and recurse on one side" structure is divide and conquer. The recursive call has no branching (unlike merge sort which recurses on both halves), so the recurrence is T(n) = T(n/2) + O(1) → O(log n).

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
            return binarySearch(startIndex, middleIndex - 1, item);  // search left half
        } else {
            return binarySearch(middleIndex + 1, endIndex, item);    // search right half
        }
    }
}
```

---

### 7. Towers of Hanoi

**DSA Pattern:** `Recursion — Divide and Conquer`

**How we identified it:**
To move `n` discs from A to C, we must first move `n-1` discs out of the way (to B), then move the largest disc, then move the `n-1` discs from B to C. The problem of size `n` is **reduced to two subproblems of size `n-1`** with a constant-time step in between. There are no repeated subproblems (each call operates on a distinct set of discs), so memoization gives no benefit — pure recursion is optimal. The 2ⁿ−1 total moves are unavoidable by mathematical proof.

**Complexity:** O(2ⁿ) time | O(n) space (call stack depth = n)

```java
package com.RecursiveProblems.TowersOfHanoi;

public class Algorithm {

    public void solveHanoiProblem(int n, char rodFrom, char middleRod, char rodTo) {
        if (n == 1) {
            System.out.println("Plate 1 from " + rodFrom + " to " + rodTo);
            return;
        }
        // Step 1: move n-1 discs out of the way
        solveHanoiProblem(n - 1, rodFrom, rodTo, middleRod);
        // Step 2: move the largest disc
        System.out.println("Plate " + n + " from " + rodFrom + " to " + rodTo);
        // Step 3: move n-1 discs from middle rod to destination
        solveHanoiProblem(n - 1, middleRod, rodFrom, rodTo);
    }
}
```

---

### 8. Factorial

**DSA Pattern:** `Recursion — Linear Reduction`

**How we identified it:**
`n! = n × (n-1)!` — the problem reduces by exactly 1 at each step with no branching and no overlapping subproblems. This is the simplest form of recursion: a **linear chain** where each call depends on exactly one smaller call. Because there are no repeated subproblems, DP adds no value here. The recursion terminates at the base case `0! = 1`.

**Complexity:** O(n) time | O(n) space (call stack)

```java
package com.RecursiveProblems.Factorial;

public class Algorithm {

    public int factorial(int n) {
        if (n == 0) return 1;           // base case
        return n * factorial(n - 1);    // linear reduction: problem shrinks by 1 each time
    }
}
```

---

### 9. Euclidean Algorithm (GCD)

**DSA Pattern:** `Recursion — Mathematical Reduction`

**How we identified it:**
The key mathematical insight is `gcd(a, b) = gcd(b, a % b)`. The modulo operation **dramatically shrinks** the numbers at each step (by Lamé's theorem, it takes at most O(log(min(a,b))) steps). This is pure recursion driven by a mathematical identity — not divide and conquer (we don't split a data structure) and not DP (no repeated subproblems). The reduction is so aggressive that it converges in logarithmic time even for very large inputs.

**Complexity:** O(log(min(a, b))) time | O(log(min(a, b))) space

```java
package com.RecursiveProblems.EuclideanAlgorithm;

public class Algorithm {

    public int gcd(int a, int b) {
        if (b == 0) return a;           // base case: gcd(a, 0) = a
        return gcd(b, a % b);           // mathematical reduction
    }
}
```

---

## SLIDING WINDOW

---

### 10. Fixed Window — Max Sum Subarray

**DSA Pattern:** `Sliding Window — Fixed Size`

**How we identified it:**
The problem asks for the best result over **every contiguous subarray of exactly size k**. The brute-force recalculates the sum of each window from scratch: O(n×k). The key observation: when we slide the window one step right, the new sum = old sum + new right element − old left element. This **O(1) update per step** by adding/subtracting one element is the hallmark of the fixed sliding window. We identified it because:
1. We are looking at a subarray of **fixed length k**.
2. Adjacent windows overlap in k-1 elements — we can reuse the previous computation.

**Complexity:** O(n) time | O(1) space

```java
package com.slidingwindow;

public class FixedSizeWindow_MaxSumSubarray {

    public static int findMaxSumSubarray(int[] arr, int k) {
        if (arr.length < k) return -1;

        int maxSum = 0, windowSum = 0, windowStart = 0;

        // Compute first window sum
        for (int i = 0; i < k; i++) windowSum += arr[i];
        maxSum = windowSum;

        // Slide: add new right element, drop old left element — O(1) per step
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

**DSA Pattern:** `Sliding Window — Variable Size (Expand & Shrink)`

**How we identified it:**
We want the longest **contiguous** substring satisfying a constraint (no repeating characters). Contiguous + constraint → think sliding window. The window size is not fixed — we **expand** the right pointer to include more characters, and **shrink** the left pointer whenever the constraint is violated (a duplicate appears). A HashMap tracks the last-seen index of each character so we can jump `windowStart` directly past the duplicate (no need to shrink one step at a time). This "expand right freely, shrink left on violation" is the variable-size sliding window pattern.

**Complexity:** O(n) time | O(alphabet size) space

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

            // Shrink: jump left pointer past the duplicate
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

**DSA Pattern:** `Sliding Window — Variable Size (Shrink While Valid)`

**How we identified it:**
We want the **smallest** contiguous subarray whose sum ≥ S. Contiguous subarray + optimise length → sliding window. Unlike the previous problem where we shrink on violation, here we **shrink while the constraint is satisfied** (sum ≥ S) because a smaller valid window is better. We expand the right pointer unconditionally, then greedily shrink the left pointer as long as the window sum still meets the threshold, recording the minimum length each time. The `while` (not `if`) shrink is the tell — we squeeze the window as tight as possible before moving on.

**Complexity:** O(n) time | O(1) space

```java
package com.slidingwindow;

public class VariableSizeWindow_MinSizeSubarraySum {

    public static int findMinSubArray(int S, int[] arr) {
        int minLength = Integer.MAX_VALUE, windowSum = 0, windowStart = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];   // expand right

            // Shrink left as long as window is still valid — capture minimum
            while (windowSum >= S) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
// Input: S=7, {2,1,5,2,3,2}  →  Output: 2  ({5,2})
```

---

### 13. Longest Substring With Exactly K Unique Chars

**DSA Pattern:** `Sliding Window — Variable Size + Math Identity`

**How we identified it:**
"Exactly K" constraints are notoriously hard to handle directly in a sliding window because shrinking past K unique chars overshoots. The trick: use the mathematical identity  
`exactlyK = atMostK(k) − atMostK(k−1)`  
"At most K" is straightforward to solve with a variable sliding window (shrink whenever unique chars exceed K). Running it twice and subtracting gives "exactly K" — no extra complexity. We identified the sliding window because the problem is about a **contiguous substring** with a **frequency constraint**, and the atMostK helper fits the expand/shrink template directly.

**Complexity:** O(n) time | O(k) space

```java
package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithExactlyKUniqueChars {

    public static int longestSubstringKUnique(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);  // math identity
    }

    private static int atMostK(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int result = 0, windowStart = 0;
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            freq.merge(s.charAt(windowEnd), 1, Integer::sum);  // expand
            while (freq.size() > k) {                          // shrink on violation
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

**DSA Pattern:** `Sorting + Greedy — Interval Merge`

**How we identified it:**
The problem involves ranges that may overlap. The key insight: if we **sort intervals by start time**, overlapping intervals are guaranteed to be adjacent. Then a single left-to-right greedy scan suffices — if the current interval overlaps with the last merged one (start of current ≤ end of last), extend the end; otherwise, start a new merged interval. We identified this pattern because: (1) the input is a set of ranges, (2) we are asked to combine touching/overlapping ones, (3) sorting makes the greedy decision local and correct.

**Complexity:** O(n log n) time | O(n) space

```java
package com.intervals;

import java.util.*;

public class MergeOverlappingIntervals {

    public static List mergeIntervals(int[][] input) {
        List output = new ArrayList<int[]>();
        Arrays.sort(input, Comparator.comparing(a -> a[0]));  // sort by start time

        int[] current = input[0];
        for (int i = 1; i < input.length; i++) {
            if (current[1] >= input[i][0]) {
                // Overlapping — extend the current interval's end
                current[1] = Math.max(current[1], input[i][1]);
            } else {
                // Gap found — commit the current interval, move to next
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

**DSA Pattern:** `Two Pointers — on Two Sorted Arrays`

**How we identified it:**
We have **two sorted lists** of intervals and need to find all overlapping pairs between them. This is the classic two-pointer merge pattern applied to intervals. We maintain one pointer per list and advance the pointer whose interval ends first (since it can no longer intersect with any future interval in the other list). The intersection of two intervals `[a,b]` and `[c,d]` exists when `max(a,c) ≤ min(b,d)`. We identified two pointers because: (1) both lists are sorted, (2) we need to compare elements across two lists efficiently, (3) advancing the pointer with the smaller end time is a greedy correct move.

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

            if (start <= end) output.add(new int[]{start, end}); // valid intersection

            // Advance the pointer whose interval ends sooner
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

**DSA Pattern:** `Greedy — Activity Selection`

**How we identified it:**
This is the classic **Activity Selection Problem**: select the maximum number of non-conflicting intervals. The greedy choice is: always pick the interval that **ends earliest** among those that don't conflict with the last picked one. Sorting by end time makes this greedy decision locally and globally optimal. We identified greedy (not DP) because: sorting by end time creates a clear local criterion that never needs to be reconsidered, and the greedy choice provably leads to the global optimum (exchange argument proof).

**Complexity:** O(n log n) time | O(n) space

```java
// Greedy — sort by end time, pick intervals that start after the last accepted ends
Arrays.sort(intervals, Comparator.comparing(a -> a[1]));  // sort by end time
PriorityQueue<Integer> q = new PriorityQueue<>();
q.offer(intervals[0][1]);
for (int i = 1; i < intervals.length; i++) {
    // If this interval starts after the earliest-ending accepted interval, accept it
    if (intervals[i][0] >= q.peek()) q.offer(intervals[i][1]);
}
return q.size(); // total non-overlapping intervals selected
```

---

### 17. Minimum Meeting Rooms

**DSA Pattern:** `Greedy + Min-Heap — Interval Scheduling`

**How we identified it:**
We need the minimum number of rooms such that no two overlapping meetings share a room. The greedy insight: process meetings in order of **start time**. For each new meeting, check if any existing room is free (i.e., its current meeting ended ≤ new start). The **min-heap of end times** efficiently gives us the room that frees up soonest in O(log n). If the earliest-ending room is still busy, we must open a new room. We identified the heap because: we repeatedly need the minimum end time across all active meetings — that's exactly what a min-heap provides.

**Complexity:** O(n log n) time | O(n) space

```java
package com.intervals;

import java.util.*;

public class MinimumMeetingRoom {

    public static int minimumMeetingRooms(int[][] input) {
        Arrays.sort(input, Comparator.comparing(a -> a[0]));   // sort by start time
        PriorityQueue<Integer> q = new PriorityQueue<>();       // min-heap of end times

        for (int[] interval : input) {
            // If the earliest-ending room is free, reuse it
            if (!q.isEmpty() && q.peek() <= interval[0]) q.poll();
            q.offer(interval[1]);  // assign this meeting to a room (or open a new one)
        }
        return q.size();  // rooms still occupied = rooms needed
    }
}
// Input: {{1,5},{2,6},{4,8},{9,12}}  →  Output: 3 rooms
```

---

### 18. Free Time Intervals

**DSA Pattern:** `Sorting + Interval Merge — Gap Detection`

**How we identified it:**
Free time is the **gaps** between busy intervals across all employees. The approach: flatten all busy intervals into one list, sort by start, merge overlaps (same as #14), then scan the merged list for gaps between consecutive intervals. We identified this as a variant of the interval merge pattern because: (1) overlapping busy periods across employees should be treated as one block, (2) the gaps between merged blocks are the free windows. The merge step is identical to problem #14; only the final step changes (find gaps instead of return merged list).

**Complexity:** O(n log n) time | O(n) space

```java
// Concept: flatten all schedules → sort → merge overlaps → gaps between merged = free time
// See com.intervals.FreeTimeInterval for full implementation
```

---

## GRAPH ALGORITHMS

---

### 19. Graph — BFS & DFS

**DSA Pattern:** `Graph Traversal — BFS (Queue) and DFS (Stack / Recursion)`

**How we identified it:**
The problem is to **visit every reachable node** in a graph without revisiting any node. Two fundamental traversal strategies exist:
- **BFS** — uses a Queue (FIFO). Visits all neighbours of the current node before going deeper. Natural choice when you need **shortest path** or **level-order** processing because nodes are processed in order of distance from the source.
- **DFS** — uses a Stack (explicit or call stack). Goes as deep as possible before backtracking. Natural for **cycle detection**, **topological sort**, and **connectivity**. We identified BFS/DFS because: (1) nodes are connected by edges (graph), (2) we need to visit all reachable nodes exactly once, (3) the choice between BFS and DFS depends on whether level-order or depth-first order matters.

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

    // BFS — Queue ensures level-by-level (shortest path) order
    void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjList.get(node))
                if (!visited.contains(neighbor)) { visited.add(neighbor); queue.add(neighbor); }
        }
    }

    // DFS iterative — Stack reverses neighbour order to match recursive behaviour
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

    // DFS recursive — call stack acts as the implicit stack
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

**DSA Pattern:** `Backtracking — Constraint Satisfaction`

**How we identified it:**
We must assign one of `m` colours to each vertex such that no two adjacent vertices share a colour. There is no greedy rule that always works (the optimal choice for one vertex can force a dead-end later), so we must **try a colour, check the constraint, recurse, and undo** if we hit a dead-end. This try-check-recurse-undo loop is the definition of backtracking. We identified it over DP because: the state space is the entire colouring of the graph, partial solutions can be invalid in ways that don't repeat, and we need to explore different branches.

**Complexity:** O(mᵛ) worst case — `m` = colours, `v` = vertices

```java
// See com.ColoringProblem.GraphColoring for full implementation.
// Core idea:
//   for each vertex v:
//     for each color c in 1..m:
//       if no adjacent vertex has color c:
//         assign color c to v
//         recurse on v+1
//         if recursion succeeds → done
//         else → unassign (backtrack) and try next color
```

---

### 21. Hamiltonian Path

**DSA Pattern:** `Backtracking — Exhaustive Search`

**How we identified it:**
A Hamiltonian path visits every vertex **exactly once**. No known polynomial algorithm exists — we must try every possible ordering of vertices. At each step we extend the current path by adding an unvisited adjacent vertex, recurse, and backtrack if no extension leads to a complete path. The exhaustive nature (O(n!)) and the undo-on-failure step are the backtracking signatures. We identified it over graph traversal (BFS/DFS) because the constraint "visit each vertex exactly once" means we cannot freely revisit — we need explicit tracking of what's been used and the ability to undo that tracking.

**Complexity:** O(n!) worst case

```java
// See com.hamiltonian.HamiltonianAlgorithm for full implementation.
// Core idea:
//   maintain a path[] and inPath[] boolean array
//   at each step: try adding every unvisited neighbour → recurse → backtrack
//   success when path.length == numVertices
```

---

## SORTING / SELECTION

---

### 22. Quickselect — Kth Largest Element

**DSA Pattern:** `Divide and Conquer — Partition-based Selection`

**How we identified it:**
We need the Kth largest element, but sorting the full array wastes work — we only care about one element's final position. The Quicksort partition step places a pivot in its **correct sorted position** in O(n). After partitioning, the pivot's index tells us: if `pivot == k`, we're done; if `pivot > k`, the answer is in the left partition; if `pivot < k`, it's in the right partition. We recurse on **only one side** (unlike Quicksort which recurses on both). This selective recursion is the divide-and-conquer efficiency gain. Randomising the pivot avoids worst-case O(n²) on sorted inputs.

**Complexity:** O(n) average | O(n²) worst case | O(1) space

```java
package com.quickselect;

import java.util.Random;

public class Quickselect {

    private int[] nums;

    public Quickselect(int[] nums) { this.nums = nums; }

    public int select(int k) { return select(0, nums.length - 1, k - 1); }

    private int partition(int indexFirst, int indexLast) {
        int pivot = new Random().nextInt(indexLast - indexFirst + 1) + indexFirst;
        swap(indexLast, pivot);
        for (int i = indexFirst; i < indexLast; i++) {
            if (nums[i] > nums[indexLast]) { swap(i, indexFirst); indexFirst++; }
        }
        swap(indexFirst, indexLast);
        return indexFirst;
    }

    private int select(int indexFirst, int indexLast, int k) {
        int pivot = partition(indexFirst, indexLast);
        if (pivot > k)      return select(indexFirst, pivot - 1, k);  // answer in left half
        else if (pivot < k) return select(pivot + 1, indexLast, k);   // answer in right half
        return nums[k];                                                 // pivot IS the answer
    }

    private void swap(int i, int j) {
        int tmp = nums[i]; nums[i] = nums[j]; nums[j] = tmp;
    }
}
```

---

### 23. Bin Packing — First Fit Decreasing

**DSA Pattern:** `Greedy — Approximation Algorithm`

**How we identified it:**
Bin packing is NP-hard, so we cannot solve it optimally in polynomial time. The greedy heuristic: **sort items largest-first** (Decreasing), then for each item scan bins left-to-right and place it in the **first bin that fits** (First Fit). Sorting largest-first is the greedy insight — large items are hardest to place, so placing them early when bins have maximum free space reduces waste. We identified greedy over DP because: (1) finding the true optimum is NP-hard, (2) a locally sensible rule (first fit) yields a provably good approximation (≤ 11/9 · OPT).

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
        Collections.sort(this.items, Collections.reverseOrder()); // greedy: largest items first

        if (this.items.get(0) > this.binCapacity) {
            System.out.println("No feasible solution...");
            return;
        }

        this.bins.add(new Bin(binCapacity, binCounter));

        for (Integer currentItem : items) {
            boolean isOk = false;
            int currentBin = 0;
            while (!isOk) {
                if (currentBin == this.bins.size()) {          // no existing bin fits → open new
                    Bin newBin = new Bin(binCapacity, ++binCounter);
                    newBin.put(currentItem);
                    this.bins.add(newBin);
                    isOk = true;
                } else if (this.bins.get(currentBin).put(currentItem)) { // first fit
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

**DSA Pattern:** `Divide and Conquer — Geometric`

**How we identified it:**
Finding the closest pair among n points naively is O(n²). The divide-and-conquer approach: split points by median x-coordinate into left and right halves, solve each recursively, then check only the **strip** of width 2δ around the dividing line (where δ = min of the two recursive results). It can be proven that the strip contains at most O(1) candidate points per point, so the merge step is O(n). This gives T(n) = 2T(n/2) + O(n log n) → O(n log² n), or O(n log n) with a careful sort. We identified divide and conquer because: (1) the 2D geometry makes it non-trivial to apply DP or greedy, (2) splitting by x and recursing is the natural decomposition, (3) the merge only needs a narrow strip.

**Complexity:** O(n log n) time | O(n) space

```java
// See com.cpop.Algorithm for full divide-and-conquer implementation.
// Core steps:
//   1. Sort points by x (XSorter comparator)
//   2. Recursively find closest pair in left half and right half
//   3. δ = min(leftDist, rightDist)
//   4. Check strip of width 2δ around the dividing line for cross-half pairs
//   5. Return the overall minimum
```

---

## STRING ALGORITHMS

---

### 25. Anagram Detection

**DSA Pattern:** `Sorting — Canonical Form Comparison`

**How we identified it:**
Two words are anagrams if they contain exactly the same characters with the same frequencies. The simplest way to check: **sort both character arrays** — anagrams produce identical sorted strings. We identified sorting over a frequency-map approach because the code is simpler and the complexity is the same class (O(n log n) vs O(n) for frequency map, but for short words the constant factor of the sort is negligible). The canonical form (sorted string) approach generalises to Group Anagrams (#34) as a HashMap key.

**Complexity:** O(n log n) time | O(n) space

```java
package com.anagram;

import java.util.Arrays;

public class AnagramProblem {

    public boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        Arrays.sort(arr1);  // canonical form
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);  // identical canonical forms → anagram
    }
}
```

---

## PRACTICE PROBLEMS

---

### 26. Aggressive Cows — Binary Search on Answer

**DSA Pattern:** `Binary Search on Answer — Monotonic Predicate`

**How we identified it:**
The answer (maximum minimum distance) lies in the range `[1, max_stall - min_stall]`. The key observation: if placing cows with minimum distance `d` is **feasible**, then any distance `d' < d` is also feasible (a weaker requirement). This **monotonic** feasibility function — feasible for small d, infeasible for large d — is the signature of "binary search on the answer". We binary search over the distance values and check feasibility with a greedy linear scan (place next cow only when the distance from the last placed cow is ≥ mid). We identified this over DP/greedy because the answer space (distances) is ordered and has the monotone property.

**Complexity:** O(n log n) time | O(1) space

```java
package com.practise;

import java.util.*;

public class AggressiveCow {

    // Greedy check: can we place 'cows' cows with at least 'minDist' apart?
    public static boolean canPlaceCows(int[] stalls, int cows, int minDist) {
        int count = 1, lastPlaced = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPlaced >= minDist) {
                count++;
                lastPlaced = stalls[i];
                if (count == cows) return true;
            }
        }
        return false;
    }

    // Binary search on the answer space [1, max_gap]
    public static int aggressiveCows(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0], bestDist = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlaceCows(stalls, cows, mid)) {
                bestDist = mid;
                low = mid + 1;   // feasible → try larger distance
            } else {
                high = mid - 1;  // infeasible → try smaller distance
            }
        }
        return bestDist;
    }
}
// Input: stalls={1,2,8,4,9}, cows=3  →  Output: 3
```

---

### 27. Array Reverse — Two Pointers

**DSA Pattern:** `Two Pointers — Inward Convergence`

**How we identified it:**
We need to reverse an array **in-place** — the first element swaps with the last, the second with the second-to-last, and so on. Two pointers starting at opposite ends and moving toward each other is the natural fit. We identified two pointers because: (1) we need to operate on two elements at once (one from each end), (2) the operation is symmetric (swap), (3) they meet in the middle — no element is processed twice. This is the simplest two-pointer pattern and runs in O(n) with O(1) extra space.

**Complexity:** O(n) time | O(1) space

```java
package com.practise;

public class ArrayReverse {

    public int[] solve(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            // Swap elements at the two pointers, then converge inward
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

**DSA Pattern:** `Two Pointers — Inward Convergence with Running Max`

**How we identified it:**
Water trapped at index `i` = `min(maxLeft[i], maxRight[i]) - height[i]`. The brute force precomputes both arrays (O(n) space). The two-pointer optimisation: maintain `leftMax` and `rightMax` as running maximums while converging inward. The insight: if `height[left] < height[right]`, the water at `left` is determined solely by `leftMax` (because `rightMax ≥ height[right] > height[left]` guarantees the right side is not the bottleneck). This asymmetric progress — **move the shorter side** — is the two-pointer pattern. We identified it because the problem has a left-right dependency that can be resolved by always processing the side with less information needed.

**Complexity:** O(n) time | O(1) space

```java
package com.practise;

public class TrappingRainWater {

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                // Left side is the bottleneck — water at left depends only on leftMax
                if (height[left] >= leftMax) leftMax = height[left];
                else water += leftMax - height[left];
                left++;
            } else {
                // Right side is the bottleneck — water at right depends only on rightMax
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

**DSA Pattern:** `Prefix Sum / Precomputation`

**How we identified it:**
The water at each index needs `maxLeft[i]` and `maxRight[i]`. Computing these on the fly while scanning left-to-right is only possible for one direction. The solution: **precompute both** in two separate passes, then answer each index in O(1). This precompute-then-query pattern is the Prefix Sum pattern. We identified it because: each query (`min(leftMax, rightMax) - height[i]`) depends on aggregate information from the left and the right simultaneously, which a single-pass scan cannot provide — a two-pass precomputation resolves this. This uses more space (O(n)) than the two-pointer version but is easier to understand.

**Complexity:** O(n) time | O(n) space

```java
package com.practise;

public class TrappingRainWater2 {

    public static int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n], rightMax = new int[n];

        // Pass 1: leftMax[i] = max height from index 0 to i
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++)
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);

        // Pass 2: rightMax[i] = max height from index i to n-1
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);

        // Pass 3: compute trapped water at each index using precomputed arrays
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

**DSA Pattern:** `BFS — Multi-source Level-by-Level (Shortest Time)`

**How we identified it:**
Multiple rotten oranges spread rot simultaneously each minute. This is **not** single-source BFS — it's multi-source: all initial rotten oranges start spreading at time 0. We add all of them to the queue upfront and run BFS level by level, where each level = 1 minute. BFS is the right choice (not DFS) because: (1) we want the **minimum time** for rot to spread to all oranges, and BFS processes nodes in order of distance from the source (= time steps here). (2) The grid graph has uniform edge weights (1 minute per step). We identified BFS over DP because the spreading is a process on a graph, not a decision problem with optimal substructure.

**Complexity:** O(rows × cols) time | O(rows × cols) space

```java
package com.practise;

import java.util.*;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        // Multi-source: enqueue ALL initially rotten oranges at time 0
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
                else if (grid[i][j] == 1) fresh++;
            }

        if (fresh == 0) return 0;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int minutes = 0;

        // Each BFS level = 1 minute of simultaneous spreading
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

**DSA Pattern:** `BFS — Unweighted Shortest Path on Grid`

**How we identified it:**
A chess knight has 8 possible L-shaped moves from any cell. We want the **minimum number of moves** to reach a target. This is a classic **shortest path on an unweighted graph** where each cell is a node and each valid knight move is an edge of weight 1. BFS is optimal for unweighted shortest path because it visits nodes in order of increasing distance. DFS would not give shortest path, and Dijkstra is overkill (all edges have the same weight = 1). We identified BFS because: (1) unweighted grid graph, (2) we want minimum steps, (3) BFS guarantees the first time we reach the target is via the shortest path.

**Complexity:** O(N²) time | O(N²) space

```java
package com.practise;

import java.util.*;

class KnightMoves {

    private static final int[][] MOVES = {
        {-2,-1},{-2,1},{2,-1},{2,1},{-1,-2},{-1,2},{1,-2},{1,2}  // all 8 L-shapes
    };

    public static int minKnightMoves(int N, int sx, int sy, int tx, int ty) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy, 0});   // {x, y, steps}
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tx && cur[1] == ty) return cur[2]; // first arrival = shortest path

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

**DSA Pattern:** `Stack + Auxiliary Data Structure`

**How we identified it:**
A regular stack gives O(1) push/pop but O(n) max (requires a scan). The insight: maintain a **second stack** (`maxStack`) in parallel that tracks the running maximum at each level. When we push `x`, we push `max(x, maxStack.peek())` onto `maxStack`. When we pop, we pop from both. Since the maxStack mirrors the main stack's history of maximums, `maxStack.peek()` is always the current max in O(1). We identified the auxiliary stack pattern because: the constraint (O(1) max) is about the stack's state at any point, and mirroring the main stack with an aggregated view is the standard technique for augmenting stacks/queues.

**Complexity:** push/pop/peekMax O(1) | popMax O(n) | O(n) space

```java
package com.practise;

import java.util.Stack;

public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;  // parallel stack tracking running max

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        // maxStack always holds the max reachable from current stack depth downward
        maxStack.push(maxStack.isEmpty() ? x : Math.max(x, maxStack.peek()));
    }

    public int pop() {
        maxStack.pop();       // keep in sync
        return stack.pop();
    }

    public int peekMax() { return maxStack.peek(); }  // O(1) — top of auxiliary stack

    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> temp = new Stack<>();
        while (stack.peek() != max) {   // pop until we find the max element
            temp.push(stack.pop());
            maxStack.pop();
        }
        stack.pop(); maxStack.pop();
        while (!temp.isEmpty()) push(temp.pop());  // restore (re-push updates maxStack)
        return max;
    }
}
```

---

### 33. Calendar Booking — Conflict Detection

**DSA Pattern:** `Ordered Map (TreeMap) — Interval Overlap Check`

**How we identified it:**
When booking a new slot `[start, end)`, we only need to check the **nearest existing bookings** — the one starting just before `start` and the one starting just after `start`. A TreeMap maintains bookings in sorted key order and provides `floorKey(start)` (predecessor) and `ceilingKey(start)` (successor) in O(log n). No overlap exists if the predecessor ends before `start` AND the successor starts after `end`. We identified the TreeMap pattern (not a list scan) because: the "nearest neighbour" query is exactly what a balanced BST supports in O(log n), making each booking O(log n) instead of O(n).

**Complexity:** O(log n) per booking | O(n) space

```java
package com.practise;

import java.util.TreeMap;

public class CalendarBooking {
    private TreeMap<Integer, Integer> bookings = new TreeMap<>();  // start → end

    public boolean bookStage(int start, int end) {
        if (start >= end) return false;

        // Check predecessor: does the previous booking end after our start?
        Integer prev = bookings.floorKey(start);
        if (prev != null && bookings.get(prev) > start) return false;

        // Check successor: does the next booking start before our end?
        Integer next = bookings.ceilingKey(start);
        if (next != null && next < end) return false;

        bookings.put(start, end);  // no conflict — book it
        return true;
    }
}
// bookStage(10,20)→true, bookStage(15,25)→false (overlaps prev), bookStage(20,30)→true
```

---

### 34. Group Anagrams

**DSA Pattern:** `Hashing — Canonical Form as HashMap Key`

**How we identified it:**
Anagrams share the same multiset of characters. If we sort each word's characters, anagrams produce the **same sorted string** (canonical form). Using this as a HashMap key groups all anagrams together in one pass. We identified hashing over sorting-all-words because: we need to group, not just compare pairs. The HashMap gives O(1) bucket insertion per word; the only cost is computing the canonical form (O(k log k) per word of length k). This "compute a canonical key, group by key" approach generalises to many grouping problems.

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
            String key = new String(chars);  // canonical form: same for all anagrams

            // Group words by their canonical form
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

**DSA Pattern:** `Heap + HashMap — Lazy Deletion`

**How we identified it:**
We need O(log n) `maximum()` and `minimum()` queries with the ability to **update** past prices. A sorted structure like TreeMap handles updates cleanly, but a heap is more intuitive here. The challenge: updating a price at a timestamp invalidates old heap entries but heaps don't support random deletion. The solution is **lazy deletion** — don't remove stale entries immediately; instead, when peeking the max/min, check whether the top of the heap still matches the HashMap (source of truth) and discard stale entries until it does. We identified this because: (1) we need max and min queries → heap, (2) prices can be corrected → stale entries → lazy deletion with a HashMap as ground truth.

**Complexity:** O(log n) update | O(log n) max/min (amortised) | O(n) space

```java
package com.practise;

import java.util.*;

class StockPrice {
    private Map<Integer, Integer> timestampToPrice = new HashMap<>();  // ground truth
    private PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    private PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    private int latestTimestamp = 0;

    public void update(int timestamp, int price) {
        timestampToPrice.put(timestamp, price);  // update ground truth
        if (timestamp > latestTimestamp) latestTimestamp = timestamp;
        maxHeap.offer(new int[]{price, timestamp});  // old entry stays — handled lazily
        minHeap.offer(new int[]{price, timestamp});
    }

    public int current() { return timestampToPrice.get(latestTimestamp); }

    public int maximum() {
        // Discard stale entries (where heap price ≠ HashMap price for that timestamp)
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

**DSA Pattern:** `Ordered Map (TreeMap) — Dual-index Sorted Structure`

**How we identified it:**
We need two different sorted orders simultaneously: timestamps (to get the most recent price) and prices (to get the maximum price). Two TreeMaps solve this: `stockData` (timestamp → price) for ordered timestamp access, and `priceCount` (price → frequency) for ordered price access. Both support O(log n) insert/delete/max/min. We identified TreeMap over HashMap because `lastKey()` (get max or latest) requires **ordered** key traversal — a HashMap cannot do this. We identified two separate TreeMaps (not one) because the two orderings serve different queries.

**Complexity:** O(log n) all operations | O(n) space

```java
package com.practise;

import java.util.*;

class StockDataStructure {
    private TreeMap<Integer, Integer> stockData = new TreeMap<>();   // timestamp → price
    private TreeMap<Integer, Integer> priceCount = new TreeMap<>();  // price → frequency

    public void insertOrUpdate(int timestamp, int price) {
        if (stockData.containsKey(timestamp)) removePrice(stockData.get(timestamp));
        stockData.put(timestamp, price);
        priceCount.merge(price, 1, Integer::sum);
    }

    public int getRecentStockPrice() { return stockData.get(stockData.lastKey()); }  // O(log n)
    public int getMaxStockPrice()    { return priceCount.lastKey(); }               // O(log n)

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

**DSA Pattern:** `Divide and Conquer + Heap — External K-way Merge Sort`

**How we identified it:**
The file is too large to load into memory — a standard sort is impossible. External merge sort is the solution: **divide** the file into chunks that fit in memory, sort each chunk (any in-memory sort), write chunks to temp files, then **merge** all sorted chunks using a **min-heap** (priority queue). The heap always yields the globally smallest unprocessed element across all chunk files in O(log k) where k = number of chunks. We identified this as divide and conquer because: (1) divide the problem into independent sorted chunks, (2) merge using the heap as the combiner. The heap specifically is chosen because we need the minimum across k sorted streams.

**Complexity:** O(n log n) time | O(chunk_size + k) memory — k = number of chunks

```java
// Strategy:
// Step 1 — Divide: read CHUNK_SIZE numbers, sort in memory, write to temp file
// Step 2 — Merge:  open all temp files simultaneously; use a min-heap of (value, reader) pairs
//                  heap.poll() → globally smallest; read next from same reader → heap.offer()

private static class Node {
    int value;
    BufferedReader reader;  // which chunk file this value came from
}

// The heap ensures we always write the globally smallest remaining number next.
// Each element is heap-inserted and heap-removed exactly once → O(n log k) merge.
// See com.practise.LargeFileSort for full implementation.
```

---

### 38. Large File Sorter — External Sort (Parallel)

**DSA Pattern:** `Divide and Conquer + Concurrency — Parallel External Sort`

**How we identified it:**
Same divide-and-conquer external sort as #37, but the chunk-sorting phase is parallelised. Each chunk sort is an **independent** sub-problem (no shared state between chunks), making it a perfect candidate for `CompletableFuture.supplyAsync()` on a thread pool. The merge phase is unchanged — it must be sequential because the heap processes one element at a time. We identified the parallel pattern because: (1) chunk sorts are embarrassingly parallel (no dependencies between chunks), (2) `Runtime.getRuntime().availableProcessors()` threads can sort p chunks simultaneously, (3) `CompletableFuture.allOf(...).join()` synchronises before merge.

**Complexity:** O(n log n / p) parallel sort phase | O(n log k) merge phase | p = processors

```java
// Key differences from LargeFileSort (#37):
// - FileChannel + ByteBuffer for non-blocking I/O (faster than BufferedReader for large files)
// - Each chunk sort runs as CompletableFuture.supplyAsync(...) on a fixed thread pool
// - CompletableFuture.allOf(futures).join() — barrier: all chunks done before merge starts
// - Merge phase is identical to #37 (sequential min-heap k-way merge)

ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

private static CompletableFuture<Path> sortAndWriteChunk(List<Integer> chunk) {
    return CompletableFuture.supplyAsync(() -> {
        Collections.sort(chunk);
        // write to temp file and return its Path
    }, executor);
}
// See com.practise.LargeFileSorter for full implementation.
```

---

### 39. Monster Game — Simulation

**DSA Pattern:** `Simulation — Direct State Update`

**How we identified it:**
The problem has no optimisation objective and no overlapping subproblems — it simply asks "what happens if we follow these rules?" That is the definition of a **simulation**. Each turn we apply `attackPower` damage to every living monster and count the turn. The loop terminates when all monsters are at ≤ 0 HP. We identified simulation over DP/greedy because: (1) there is no choice to make — the process is fully deterministic, (2) the answer is derived by just executing the described process step by step.

**Complexity:** O(ceil(maxHP / attackPower) × numMonsters) time | O(1) space

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
                    monsters[i] -= attackPower;   // apply damage
                    allDead = false;
                }
            }
            if (!allDead) turns++;
        }
        return turns;
    }
}
// Input: monsters={10,20,15}, attackPower=5  →  Output: 4 turns  (ceil(20/5))
```

---

### 40. Vendor Placement — Binary Search on Answer

**DSA Pattern:** `Binary Search on Answer — Monotonic Predicate`

**How we identified it:**
Identical reasoning to Aggressive Cows (#26). We ask: "can we place K shops such that every pair is at least `d` apart?" The feasibility of distance `d` is monotone — if `d` works, any `d' < d` also works. So we binary search over `d` and check feasibility with a greedy linear scan. We identified binary search on the answer because: (1) we are asked to check/confirm a distance (not compute it directly), (2) the feasibility function is monotone, (3) the answer space (distances between sorted positions) is ordered and bounded.

**Complexity:** O(n log n) time | O(1) space

```java
package com.practise;

import java.util.Arrays;

public class VendorPlacement {

    // Greedy check: can we place 'shopCount' shops with at least 'distance' apart?
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
// To find the MAXIMUM such distance, wrap this in a binary search (same as AggressiveCows)
```

---

### 41. Email Regex Validator

**DSA Pattern:** `String Pattern Matching — Regular Expression (Finite Automaton)`

**How we identified it:**
Email validation requires checking a complex structural rule (local part @ domain . TLD). A hand-written parser would be verbose and error-prone. A **compiled regex** pattern is the right tool: Java's `Pattern.compile()` builds a finite automaton once, and `Matcher.matches()` runs it in O(n) per string. We identified regex over manual parsing because: (1) the rule has well-defined structure expressible in regex syntax, (2) the compiled pattern reuses across multiple emails efficiently, (3) regex is the standard DSA for fixed-grammar string matching. The `static final Pattern` ensures compilation happens only once.

**Complexity:** O(n) per email match after O(pattern_length) compilation | O(1) extra space

```java
package com.practise;

import java.util.regex.*;

public class RegexExpr {

    private static final String EMAIL_REGEX =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // Compiled once — reused for every call (finite automaton built at startup)
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return pattern.matcher(email).matches();
    }
}
// "example@example.com" → true
// "invalid.email.com"   → false  (no @)
// "invalid@.com"        → false  (domain starts with .)
```

**Regex breakdown:**
| Part | Meaning |
|---|---|
| `^[_A-Za-z0-9-\\+]+` | Local part: letters, digits, `_`, `-`, `+` |
| `(\\.[_A-Za-z0-9-]+)*` | Optional dotted sub-parts in local |
| `@` | Separator |
| `[A-Za-z0-9-]+` | Domain name |
| `(\\.[A-Za-z0-9]+)*` | Optional subdomains |
| `(\\.[A-Za-z]{2,})$` | TLD — at least 2 letters |

---

## QUICK COMPLEXITY REFERENCE

| Algorithm | Pattern | Time (avg) | Time (worst) | Space |
|---|---|---|---|---|
| Fibonacci (naive) | DP — Overlapping subproblems | O(2ⁿ) | O(2ⁿ) | O(n) |
| Fibonacci (memo/tab) | DP — Overlapping subproblems | O(n) | O(n) | O(n) |
| Coin Change | DP — Unbounded Knapsack | O(A·C) | O(A·C) | O(A) |
| Longest Palindrome | DP — Interval DP | O(n²) | O(n²) | O(n²) |
| 0/1 Knapsack | DP — 0/1 Knapsack | O(N·W) | O(N·W) | O(N·W) |
| Egg Dropping | DP — Min-Max DP | O(E·F²) | O(E·F²) | O(E·F) |
| Binary Search | Divide & Conquer | O(log n) | O(log n) | O(log n) |
| Towers of Hanoi | Recursion — D&C | O(2ⁿ) | O(2ⁿ) | O(n) |
| Factorial | Recursion — Linear | O(n) | O(n) | O(n) |
| Euclidean GCD | Recursion — Math | O(log n) | O(log n) | O(log n) |
| Sliding Window (fixed) | Sliding Window | O(n) | O(n) | O(1) |
| Sliding Window (variable) | Sliding Window | O(n) | O(n) | O(k) |
| Merge Intervals | Sort + Greedy | O(n log n) | O(n log n) | O(n) |
| Interval Intersection | Two Pointers | O(n log n) | O(n log n) | O(n) |
| Max Non-Overlapping | Greedy (Activity Selection) | O(n log n) | O(n log n) | O(n) |
| Min Meeting Rooms | Greedy + Min-Heap | O(n log n) | O(n log n) | O(n) |
| Graph BFS / DFS | Graph Traversal | O(V+E) | O(V+E) | O(V) |
| Graph Coloring | Backtracking | O(mᵛ) | O(mᵛ) | O(v) |
| Hamiltonian Path | Backtracking | O(n!) | O(n!) | O(n) |
| Quickselect | D&C — Partition | O(n) | O(n²) | O(1) |
| Bin Packing FFD | Greedy (Approx) | O(n²) | O(n²) | O(n) |
| Closest Pair of Points | D&C — Geometric | O(n log n) | O(n log n) | O(n) |
| Anagram Detection | Sorting — Canonical | O(n log n) | O(n log n) | O(n) |
| Aggressive Cows | Binary Search on Answer | O(n log n) | O(n log n) | O(1) |
| Array Reverse | Two Pointers | O(n) | O(n) | O(1) |
| Trapping Rain Water (two-pointer) | Two Pointers | O(n) | O(n) | O(1) |
| Trapping Rain Water (prefix) | Prefix Sum | O(n) | O(n) | O(n) |
| Rotting Oranges | BFS — Multi-source | O(V+E) | O(V+E) | O(V) |
| Knight Moves | BFS — Shortest Path | O(N²) | O(N²) | O(N²) |
| Max Stack peekMax | Stack + Auxiliary | O(1) | O(1) | O(n) |
| Max Stack popMax | Stack + Auxiliary | O(n) | O(n) | O(n) |
| Calendar Booking | Ordered Map (TreeMap) | O(log n) | O(log n) | O(n) |
| Group Anagrams | Hashing — Canonical | O(n·k log k) | O(n·k log k) | O(n) |
| Stock Price (heap) | Heap + Lazy Deletion | O(log n) | O(log n) | O(n) |
| Stock Data Structure | Ordered Map (TreeMap) | O(log n) | O(log n) | O(n) |
| Large File Sort | D&C + Heap (External) | O(n log n) | O(n log n) | O(chunk) |
| Large File Sorter | D&C + Concurrency | O(n log n / p) | O(n log n) | O(chunk×p) |
| Monster Game | Simulation | O(turns×m) | O(turns×m) | O(1) |
| Vendor Placement | Binary Search on Answer | O(n log n) | O(n log n) | O(1) |
| Email Regex | String Pattern Matching | O(n) | O(n) | O(1) |
