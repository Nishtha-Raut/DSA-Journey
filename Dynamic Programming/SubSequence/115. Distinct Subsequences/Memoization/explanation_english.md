# Explanation

## Intuition

We need to count how many different ways we can form `t` as a subsequence of `s`.

At every position, we have two choices:

1. **Take** the current character from `s` if it matches the current character of `t`.
2. **Do not take** the current character from `s`.

This naturally gives us a recursive Dynamic Programming solution.

The state is represented by two indices:

- `i` → current position in `s`
- `j` → current position in `t`

So:

`solve(i, j)` = number of ways to form `t[j...]` using `s[i...]`.

## Approach

For every state `(i, j)`:

### Case 1: Characters Match

If:

`s[i] == t[j]`

we have two choices.

**Take:**

Use this character to match `t[j]`.

`solve(i + 1, j + 1)`

**Do Not Take:**

Ignore this character of `s` and try to match `t[j]` using the remaining characters.

`solve(i + 1, j)`

Therefore:

`solve(i, j) = solve(i + 1, j + 1) + solve(i + 1, j)`

### Case 2: Characters Do Not Match

If:

`s[i] != t[j]`

we cannot use `s[i]` to match `t[j]`.

So the only option is to skip it:

`solve(i, j) = solve(i + 1, j)`

## Base Cases

### Target is Completely Matched

If:

`j == t.length()`

we have successfully formed the entire target.

Therefore, there is exactly **1 valid way**.

Return `1`.

### Source is Completely Exhausted

If:

`i == s.length()`

but `t` is still not completely matched, then forming `t` is impossible.

Return `0`.

## Memoization

Without memoization, the same `(i, j)` states are calculated many times.

For example, different recursive paths can reach the same positions in both strings.

So we store the answer for every state `(i, j)` in a 2D memoization array.

`memo[i][j]` stores:

> Number of ways to form `t[j...]` using `s[i...]`.

If the state has already been calculated, we directly return its stored result.

## Dry Run

Consider:

`s = "babgbag"`

`t = "bag"`

We want to form `"bag"` from `"babgbag"`.

At the beginning:

`i = 0` → `s[i] = 'b'`

`j = 0` → `t[j] = 'b'`

The characters match, so we have two choices:

- Take this `b`
- Skip this `b`

Both choices can potentially lead to `"bag"`.

This branching continues whenever the characters match.

The different valid selections eventually produce `5` distinct ways.

The important idea is that we count **different choices of indices**, even when the resulting characters are the same.

## Why Do We Add Take and Non-Take?

Suppose the current characters match.

Every valid subsequence using the current character belongs to the **take** group.

Every valid subsequence not using the current character belongs to the **non-take** group.

These two groups are separate, so we can safely add their counts.

Therefore:

`answer = take + nonTake`

## Complexity

Let:

- `m = s.length()`
- `n = t.length()`

There are at most `m × n` different `(i, j)` states.

### Time Complexity

**O(m × n)**

Each state is calculated only once.

### Space Complexity

**O(m × n)** for the memoization table.

There is also **O(m + n)** recursion stack space in the worst case.

## Edge Cases

- If `t` is completely matched, return `1`.
- If `s` is exhausted before matching all of `t`, return `0`.
- If `s` and `t` are identical, there is exactly one way.
- If `t` is longer than `s`, the answer is `0`.
- Repeated characters in `s` can create multiple distinct subsequences.

## Important Implementation Note

The recursive state depends on **both `i` and `j`**.

Therefore, a one-dimensional memoization array indexed only by `i` is not sufficient.

We need:

`memo[i][j]`

because the answer for the same `i` can be different depending on which character of `t` we are currently trying to match.

## Interview Takeaway

This is a classic **2D DP / subsequence counting** problem.

At every matching character, we either:

- take it and move both pointers, or
- skip it and move only the pointer in `s`.

The recurrence is:

`dp[i][j] = dp[i+1][j+1] + dp[i+1][j]`

when the characters match.

### One-Line Interview Explanation

> I use 2D memoization where `memo[i][j]` represents the number of ways to form the remaining target from the remaining source, and whenever the characters match, I add the take and non-take possibilities.
