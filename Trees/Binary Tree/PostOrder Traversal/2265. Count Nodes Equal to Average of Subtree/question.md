# 2265. Count Nodes Equal to Average of Subtree

[**2265. Count Nodes Equal to Average of Subtree**](https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/)

**Difficulty:** Medium

## Problem Statement

Given the `root` of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

## Note

- The average of `n` elements is the sum of the `n` elements divided by `n` and rounded down to the nearest integer.
- A subtree of `root` is a tree consisting of `root` and all of its descendants.

## Example 1

**Input:**

root =
[
    4,
    8,
    5,
    0,
    1,
    null,
    6
]

**Output:**

5

**Explanation:**

For the node with value 4:

(4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4

For the node with value 5:

(5 + 6) / 2 = 11 / 2 = 5

For the node with value 0:

0 / 1 = 0

For the node with value 1:

1 / 1 = 1

For the node with value 6:

6 / 1 = 6

So there are 5 nodes whose value is equal to the average of their subtree.

## Example 2

**Input:**

root =
[
    1
]

**Output:**

1

**Explanation:**

For the node with value 1:

1 / 1 = 1

Therefore, the answer is 1.

## Constraints

- The number of nodes in the tree is in the range `[1, 1000]`.
- `0 <= Node.val <= 1000`
