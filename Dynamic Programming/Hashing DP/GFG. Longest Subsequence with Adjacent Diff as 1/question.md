# Longest Subsequence with Adjacent Diff as 1

Given an array `arr[]` with `n` elements, find the longest subsequence such that the absolute difference between adjacent elements is `1`.

## Examples

### Example 1

Input:
`arr[] = [10, 9, 4, 5, 4, 8, 6]`

Output:
`3`

Explanation:
The longest subsequences with adjacent difference `1` are:

- `[10, 9, 8]`
- `[4, 5, 4]`
- `[4, 5, 6]`

Therefore, the answer is `3`.

### Example 2

Input:
`arr[] = [1, 2, 3, 2, 3, 7, 2, 1]`

Output:
`7`

Explanation:
The longest valid subsequence is:

`[1, 2, 3, 2, 3, 2, 1]`

Therefore, the answer is `7`.

## Constraints

- `1 ≤ arr.size() ≤ 10^5`
- `1 ≤ arr[i] ≤ 10^6`
