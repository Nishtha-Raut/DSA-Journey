# Explanation

## Intuition

For every current element `x`, the previous element in the subsequence must have an absolute difference of exactly `1`.

Therefore, the previous element can only be:

- `x - 1`
- `x + 1`

We use a HashMap to store the longest valid subsequence length ending with each value.

## Approach

For every element `x`:

1. Check `x - 1` in the HashMap.
2. Check `x + 1` in the HashMap.
3. Take the larger of the two lengths.
4. Add the current element to that subsequence.
5. Store the best length for `x`.
6. Update the overall maximum.

If neither `x - 1` nor `x + 1` exists, the current element starts a new subsequence of length `1`.

## Why This Works

Suppose the current value is `5`.

A valid previous value can only be `4` or `6` because:

`|5 - 4| = 1`

`|5 - 6| = 1`

So, if the longest valid subsequence ending at `4` has length `3`, adding `5` gives a valid subsequence of length `4`.

The same applies to `6`.

Therefore, we only need to check these two values instead of checking every previous element.

## Dry Run

For:

`arr = [10, 9, 4, 5, 4, 8, 6]`

| Current | Previous Values Checked | Current Length |
|---------|-------------------------|----------------|
| 10 | 9, 11 | 1 |
| 9 | 8, 10 | 2 |
| 4 | 3, 5 | 1 |
| 5 | 4, 6 | 2 |
| 4 | 3, 5 | 3 |
| 8 | 7, 9 | 3 |
| 6 | 5, 7 | 3 |

Maximum length = `3`.

## Time Complexity

**O(n) average**

Each element performs a constant number of HashMap operations.

## Space Complexity

**O(n)**

The HashMap can contain up to `n` different values.

## Edge Cases

- Single element → answer is `1`.
- All elements are equal → answer is `1`.
- No two usable values differ by `1` → answer is `1`.
- Repeated values can be part of the subsequence if consecutive selected values differ by `1`.

## Interview Takeaway

The key observation is that for every value `x`, only `x - 1` and `x + 1` can be its previous element.

### One-Line Interview Explanation

I use a HashMap where each value stores the longest valid subsequence ending at that value. For every current value `x`, I check `x - 1` and `x + 1`, extend the better subsequence, and maintain the maximum length.
