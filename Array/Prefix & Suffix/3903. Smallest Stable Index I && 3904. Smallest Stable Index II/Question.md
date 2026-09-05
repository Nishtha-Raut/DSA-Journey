# 3903. Smallest Stable Index I

**LeetCode:** [3903. Smallest Stable Index I](https://leetcode.com/problems/smallest-stable-index-i/)
**Difficulty:** Easy

## Problem Statement

You are given an integer array `nums` of length `n` and an integer `k`.

For each index `i`, define its **instability score** as:

```text
max(nums[0..i]) - min(nums[i..n - 1])
```

In other words:

* `max(nums[0..i])` is the largest value among the elements from index `0` to `i`.
* `min(nums[i..n - 1])` is the smallest value among the elements from index `i` to `n - 1`.

An index `i` is called **stable** if its instability score is **less than or equal to** `k`.

Return the **smallest stable index**. If no such index exists, return `-1`.

---

## Examples

### Example 1

**Input:**

```text
nums = [5,0,1,4]
k = 3
```

**Output:**

```text
3
```

**Explanation:**

* At index `0`: `5 - 0 = 5`
* At index `1`: `5 - 0 = 5`
* At index `2`: `5 - 1 = 4`
* At index `3`: `5 - 4 = 1`

The first index with instability score `<= 3` is `3`.

---

### Example 2

**Input:**

```text
nums = [3,2,1]
k = 1
```

**Output:**

```text
-1
```

**Explanation:**

For every index, the instability score is `2`, which is greater than `k = 1`.

Therefore, there is no stable index.

---

### Example 3

**Input:**

```text
nums = [0]
k = 0
```

**Output:**

```text
0
```

**Explanation:**

At index `0`:

```text
0 - 0 = 0
```

Since `0 <= k`, index `0` is stable.

---

## Constraints

* `1 <= nums.length <= 100`
* `0 <= nums[i] <= 10^9`
* `0 <= k <= 10^9`
