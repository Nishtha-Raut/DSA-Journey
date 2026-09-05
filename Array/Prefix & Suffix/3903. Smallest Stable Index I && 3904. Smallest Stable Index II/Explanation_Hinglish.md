# Approach

## 💡 Intuition

Har index `i` ke liye hume calculate karna hai:

```text
max(nums[0..i]) - min(nums[i..n-1])
```

Matlab hume har index par do values chahiye:

* **Left side ka maximum**, including current index.
* **Right side ka minimum**, including current index.

Agar hum har index par dono values dobara calculate karein, toh unnecessary repeated work hoga.

Isliye:

* Right side ke minimum ko pehle `rightmin` array mein store kar lenge.
* Left side ke maximum ko array traverse karte time `leftmax` variable se maintain karenge.

---

## 🔍 Approach

### 1. Suffix Minimum Calculate Karna

Ek `rightmin` array banayenge:

```text
rightmin[i] = index i se last index tak ka minimum
```

Isko right se left traverse karke calculate karenge.

Example:

```text
nums = [5, 0, 1, 4]

rightmin = [0, 0, 1, 4]
```

For example:

```text
rightmin[1] = min(0, 1, 4) = 0
```

---

### 2. Prefix Maximum Maintain Karna

Ab array ko left se right traverse karenge.

Ek variable `leftmax` rakhenge jo current index tak ka maximum store karega:

```text
leftmax = max(leftmax, nums[i])
```

For example:

```text
nums = [5, 0, 1, 4]
```

`leftmax` values hongi:

```text
5 → 5 → 5 → 5
```

---

### 3. Stable Index Check Karna

Har index par instability score calculate karenge:

```text
instability = leftmax - rightmin[i]
```

Agar:

```text
instability <= k
```

toh current index stable hai.

Hum left se right traverse kar rahe hain, isliye **jo first stable index milega wahi smallest stable index hoga**.

Agar koi bhi stable index nahi milta, toh `-1` return karenge.

---

## ⏱️ Complexity Analysis

### Time Complexity

**O(n)**

Array ko do baar traverse karte hain:

* Ek baar `rightmin` calculate karne ke liye.
* Ek baar `leftmax` maintain karke answer find karne ke liye.

Therefore:

```text
O(n)
```

### Space Complexity

**O(n)**

`rightmin` array mein `n` elements store karte hain.

```text
O(n)
```
