# Intuition

For every index `i`, we need to check whether the **sum of digits of `nums[i]` is equal to `i`**.

We can calculate the digit sum using the standard digit extraction technique:

* `num % 10` gives the last digit.
* `num / 10` removes the last digit.

We check indices from left to right. The first index satisfying the condition is automatically the **smallest index**, so we can immediately return it.

# Approach

1. Traverse the array from index `0` to `nums.length - 1`.
2. For each `nums[i]`, calculate its digit sum.
3. Compare the digit sum with `i`.
4. If `sum == i`, return `i`.
5. If the loop finishes without finding a match, return `-1`.

## Why does returning immediately give the smallest index?

We traverse the array in increasing order:

`0 → 1 → 2 → 3 → ...`

Therefore, the first index satisfying the condition must be the smallest valid index.

# Dry Run

For:

`nums = [1,10,11]`

### i = 0

`nums[0] = 1`

Digit sum = `1`

`1 != 0`

Continue.

### i = 1

`nums[1] = 10`

Digit sum:

`1 + 0 = 1`

`1 == 1`

So return `1`.

We do not need to check further because we need the **smallest** valid index.

# Complexity

Let `d` be the maximum number of digits in an element.

* **Time:** `O(n × d)`
* **Space:** `O(1)`

Since `nums[i] <= 1000`, each number has at most 4 digits, so practically this is **O(n)**.

# Edge Cases

* If `nums[i] = 0`, its digit sum is `0`, so index `0` can satisfy the condition.
* Multiple indices may satisfy the condition; return the smallest one.
* If no index satisfies the condition, return `-1`.

# Interview Takeaway

This is a basic **digit manipulation** problem.

Remember:

`last digit = number % 10`

`remove last digit = number / 10`

Since we scan from left to right, the first matching index is automatically the smallest.
