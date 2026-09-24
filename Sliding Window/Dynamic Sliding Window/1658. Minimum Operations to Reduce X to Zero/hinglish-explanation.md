# Explanation - Hinglish

## Intuition

Initially lagta hai ki hume left aur right se elements remove karke different combinations try karne padenge.

Lekin isko ulta soch sakte hain.

Maan lo:

`totalSum = poore array ka sum`

Hume total array se kuch elements remove karke sum `x` banana hai.

Agar removed elements ka sum `x` hai, to jo elements bachenge unka sum hoga:

`totalSum - x`

Aur left aur right se elements remove karne ke baad jo part bachta hai, woh hamesha ek **contiguous middle subarray** hota hai.

Isliye:

> Hume `totalSum - x` sum wala **longest contiguous subarray** find karna hai.

Longest subarray isliye, kyunki jitne zyada elements bachenge, utne hi kam elements remove karne padenge.

So:

`minimum operations = n - longest subarray length`

---

## Target Sum

Code mein:

```text id="8r4p2k"
k = -x
```

Aur phir poore array ke elements add karta hai.

Finally:

```text id="5m7v1q"
k = totalSum - x
```

Yaani remaining subarray ka required sum `k` hai.

---

## Sliding Window

Array ke saare elements positive hain.

Isliye hum **variable-size sliding window** use kar sakte hain.

Maintain karenge:

* `j` → window ka left end
* `i` → window ka right end
* `sum` → current window ka sum

Har `i` par:

1. `nums[i]` ko window mein add karo.
2. Agar `sum > k`, to left se elements remove karo.
3. Jab `sum == k`, valid subarray mil gaya.
4. Uski maximum length store karo.

---

## `sum > k` Hone Par Window Kyun Shrink Karte Hain?

Ye positivity ki wajah se possible hai.

Sabhi elements positive hain.

Agar:

`sum > k`

to current window ko aur bada karne se sum aur increase hoga.

Hume sum ko `k` tak lana hai, isliye left se elements remove karna hi useful hai.

Jaise:

```text id="q6w1mz"
sum = 10
k = 6
```

Left element positive hai, to usko remove karne par sum decrease hoga.

Isliye sliding window safely shrink kar sakte hain.

---

## Dry Run

`nums = [1,1,4,2,3]`

`x = 5`

Total sum:

`11`

Required remaining sum:

`11 - 5 = 6`

Ab hume longest subarray with sum `6` find karna hai.

Array:

```text id="a7n3kp"
[1, 1, 4, 2, 3]
```

Subarray:

```text id="v9c5rx"
[1,1,4]
```

ka sum:

`1 + 1 + 4 = 6`

Length:

`3`

So hum 3 elements ko retain kar sakte hain.

Total elements:

`5`

Therefore:

`operations = 5 - 3 = 2`

Exactly answer `2`.

---

## Code Ko Samjho

### Total required sum

```text id="r3k8mz"
k = totalSum - x
```

Ye batata hai ki array mein kitna sum **bachna chahiye**.

### Current window

```text id="d5q1vx"
sum += nums[i]
```

Current element ko window mein add karte hain.

### Window shrink

```text id="f8m2za"
while(sum > k && j <= i) {
    sum -= nums[j];
    j++;
}
```

Agar sum target se bada hai, left se elements remove karte hain.

### Valid window

```text id="n6w4cy"
if(sum == k) {
    ans = Math.max(i - j + 1, ans);
}
```

Agar target sum mil gaya, current window ki length:

`i - j + 1`

hai.

Hum maximum length store karte hain.

### Final answer

```text id="p2v7sx"
nums.length - ans
```

Agar `ans` elements bachaye, to baaki:

`n - ans`

elements remove karne padenge.

---

## Kyu Work Karta Hai?

Suppose longest remaining subarray ka sum:

`totalSum - x`

hai.

To uske bahar ke elements ka sum:

`totalSum - (totalSum - x) = x`

hoga.

Yaani bahar ke saare elements ko left aur right se remove karke exactly `x` reduce kiya ja sakta hai.

Ab hume minimum operations chahiye.

Isliye hume maximum number of elements **bachane** hain.

Therefore:

`minimum operations = n - maximum remaining length`

Isi maximum length ko sliding window find karta hai.

---

## Edge Cases

### 1. `totalSum < x`

Required sum:

`totalSum - x`

negative hoga.

Lekin array ke saare elements positive hain, isliye koi subarray negative sum nahi bana sakta.

Answer:

`-1`

### 2. `totalSum == x`

Required remaining sum:

`0`

Lekin remaining array non-empty hona chahiye aur saare elements positive hain.

Isliye koi non-empty zero-sum subarray nahi hai.

Hume saare elements remove karne padenge.

Answer:

`n`

### 3. Required sum ka koi subarray nahi hai

`ans` `-1` hi rahega.

Return:

`-1`

### 4. Poora array required sum ke equal hai

Agar:

`totalSum - x = totalSum`

to `x = 0` hota, aur poora array retain karke `0` operations hote.

---

## Complexity

**Time Complexity:** `O(n)`

`i` aur `j` dono maximum `n` times move karte hain.

**Space Complexity:** `O(1)`

Extra array ya data structure nahi use hota.

---

## Interview Takeaway

Sabse important transformation yaad rakho:

```text id="y7m3qa"
Remove sum x
      ↓
Keep sum totalSum - x
      ↓
Find longest contiguous subarray
      ↓
Minimum operations = n - longest length
```

Aur kyunki `nums[i]` **positive** hain, hum variable-size sliding window use karke `O(n)` mein solve kar sakte hain.
