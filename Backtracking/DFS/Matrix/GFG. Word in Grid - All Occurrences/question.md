# Word in Grid - All Occurrences

[**Word in Grid - All Occurrences**](https://www.geeksforgeeks.org/problems/find-the-string-in-grid0118/1)

**Difficulty:** Medium

## Problem Statement

Given a 2D grid `mat[][]` of size `n × m` consisting of characters and a string `word`, find all starting positions where the word occurs in the grid.

- The word can be formed from any cell by moving in any of the 8 directions (2 horizontal, 2 vertical, and 4 diagonal) in a straight line without changing direction.
- Each cell can be used at most once per occurrence.
- Return all unique starting coordinates in lexicographically smallest order.

## Examples

### Example 1

**Input:**

mat[][] =
[
    [a, b, a, b],
    [a, b, e, b],
    [e, b, e, b]
]

word = "abe"

**Output:**

[
    [0, 0],
    [0, 2],
    [1, 0]
]

**Explanation:**

From `(0,0)` we can find `"abe"` in right-down diagonal.

From `(0,2)` we can find `"abe"` in left-down diagonal.

From `(1,0)` we can find `"abe"` in horizontally right direction.

### Example 2

**Input:**

mat[][] =
[
    [G, E, E, K, S, F, O, R, G, E, E, K, S],
    [G, E, E, K, S, Q, U, I, Z, G, E, E, K],
    [I, D, E, Q, A, P, R, A, C, T, I, C, E]
]

word = "GEEKS"

**Output:**

[
    [0, 0],
    [0, 8],
    [1, 0]
]

**Explanation:**

From `(0,0)` we can find `"GEEKS"` horizontally right.

From `(0,8)` we can find `"GEEKS"` horizontally right.

From `(1,0)` we can find `"GEEKS"` horizontally right.

## Constraints

- `1 <= n <= m <= 50`
- `1 <= |word| <= 20`
