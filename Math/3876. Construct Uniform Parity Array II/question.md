# 3876. Construct Uniform Parity Array II

[LeetCode
Problem](https://leetcode.com/problems/construct-uniform-parity-array-ii/)

## 📌 Problem Statement

You are given an array `nums1` of `n` **distinct** integers.

You want to construct another array `nums2` of length `n` such that the
elements in `nums2` are either **all odd or all even**.

For each index `i`, you must choose exactly one of the following (in any
order):

-   `nums2[i] = nums1[i]`
-   `nums2[i] = nums1[i] - nums1[j]`, for an index `j != i`, such that
    `nums1[i] - nums1[j] >= 1`

Return `true` if it is possible to construct such an array, otherwise
return `false`.

------------------------------------------------------------------------

## 🧪 Examples

### Example 1

**Input:**

``` text
nums1 = [1,4,7]
```

**Output:**

``` text
true
```

**Explanation:** - `nums2[0] = nums1[0] = 1` -
`nums2[1] = nums1[1] - nums1[0] = 4 - 1 = 3` - `nums2[2] = nums1[2] = 7`

So `nums2 = [1,3,7]`, and all elements are odd.

------------------------------------------------------------------------

### Example 2

**Input:**

``` text
nums1 = [2,3]
```

**Output:**

``` text
false
```

**Explanation:**

It is not possible to construct `nums2` such that all elements have the
same parity.

------------------------------------------------------------------------

### Example 3

**Input:**

``` text
nums1 = [4,6]
```

**Output:**

``` text
true
```

**Explanation:**

We can keep both elements unchanged:

``` text
nums2 = [4,6]
```

Both elements are even.

------------------------------------------------------------------------

## 📋 Constraints

-   `1 <= n == nums1.length <= 10^5`
-   `1 <= nums1[i] <= 10^9`
-   `nums1` consists of distinct integers.
