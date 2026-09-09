# Explanation

## Intuition

In the previous problem, `n` was small enough that every number containing a comma had exactly one comma.

Here, `n` can be as large as `10^15`, so some numbers can contain multiple commas.

For example:

- `1,000` → 1 comma
- `1,000,000` → 2 commas
- `1,000,000,000` → 3 commas

So we need to count how many numbers contribute at each comma position.

## Key Observation

A number gets its first comma when it reaches `1,000`.

Therefore, all numbers from:

`1,000` to `n`

contribute at least 1 comma.

A number gets a second comma when it reaches:

`1,000,000`

So all numbers from:

`1,000,000` to `n`

contribute one additional comma.

Similarly:

- From `1,000` → contributes 1 comma
- From `1,000,000` → contributes another comma
- From `1,000,000,000` → contributes another comma
- From `1,000,000,000,000` → contributes another comma

Thus, for every power of `1000`, we count the numbers from that threshold to `n`.

## Approach

First, if:

`n < 1000`

then no number contains a comma, so return `0`.

Otherwise:

1. Find how many comma levels exist up to `n`.
2. Start with `999`, because `1000` is the first number containing a comma.
3. For every comma level:
   - Add `n - tosubtract` to the answer.
   - Move the threshold to the next power of `1000`.
4. Return the answer.

The threshold sequence is:

`999`

`999999`

`999999999`

`999999999999`

and so on.

For each threshold `x`, the number of integers from `x + 1` to `n` is:

`n - x`

## Dry Run

Take:

`n = 1,000,002`

Numbers from `1000` onward have at least one comma.

First contribution:

`1,000,002 - 999 = 999,003`

Numbers from `1,000,000` onward have a second comma.

Second contribution:

`1,000,002 - 999,999 = 3`

Total:

`999,003 + 3 = 999,006`

So the answer is:

`999006`

## Why It Works

Instead of counting commas number by number, we count the contribution of each comma position.

Every number from `1000` onward contributes one comma.

Every number from `1,000,000` onward contributes one additional comma.

Every number from `1,000,000,000` onward contributes another additional comma.

Therefore, summing:

`n - 999`

`n - 999999`

`n - 999999999`

...

for every threshold up to `n` gives exactly the total number of commas.

## Complexity

There are only `O(log₁₀₀₀ n)` comma levels.

Since `n <= 10^15`, there are only a few iterations.

- Time: `O(log₁₀₀₀ n)`
- Space: `O(1)`

## Edge Cases

### n < 1000

No number contains a comma.

Example:

`n = 998`

Answer = `0`

### n = 1000

Only `1,000` contains a comma.

Answer = `1`

### n = 999999

Numbers from `1000` to `999999` have one comma.

Answer:

`999999 - 999 = 999000`

### n = 1000000

The number `1,000,000` has two commas, so it contributes to both comma levels.

## Interview Takeaway

"The key idea is to count the contribution of each comma position separately. Numbers from `1000` onward contribute one comma, numbers from `10^6` onward contribute another, numbers from `10^9` onward contribute another, and so on. Therefore, for every threshold `1000^k`, I add the count of numbers from that threshold to `n`."
