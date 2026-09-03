### 1. Find the minimum element

``` java
int min = Integer.MAX_VALUE;

for (int i = 0; i < nums1.length; i++) {
    min = Math.min(nums1[i], min);
}
```

We find the smallest element because its parity determines what is
possible.

### 2. Minimum is odd

``` java
if (min % 2 != 0) return true;
```

If the minimum is odd, every even element can subtract this minimum and
become odd.

Odd elements can simply remain unchanged.

Therefore, we can always construct an all-odd array.

### 3. Minimum is even

If the minimum is even, it cannot be changed because there is no smaller
element.

Therefore, it will remain even.

We then check whether every element is also even:

``` java
for (int i = 0; i < nums1.length; i++) {
    if (nums1[i] % 2 != 0) return false;
}
```

If we find even one odd element, construction is impossible.

### 4. All elements are even

If the loop finishes without finding an odd element:

``` java
return true;
```

The original array itself can be used as `nums2`.

------------------------------------------------------------------------

## ⏱️ Complexity

### Time Complexity

**O(n)**

We traverse the array once to find the minimum and, in the worst case,
once more to check the parity of every element.

So the total work is:

``` text
O(n) + O(n) = O(n)
```

### Space Complexity

**O(1)**

Only one extra variable, `min`, is used. We do not create another array.

------------------------------------------------------------------------

## ⚠️ Edge Cases

### 1. Single element

``` text
[7]
```

The minimum is odd, so the answer is `true`.

``` text
[4]
```

The minimum is even, but all elements are even, so the answer is also
`true`.

### 2. Minimum is odd with mixed parity

``` text
[3, 4, 8]
```

Answer: `true`.

Every even element can subtract `3` and become odd.

### 3. Minimum is even with an odd element

``` text
[2, 3, 8]
```

Answer: `false`.

The `2` must remain even, while `3` cannot be converted to even.

------------------------------------------------------------------------

## 🎯 Interview Takeaway

**Key observation:** The parity of the minimum element determines the
answer.

-   `min` is **odd** → always `true`.
-   `min` is **even** → possible only if **all elements are even**.

### One-line approach

> Find the minimum element. If it is odd, return `true`; otherwise,
> return `true` only when every element is even.

### Important Parity Rule

``` text
even - odd = odd
odd - even = odd
odd - odd = even
even - even = even
```

The important part is that when the minimum is even, the smallest odd
element has no smaller odd element to subtract from it, so it cannot
become even.
