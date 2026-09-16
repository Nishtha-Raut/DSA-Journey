# Explanation

## Intuition

We process the points from left to right.

At every point, there are two possible states:

- `draw = 0`: No segment is currently open.
- `draw = 1`: A segment is currently open.

The important observation is that when a segment is open, we have two choices:

1. Continue the current segment to the next point.
2. End the current segment at the current point.

Since segments are allowed to share endpoints, after ending a segment at point `i`, another segment can start from the same point `i`.

We use memoization because the same state can be reached through multiple paths.

## DP State

`solve(i, n, k, draw)` means:

- `i` = current point.
- `k` = number of segments still left to complete.
- `draw` = whether a segment is currently open.

So:

`memo[i][k][draw]`

stores the answer for that state.

## Base Cases

### When `k == 0`

All required segments have been completed.

Therefore, there is exactly one valid way to finish:

`return 1`

### When `i >= n`

We have no points left, but still need to create segments.

Therefore:

`return 0`

## When No Segment Is Open

If:

`draw == 0`

we have two choices.

### 1. Start a New Segment

We start a segment from the current point:

`solve(i + 1, n, k, 1)`

We move to the next point because a segment must contain at least two points.

### 2. Skip the Current Point

We do not use the current point:

`solve(i + 1, n, k, 0)`

Therefore:

`total = startNew + skip`

## When a Segment Is Open

If:

`draw == 1`

we again have two choices.

### 1. End the Current Segment

We end the segment at the current point:

`solve(i, n, k - 1, 0)`

Notice that we do not move to `i + 1`.

This is because segments are allowed to share endpoints.

For example:

`(0,1), (1,2)`

is valid.

The first segment ends at `1` and the second segment can start from `1`.

Since one segment is completed, `k` becomes `k - 1`.

### 2. Continue the Current Segment

We continue the segment to the next point:

`solve(i + 1, n, k, 1)`

Therefore:

`total = endAndStartNext + skip`

## Why Does Every Segment Have At Least Two Points?

When we start a segment at point `i`, the recursive call moves to `i + 1`.

Therefore, we cannot end the segment at the same point where it started.

The smallest possible segment is therefore something like:

`(0,1)`

which covers two points.

## Dry Run

Consider:

`n = 3, k = 1`

The points are:

`0   1   2`

Initially:

`solve(0, 3, 1, 0)`

No segment is open.

Suppose we start a segment at point `0`:

`solve(1, 3, 1, 1)`

Now the segment is open.

We can continue to point `2`:

`solve(2, 3, 1, 1)`

Then end the segment at point `2`:

`solve(2, 3, 0, 0)`

Since `k == 0`, this contributes `1`.

This represents:

`(0,2)`

Other choices generate:

`(0,1)`

and

`(1,2)`

Therefore, the answer is:

`3`

## Why Memoization?

Without memoization, the same `(i, k, draw)` state may be calculated many times.

We store the result in:

`memo[i][k][draw]`

If the same state occurs again, we directly return its stored result.

## Complexity

There are:

- `n` possible values of `i`
- `k + 1` possible values of `k`
- `2` possible values of `draw`

Therefore, the number of states is:

`O(n * k * 2) = O(nk)`

Each state performs constant work.

### Time Complexity

`O(n * k)`

### Space Complexity

Memoization table:

`O(n * k)`

Recursion stack:

`O(n)`

Overall:

`O(n * k)`

## Edge Cases

- If `k == 0`, all required segments are already completed.
- If `i >= n` while segments are still required, the state is impossible.
- Segments are allowed to share endpoints.
- Every segment must cover at least two points.
- The answer is calculated modulo `10^9 + 7`.

## Interview Takeaway

This is a **3-state memoization DP**.

The state tracks:

`current point + remaining segments + whether a segment is open`

The key transition is that an open segment can either continue to the next point or end at the current point, allowing another segment to start from the same endpoint.
