# 🐦 Bird and Max Fruit Gathering — Explanation

## Intuition

The important observation is that the bird can start from **any tree** and can only move to neighboring trees.

Therefore, the trees visited by the bird will always form a **contiguous segment** of the circular array.

So the problem becomes:

> Find the maximum sum of a subarray of length `m` in a circular array.

A normal sliding window can find the maximum sum of a fixed-size subarray in `O(N)` time.

The only extra challenge is the **circular nature** of the array.

For example:

```text
[7, 2, 1, 3, 4]
```

A valid window can cross the boundary:

```text
[4, 7]
```

We can handle this using modulo indexing.

---

## Approach

1. If `m >= n`, the bird can visit every tree, so return the sum of the entire array.
2. Calculate the sum of the first `m` elements.
3. Treat these `m` elements as the initial sliding window.
4. Move the window one position at a time.
5. Remove the element leaving the window.
6. Add the new element entering the window using:

   ```text
   (i + m - 1) % n
   ```
7. Keep track of the maximum window sum.
8. Return the maximum sum found.

Using modulo allows the window to wrap around from the last tree back to the first tree.

---

## Complexity

* **Time Complexity:** `O(N)`

  * We calculate the initial window in `O(m)`.
  * Then we slide the window across all `N` possible starting positions.

* **Space Complexity:** `O(1)`

  * Only a few variables are used apart from the input array.
