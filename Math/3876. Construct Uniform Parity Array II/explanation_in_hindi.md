### 1. Minimum element find karna

``` java
int min = Integer.MAX_VALUE;

for (int i = 0; i < nums1.length; i++) {
    min = Math.min(nums1[i], min);
}
```

Sabse pehle hum minimum element find karte hain.

Minimum important hai kyunki:

-   Agar minimum odd hai → hum all elements ko odd bana sakte hain.
-   Agar minimum even hai → minimum khud change nahi ho sakta, isliye wo
    even hi rahega.

### 2. Minimum odd hai

``` java
if (min % 2 != 0) return true;
```

Agar minimum odd hai, to:

-   Odd elements ko same rakh sakte hain.
-   Even elements se minimum odd number subtract karke odd bana sakte
    hain.

For example:

``` text
8 - 3 = 5
6 - 3 = 3
```

Dono odd ho gaye.

Isliye answer directly `true` hai.

### 3. Minimum even hai

Agar minimum even hai, to uske paas subtract karne ke liye koi smaller
element nahi hai.

Isliye minimum **even hi rahega**.

Ab humein check karna hai ki koi odd element to nahi hai:

``` java
for (int i = 0; i < nums1.length; i++) {
    if (nums1[i] % 2 != 0) return false;
}
```

Agar ek bhi odd element mil gaya, to answer `false`.

Kyunki smallest odd element ko even banana possible nahi hoga.

### 4. Saare elements even hain

Agar loop complete ho gaya aur koi odd element nahi mila:

``` java
return true;
```

Matlab saare elements already even hain.

Hum original array ko hi `nums2` ke roop mein use kar sakte hain.

------------------------------------------------------------------------

## ⏱️ Complexity

### Time Complexity

**O(n)**

Hum maximum do baar array traverse karte hain:

``` text
First traversal  -> minimum find karna
Second traversal -> odd element check karna
```

So:

``` text
O(n) + O(n) = O(n)
```

Constants ignore karne par final complexity:

``` text
O(n)
```

### Space Complexity

**O(1)**

Hum sirf ek extra variable `min` use kar rahe hain.

Koi extra array ya data structure nahi banaya.

------------------------------------------------------------------------

## ⚠️ Edge Cases

### 1. Sirf ek element

``` text
[7]
```

Minimum odd hai → `true`.

``` text
[4]
```

Minimum even hai aur saare elements even hain → `true`.

### 2. Minimum odd aur mixed parity

``` text
[3, 4, 8]
```

Answer: `true`.

Kyunki:

``` text
4 - 3 = 1
8 - 3 = 5
```

Ab sab odd hain.

### 3. Minimum even aur ek odd element

``` text
[2, 3, 8]
```

Answer: `false`.

`2` ko change nahi kar sakte aur `3` ko even nahi bana sakte.

------------------------------------------------------------------------

## 🎯 Interview Takeaway

### Key Observation

Minimum element ki parity poore problem ka answer decide karti hai.

``` text
Minimum odd  -> true
Minimum even -> true only if all elements are even
```

### One-line Approach

> Minimum element find karo. Agar minimum odd hai to `true` return karo;
> agar minimum even hai to check karo ki saare elements even hain ya
> nahi.

### Important Parity Rules

``` text
even - odd = odd
odd - even = odd
odd - odd = even
even - even = even
```

Sabse important point:

> Jab minimum even hota hai, smallest odd element ke paas usse chhota
> koi odd element nahi hota. Isliye usko even banana impossible hai.
