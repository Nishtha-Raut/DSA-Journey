# Explanation - Hinglish

## Topic

**Dynamic Programming → Subarray DP → Modulo DP**

## Intuition

Jab hum prefix aur suffix remove karte hain aur array ko non-empty rakhte hain, to jo bachta hai woh hamesha ek **non-empty contiguous subarray** hota hai.

Isliye problem ko hum aise soch sakte hain:

> Har non-empty contiguous subarray ka product `% k` karne par jo remainder aaye, uske according usko count karo.

Hume actual product calculate karne ki zarurat nahi hai.

Important property:

`(A * B) % k = ((A % k) * (B % k)) % k`

Matlab agar hume previous product ka remainder pata hai aur current element ka remainder pata hai, to new product ka remainder easily nikal sakte hain.

Aur `k <= 5` hai, isliye sirf `0` se `k-1` tak ke remainders ko store karna hai.

---

## DP Meaning

### `prevcount[r]`

Ye bahut important hai:

> **`prevcount[r]` batata hai ki `i-1` index par end hone wale kitne contiguous subarrays hain jinka product remainder `r` hai.**

Yaani `prevcount` mein **saare purane subarrays nahi hote**.

Sirf **immediately previous index par end hone wale subarrays** hote hain.

Aisa isliye kyunki current `i-th` element ko add karke contiguous subarray banane ke liye previous subarray ka `i-1` par end hona zaroori hai.

---

### `currcount[r]`

`currcount[r]` batata hai:

> **Current `i-th` element ko include karke `i-th` index par end hone wale kitne contiguous subarrays hain jinka product remainder `r` hai.**

---

## Approach

Har `nums[i]` ke liye 4 main steps hain.

### 1. Current element ka remainder nikalo

```text id="5n8q2c"
currentRemainder = nums[i] % k
```

Hum actual value ki jagah remainder use kar sakte hain because:

`(A * B) % k = ((A % k) * (B % k)) % k`

---

### 2. Current element se ek naya subarray banao

Current element khud ek valid subarray hai:

`[nums[i]]`

Isliye:

```text id="9v3m6x"
currcount[currentRemainder]++;
```

---

### 3. Previous subarrays ko current element ke saath extend karo

Maan lo `i-1` par end hone wale kisi subarray ka product remainder `j` hai.

Current element ka remainder `currentRemainder` hai.

To new product ka remainder:

```text id="4q7k1z"
newRemain = (j * currentRemainder) % k
```

Isliye:

```text id="2m6p8v"
currcount[newRemain] += prevcount[j];
```

Yahan hum **sirf `prevcount` use kar rahe hain**, kyunki usme wahi subarrays hain jo `i-1` par end ho rahe hain.

---

## Sirf Previous Index Wale Subarrays Hi Kyun?

Example:

```text id="w3r8ka"
nums = [1, 2, 3]
```

Jab `3` process kar rahe hain, valid contiguous subarrays jo `3` par end hote hain:

```text id="d5x1qm"
[3]
[2,3]
[1,2,3]
```

`[2,3]` banane ke liye `[2]` ko extend kiya.

`[1,2,3]` banane ke liye `[1,2]` ko extend kiya.

Dono previous index `1` par end hote hain.

Lekin `[1]` ko directly `3` ke saath combine nahi kar sakte:

```text id="k7p4zs"
[1,3]
```

because ye contiguous nahi hai.

Isi liye `prevcount` mein **sirf previous index par end hone wale subarrays** rakhte hain.

---

## 4. Answer Update Karo

Current index par end hone wale saare subarrays valid remaining arrays hain.

Isliye:

```text id="h2v6ny"
result[x] += currcount[x];
```

---

## 5. `prevcount = currcount`

Ye bahut important step hai:

```text id="p8s4wc"
prevcount = currcount;
```

Hum `+=` nahi karenge.

Kyun?

Ab next iteration mein current `i` previous index ban jayega.

Aur next element ko extend karne ke liye hume **sirf current `i` par end hone wale subarrays** chahiye.

Aur woh exactly `currcount` mein hain.

Isliye:

```text id="x5n9qa"
prevcount = currcount;
```

---

## Dry Run

`nums = [1,2,3]`

`k = 3`

### `i = 0`

Element:

`1`

Remainder:

`1`

New subarray:

`[1] → 1`

So:

`currcount[1] = 1`

Then:

`prevcount = currcount`

Ab `prevcount` mein sirf:

`[1]`

hai.

---

### `i = 1`

Element:

`2`

Remainder:

`2`

Naya subarray:

`[2] → 2`

Previous subarray:

`[1] → 1`

Usko `2` ke saath extend:

`(1 * 2) % 3 = 2`

So:

`[1,2] → 2`

Current subarrays:

```text id="b3m7kx"
[2]     → 2
[1,2]   → 2
```

Ab:

`prevcount = currcount`

To `prevcount` mein sirf ye dono hain.

---

### `i = 2`

Element:

`3`

Remainder:

`0`

Naya:

`[3] → 0`

Previous subarrays:

```text id="f6q2mz"
[2]     → 2
[1,2]   → 2
```

Inko `3` ke saath extend:

For `[2]`:

`(2 * 0) % 3 = 0`

So:

`[2,3] → 0`

For `[1,2]`:

`(2 * 0) % 3 = 0`

So:

`[1,2,3] → 0`

Current:

```text id="v8c4rn"
[3]       → 0
[2,3]     → 0
[1,2,3]   → 0
```

---

## Kyu Work Karta Hai?

Har contiguous subarray jo `i` par end hota hai, do possibilities mein se ek hota hai:

### Case 1: Sirf current element

`[nums[i]]`

Isko hum directly add karte hain.

### Case 2: Previous subarray + current element

Agar length greater than `1` hai, to woh kisi subarray ko `i-1` par end karke current element add karne se banega.

Isliye:

```text id="s7m3px"
prevcount → currcount
```

transition se **har contiguous subarray exactly once** generate hota hai.

---

## Modulo Property Kyu Use Kar Rahe Hain?

Suppose previous subarray ka actual product `P` hai.

Uska remainder:

`P % k = r`

Current element `x` hai.

New product:

`P * x`

Hoga.

Hume:

`(P * x) % k`

chahiye.

Modulo property se:

`(P * x) % k = ((P % k) * (x % k)) % k`

So:

`newRemain = (r * currentRemainder) % k`

Isliye actual product store karne ki koi zarurat nahi hai.

Sirf remainder store karna enough hai.

---

## Complexity

**Time Complexity:** `O(n * k)`

Har element ke liye `k` possible remainders check karte hain.

**Space Complexity:** `O(k)`

Sirf remainder counts ke arrays use hote hain.

Since `k <= 5`, ye practically `O(n)` hai.

---

## Edge Cases

1. **Single element**

   * Sirf ek subarray possible hai.

2. **`k = 1`**

   * Har product ka remainder `0` hoga.

3. **Current element ka remainder `0`**

   * Kisi bhi previous remainder `r` ke liye:
   * `(r * 0) % k = 0`

4. **Repeated elements**

   * Same remainder hone ke baad bhi different subarrays separately count honge.

5. **Large numbers**

   * Actual product calculate nahi karte.
   * Sirf `% k` calculate karte hain.

---

## Interview Takeaway

Ye 4 cheezein yaad rakho:

### 1. Pro
