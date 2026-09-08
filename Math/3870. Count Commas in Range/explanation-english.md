# Explanation

## Intuition

A comma is needed only for numbers having at least 4 digits.

Since `n <= 10^5`, every number from `1` to `999` has no comma.

Starting from `1000`, every number up to `n` has exactly one comma.

So we only need to count how many numbers are present from `1000` to `n`.

## Approach

- If `n < 1000`, no number contains a comma, so return `0`.
- Otherwise, the numbers containing one comma are:
  `1000, 1001, ..., n`
- The number of such numbers is:

  `n - 1000 + 1`

  which simplifies to:

  `n - 999`

Therefore, return `n - 999`.

## Dry Run

For `n = 1002`:

Numbers containing commas:

`1000, 1001, 1002`

Count:

`1002 - 999 = 3`

So the answer is `3`.

For `n = 998`:

Since `998 < 1000`, return `0`.

## Why It Works

Every number from `1` to `999` has fewer than four digits, so it contributes zero commas.

Every number from `1000` to `100000` has exactly one comma because the maximum value of `n` is `10^5`.

Therefore, counting the numbers from `1000` to `n` directly gives the total number of commas.

## Complexity

- Time: `O(1)`
- Space: `O(1)`

## Edge Cases

- `n = 1` → `0`
- `n = 999` → `0`
- `n = 1000` → `1`
- `n = 1002` → `3`

## Interview Takeaway

"The key observation is that because `n <= 10^5`, only numbers from `1000` onward contain commas, and each of them contains exactly one comma. Therefore, the answer is simply `n - 999` when `n >= 1000`."
