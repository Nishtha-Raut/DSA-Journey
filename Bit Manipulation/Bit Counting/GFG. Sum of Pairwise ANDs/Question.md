# Sum of Pairwise ANDs

[**Sum of Pairwise ANDs**](https://www.geeksforgeeks.org/practice-problems/)

**Difficulty:** Medium

## Problem Statement

Given an array `arr[]` of integers, calculate the sum of bitwise AND for all pairs of elements such that the first index is less than the second index.

In other words, for every pair `(i, j)` where `i < j`, calculate:

`arr[i] & arr[j]`

and return the sum of all these values.

## Examples

### Example 1

**Input:** `arr = [5, 10, 15]`

**Output:** `15`

**Explanation:**

Consider all pairs of elements where the first index is less than the second index (`i < j`).

The valid pairs are:

`(5, 10) -> 5 & 10 = 0`

`(5, 15) -> 5 & 15 = 5`

`(10, 15) -> 10 & 15 = 10`

Now, add all these results:

`0 + 5 + 10 = 15`

So, the total sum of bitwise ANDs for all such pairs is `15`.

### Example 2

**Input:** `arr = [10, 20, 30, 40]`

**Output:** `46`

**Explanation:**

Consider all pairs of elements where the first index is less than the second index (`i < j`).

The valid pairs are:

`(10, 20) -> 10 & 20 = 0`

`(10, 30) -> 10 & 30 = 10`

`(10, 40) -> 10 & 40 = 8`

`(20, 30) -> 20 & 30 = 20`

`(20, 40) -> 20 & 40 = 0`

`(30, 40) -> 30 & 40 = 8`

Now, add all these results:

`0 + 10 + 8 + 20 + 0 + 8 = 46`

So, the total sum of bitwise ANDs for all such pairs is `46`.

## Constraints

- `1 ≤ arr.size() ≤ 10^5`
- `1 ≤ arr[i] ≤ 10^8`
