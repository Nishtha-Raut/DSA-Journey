# Explanation

## Intuition

We need to find two non-overlapping subarrays whose sum is exactly `target`, while minimizing their total length.

Since all elements are positive, we can use a **sliding window** to find target-sum subarrays.

We use two arrays:

* `prefixmin[i]` = minimum length of a target-sum subarray completely within `0...i`.
* `suffixmin[i]` = minimum length of a target-sum subarray completely within `i...n-1`.

Then we try every split between `i` and `i + 1`.

If both sides have a valid subarray:

`prefixmin[i] + suffixmin[i + 1]`

is a valid answer because the two subarrays are guaranteed to be non-overlapping.

## Approach

### Step 1: Build `prefixmin`

Traverse from left to right using a sliding window.

Maintain:

* `psum` = current window sum
* `j` = left boundary
* `prelen` = shortest target-sum subarray found so far

If `psum > target`, move `j` forward until the sum is at most `target`.

When `psum == target`, calculate:

`i - j + 1`

and update `prelen`.

Store:

`prefixmin[i] = prelen`

So `prefixmin[i]` represents the best valid subarray found up to index `i`.

### Step 2: Build `suffixmin`

Traverse from right to left.

Maintain:

* `ssum` = current window sum
* `j` = right boundary
* `suflen` = shortest target-sum subarray found so far

If `ssum > target`, move `j` backward.

When `ssum == target`, calculate:

`j - i + 1`

and update `suflen`.

Store:

`suffixmin[i] = suflen`

So `suffixmin[i]` represents the best valid subarray from index `i` onward.

### Step 3: Combine Both Sides

For every split between `i` and `i + 1`:

* Left side → `prefixmin[i]`
* Right side → `suffixmin[i + 1]`

If both exist, calculate their sum and minimize the answer.

If no valid pair exists, return `-1`.

## Why It Works

Any two non-overlapping subarrays have a split point between them.

For that split:

* `prefixmin[i]` gives the shortest valid subarray on the left.
* `suffixmin[i + 1]` gives the shortest valid subarray on the right.

Checking every split means every possible arrangement of two non-overlapping subarrays is covered.

Because all values are positive, the sliding window correctly finds target-sum subarrays in linear time.

## Dry Run

For:

`arr = [7,3,4,7]`

`target = 7`

Valid subarrays are:

* `[7]` → length `1`
* `[3,4]` → length `2`
* `[7]` → length `1`

Using the split after the first element:

* `prefixmin[0] = 1`
* `suffixmin[1] = 1`

Therefore:

`1 + 1 = 2`

So the answer is `2`.

## Complexity

**Time:** `O(n)`

There are two sliding-window passes and one pass to combine the results.

**Space:** `O(n)`

We store `prefixmin` and `suffixmin`.

## Edge Cases

* Only one target-sum subarray exists → return `-1`.
* Valid subarrays overlap → they cannot be selected together.
* Multiple valid subarrays exist → choose the pair with minimum total length.
* Two single-element target-sum subarrays exist → answer can be `2`.
* No target-sum subarray exists → return `-1`.

## Interview Takeaway

The key combination is:

**Sliding Window + Prefix/Suffix Minimum + Split Point**

Instead of comparing every pair of subarrays, preprocess the best subarray on the left and right of every split. This reduces the solution to `O(n)`.
