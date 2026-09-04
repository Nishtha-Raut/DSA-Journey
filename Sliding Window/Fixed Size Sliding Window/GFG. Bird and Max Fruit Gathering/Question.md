# 🐦 Bird and Max Fruit Gathering

**Difficulty:** Easy
**Points:** 2

## Problem Statement

Given an array `arr[]` representing the fruit values of trees arranged in a **circle** and an integer `m`, find the **maximum total fruits** the bird can collect by visiting **at most `m` trees**.

### Rules

* The bird can start from any tree.
* The bird can move only to a neighboring tree.
* The first and last trees are also considered neighbors because the trees are arranged in a circle.
* The bird collects the fruit value of every tree it visits.

## Examples

### Example 1

```text
Input:
arr[] = [2, 1, 3, 5, 0, 1, 4]
m = 3

Output:
9
```

**Explanation:**

The bird can start from the second tree and visit:

```text
1 + 3 + 5 = 9
```

So the maximum total is `9`.

### Example 2

```text
Input:
arr[] = [1, 6, 2, 5, 3, 4]
m = 2

Output:
8
```

The maximum can be obtained from:

```text
6 + 2 = 8
```

or

```text
5 + 3 = 8
```

Therefore, the answer is `8`.

### Example 3

```text
Input:
arr[] = [7, 2, 1, 3, 4]
m = 2

Output:
11
```

The fifth and first trees are neighbors because the array is circular:

```text
4 + 7 = 11
```

Therefore, the maximum total is `11`.

## Constraints

```text
1 ≤ arr.size(), m ≤ 10^6
0 ≤ arr[i] ≤ 10^6
```
