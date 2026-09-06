# Explanation

## Intuition

Direct approach mein hum har possible pair `(i, j)` banakar `arr[i] & arr[j]` calculate kar sakte hain.

Lekin `n` maximum `10^5` hai, aur total pairs almost `n² / 2` ho sakte hain.

Isliye `O(n²)` approach bahut slow hogi.

Yahan main observation ye hai ki Bitwise AND ko hum bit-by-bit calculate kar sakte hain.

Kisi particular bit ka result `1` tabhi aata hai jab dono numbers mein woh bit `1` ho.

Isliye har pair calculate karne ki jagah hum har bit ka total contribution calculate karenge.

## Key Observation

Maan lo kisi particular bit mein `count` numbers ke andar `1` hai.

In `count` numbers mein se koi bhi 2 numbers lenge, to unka AND us bit par `1` hoga.

Aise pairs ki number hogi:

`count * (count - 1) / 2`

Agar current bit ki value `2^k` hai, to us bit ka total contribution hoga:

`pairs * 2^k`

Hum har bit ke liye ye calculate karke sabko add kar denge.

## Approach

1. Har bit position ko consider karo.
2. Count karo ki kitne array elements mein current bit set hai.
3. Number of pairs calculate karo:
   `count * (count - 1) / 2`
4. Current bit ki value calculate karo.
5. `pairs * bitValue` ko answer mein add karo.
6. Finally answer return karo.

`arr[i] <= 10^8` hai, isliye 31 bits check karna enough hai.

## Dry Run

Array:

`arr = [5, 10, 15]`

Binary:

`5  = 0101`

`10 = 1010`

`15 = 1111`

### Bit 0

Bit 0 `5` aur `15` mein set hai.

Count = 2

Pairs:

`2 * 1 / 2 = 1`

Contribution:

`1 * 1 = 1`

### Bit 1

Bit 1 `10` aur `15` mein set hai.

Count = 2

Pairs = 1

Contribution:

`1 * 2 = 2`

### Bit 2

Bit 2 `5` aur `15` mein set hai.

Count = 2

Pairs = 1

Contribution:

`1 * 4 = 4`

### Bit 3

Bit 3 `10` aur `15` mein set hai.

Count = 2

Pairs = 1

Contribution:

`1 * 8 = 8`

Total:

`1 + 2 + 4 + 8 = 15`

Answer = `15`

## Why It Works

Bitwise AND mein kisi bit ka result `1` tabhi hota hai jab pair ke dono elements mein woh bit `1` ho.

Agar kisi bit mein `count` numbers ke andar `1` hai, to unmein se har 2 numbers ka pair us bit ko AND result mein contribute karega.

Total pairs:

`count * (count - 1) / 2`

Isliye har bit ka contribution independently calculate karne par complete answer mil jata hai.

## Complexity

Agar `k` bits check kar rahe hain:

- Time: `O(n * k)`
- Space: `O(1)`

Yahan `k = 31` constant hai, isliye practically time complexity `O(n)` hai.

## Edge Cases

### Sirf ek element

Agar:

`arr = [7]`

To koi pair possible nahi hai.

`count = 1` hone par:

`1 * 0 / 2 = 0`

Isliye answer `0` hoga.

### Common set bit nahi hai

Agar kisi pair ke numbers mein koi common set bit nahi hai, to unka AND `0` hoga.

### Large answer

Elements maximum `10^8` hain, lekin pairs bahut zyada ho sakte hain.

Isliye total answer `int` ki range se bada ho sakta hai.

Java mein `long` use karna safe hai.

## Interview Takeaway

Interview mein simple way mein bol sakte ho:

"Instead of checking every pair, I process every bit independently. If a bit is set in `count` numbers, then it contributes to `count choose 2` pairs. So its contribution is `count * (count - 1) / 2 * 2^bit`."

Important formula:

`pairs = count * (count - 1) / 2`
