# Approach

## 💡 Intuition

For every index `i`, we need to calculate:

```text
max(nums[0..i]) - min(nums[i..n-1])
```

So, for each index, we need two values:

* The **maximum element on the left**, including the current index.
* The **minimum element on the right**, including the current index.

Instead of calculating these values separately for every index, we can precompute the minimum values from the right and maintain the maximum values from the left.

This allows us to check every index efficiently.

---

## 🔍 Approach

### 1. Calculate the Suffix Minimum

Create a `rightmin` array where:

```text
rightmin[i] = minimum element from index i to n-1
```

We calculate it by traversing the array from right to left.

For example:

```text
nums = [5, 0, 1, 4]

rightmin = [0, 0, 1, 4]
```

---

### 2. Maintain the Prefix Maximum

Now traverse the array from left to right.

Maintain a variable `leftmax`:

```text
leftmax = maximum element from index 0 to i
```

At each index:

```text
leftmax = max(leftmax, nums[i])
```

---

### 3. Check the Stability Condition

For every index, calculate:

```text
instability = leftmax - rightmin[i]
```

If:

```text
instability <= k
```

the index is stable.

Since we traverse from left to right, the first stable index is automatically the **smallest stable index**.

If no index satisfies the condition, return `-1`.

---

## ⏱️ Complexity Analysis

### Time Complexity

**O(n)**

We traverse the array twice:

* Once to calculate the suffix minimum.
* Once to calculate the prefix maximum and find the stable index.

Therefore, the total time complexity is:

```text
O(n)
```

### Space Complexity

**O(n)**

We use the `rightmin` array of size `n`.

```text
Space = O(n)
```
