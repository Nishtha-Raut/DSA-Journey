# Explanation

## Topic

**Dynamic Programming → Subarray DP → Modulo DP**

## Intuition

Removing a prefix and a suffix while keeping the array non-empty always leaves a **non-empty contiguous subarray**.

So the problem becomes:

> Count every non-empty contiguous subarray according to the remainder of its product when divided by `k`.

We do not need to calculate the complete product of a subarray.

The important modulo property is:

`(A * B) % k = ((A % k) * (B % k)) % k`

So if we know the remainder of the previous subarray's product and the remainder of the current element, we can calculate the new remainder directly.

Since `k <= 5`, there are only `k` possible remainders: `0` to `k - 1`.

---

## DP Meaning

### `prevcount[r]`

`prevcount[r]` means:

> **How many contiguous subarrays ending at index `i - 1` have product remainder `r`?**

This is very important.

It does **not** contain all previous subarrays.

It contains only the subarrays that end at the **immediately previous index**.

Why?

Because when we add `nums[i]`, only a subarray ending at `i - 1` can be extended to form a contiguous subarray ending at `i`.

---

### `currcount[r]`

`currcount[r]` means:

> **How many contiguous subarrays ending at the current index `i` have product remainder `r`?**

So after processing the current element, we do:

`prevcount = currcount`

because in the next iteration, the current index becomes the previous index.

---

## Approach

For every element `nums[i]`, perform these steps.

### 1. Calculate the current remainder

```text id="7n4k2p"
currentRemainder = nums[i] % k
```

Only the remainder is needed because:

`(A * B) % k = ((A % k) * (B % k)) % k`

---

### 2. Start a new subarray

The current element itself forms a valid subarray:

`[nums[i]]`

Its product is simply `nums[i]`.

Therefore:

```text id="2m8q6x"
currcount[currentRemainder]++;
```

---

### 3. Extend previous subarrays

Now take every subarray that ended at `i - 1`.

Suppose its product remainder is `j`.

When we append `nums[i]`, whose remainder is `currentRemainder`, the new product remainder becomes:

```text id="5v3p9a"
newRemain = (j * currentRemainder) % k
```

So:

```text id="1c7r4m"
currcount[newRemain] += prevcount[j];
```

This creates all longer contiguous subarrays ending at `i`.

---

## Why Only `prevcount` Is Used?

Consider:

```text
nums = [1, 2, 3]
```

When we are processing `3`, the valid contiguous subarrays ending at `3` are:

```text
[3]
[2,3]
[1,2,3]
```

To create:

* `[2,3]`, we extend `[2]`.
* `[1,2,3]`, we extend `[1,2]`.

Both `[2]` and `[1,2]` end at the previous index.

But `[1]` does **not** end at the previous index.

If we used all previously found subarrays, we could incorrectly create:

`[1,3]`

which is not contiguous.

Therefore, `prevcount` must contain only subarrays ending at `i - 1`.

---

## 4. Add Current Counts to the Answer

Every non-empty contiguous subarray represents exactly one possible remaining array after removing a prefix and suffix.

Therefore:

```text id="8q1m5z"
result[x] += currcount[x];
```

We add all subarrays ending at the current index to the final answer.

---

## 5. Move Current State to Previous State

After processing index `i`:

```text id="3f6k9w"
prevcount = currcount;
```

We **replace** `prevcount`; we do not add to it.

Why?

Because in the next iteration, we need only the subarrays ending at the current index `i`.

Those are exactly the subarrays stored in `currcount`.

---

## Dry Run

Consider:

`nums = [1, 2, 3]`

`k = 3`

### Step 1: `i = 0`

Current element:

`1`

Remainder:

`1 % 3 = 1`

New subarray:

`[1] → remainder 1`

So:

`currcount[1] = 1`

Then:

`prevcount = currcount`

Now `prevcount` represents subarrays ending at index `0`:

`[1]`

---

### Step 2: `i = 1`

Current element:

`2`

Remainder:

`2`

Start a new subarray:

`[2] → 2`

Extend previous subarray:

`[1]`

Its remainder is `1`.

Using:

`(1 * 2) % 3 = 2`

we get:

`[1,2] → 2`

Therefore:

```text id="4x9m2c"
[2]     → 2
[1,2]   → 2
```

So:

`currcount[2] = 2`

Then:

`prevcount = currcount`

Now `prevcount` represents only:

```text id="q8v1ka"
[2]
[1,2]
```

Both end at index `1`.

---

### Step 3: `i = 2`

Current element:

`3`

Remainder:

`0`

Start:

`[3] → 0`

Now extend previous subarrays:

For `[2]`:

`(2 * 0) % 3 = 0`

So:

`[2,3] → 0`

For `[1,2]`:

`(2 * 0) % 3 = 0`

So:

`[1,2,3] → 0`

Therefore:

```text id="n4s7bx"
[3]       → 0
[2,3]     → 0
[1,2,3]   → 0
```

All three are counted in `result[0]`.

---

## Why the DP Is Correct

Every non-empty contiguous subarray ending at index `i` has exactly two possibilities:

### Case 1: It contains only `nums[i]`

We create it using:

`currcount[currentRemainder]++`

### Case 2: It contains more than one element

Then it must have been formed by taking a contiguous subarray ending at `i - 1` and appending `nums[i]`.

We generate these using:

`currcount[newRemain] += prevcount[j]`

Therefore, every contiguous subarray ending at `i` is generated exactly once.

Since we add every `currcount` to `result`, every valid operation is counted exactly once.

---

## Why Modulo DP Works

Suppose a previous subarray has product `P` and:

`P % k = r`

Current element is `x`.

The new product is:

`P * x`

We only need:

`(P * x) % k`

Using the modulo property:

`(P * x) % k = ((P % k) * (x % k)) % k`

Therefore:

`newRemain = (r * (x % k)) % k`

We never need the actual product `P`.

This also prevents the product from becoming extremely large.

---

## Complexity

Let `n = nums.length`.

For every element, we check all `k` possible remainders.

**Time Complexity:** `O(n * k)`

**Space Complexity:** `O(k)`

Since `k <= 5`, the solution is effectively linear in the size of the array.

---

## Edge Cases

1. **Single element**

   * Only on
