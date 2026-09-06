# Explanation

## Intuition

A direct approach would generate every pair `(i, j)` and calculate `arr[i] & arr[j]`.

But there can be about `n² / 2` pairs. With `n = 10^5`, this would take `O(n²)` time and would be too slow.

The key observation is that bitwise AND works independently for every bit.

A particular bit is `1` in `a & b` only when that same bit is `1` in both `a` and `b`.

So instead of calculating every pair, we can calculate the contribution of each bit separately.

## Key Observation

Suppose a particular bit is set in `count` elements.

Any two of these `count` elements will have this bit set in their AND.

The number of pairs that can be formed is:

`count * (count - 1) / 2`

If the current bit has value `2^k`, then its total contribution is:

`number of pairs * 2^k`

We calculate this for every bit and add all contributions.

## Approach

1. Consider every bit position.
2. Count how many array elements have that bit set.
3. Calculate the number of pairs among those elements using:
   `count * (count - 1) / 2`
4. Calculate the value of the current bit.
5. Add:
   `pairs * bitValue`
   to the answer.
6. Return the final answer.

Since `arr[i] <= 10^8`, checking 31 bits is enough.

## Dry Run

For:

`arr = [5, 10, 15]`

Binary representation:

`5  = 0101`

`10 = 1010`

`15 = 1111`

### Bit 0

The bit is set in `5` and `15`.

Count = 2

Pairs:

`2 * 1 / 2 = 1`

Contribution:

`1 * 1 = 1`

### Bit 1

The bit is set in `10` and `15`.

Count = 2

Pairs = 1

Contribution:

`1 * 2 = 2`

### Bit 2

The bit is set in `5` and `15`.

Count = 2

Pairs = 1

Contribution:

`1 * 4 = 4`

### Bit 3

The bit is set in `10` and `15`.

Count = 2

Pairs = 1

Contribution:

`1 * 8 = 8`

Total:

`1 + 2 + 4 + 8 = 15`

Therefore, the answer is `15`.

## Why It Works

For any bit, the bit appears in the AND of a pair exactly when both numbers contain that bit.

If `count` numbers contain that bit, every combination of two of them contributes that bit.

There are exactly:

`count * (count - 1) / 2`

such pairs.

Therefore, calculating every bit's contribution independently gives exactly the same result as calculating the AND of every pair individually.

## Complexity

Let `k` be the number of bits checked.

- Time: `O(n * k)`
- Space: `O(1)`

Since `k = 31` is constant, the time complexity is effectively `O(n)`.

## Edge Cases

### One element

If the array contains only one element, there are no valid pairs.

For every bit, `count` can be at most 1, so:

`count * (count - 1) / 2 = 0`

Therefore, the answer is `0`.

### No common set bit

If no pair has a common set bit, every AND is `0`, so the answer is `0`.

### Large answer

Although every individual element is at most `10^8`, there can be many pairs.

Therefore, the answer can be larger than the range of `int`, so `long` should be used.

## Interview Takeaway

The important idea is:

"Instead of calculating every pair, count how many numbers contain each bit. If a bit appears in `count` numbers, it contributes to `count choose 2` pairs."

The formula to remember is:

`pairs = count * (count - 1) / 2`
