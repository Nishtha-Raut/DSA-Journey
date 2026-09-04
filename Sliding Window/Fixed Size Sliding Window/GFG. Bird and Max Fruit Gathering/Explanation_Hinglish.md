# 🐦 Bird and Max Fruit Gathering — Hinglish Explanation

## Intuition

Sabse important observation ye hai ki bird **kisi bhi tree se start** kar sakta hai aur sirf **neighboring tree** par move kar sakta hai.

Iska matlab bird jo trees visit karega, wo circular array mein hamesha ek **continuous segment** hoga.

Toh problem basically ban gayi:

> Circular array mein size `m` ke subarray ka **maximum sum** find karna.

Normal array mein fixed-size subarray ka maximum sum nikalne ke liye hum **Sliding Window** use kar sakte hain.

Lekin yahan array circular hai.

Example:

```text
[7, 2, 1, 3, 4]
```

Bird last aur first tree dono ko visit kar sakta hai:

```text
4 + 7 = 11
```

Isliye hume window ko array ke end ke baad wapas beginning se continue karna hoga.

Iske liye hum **modulo `% n`** use kar sakte hain.

---

## Approach

### Step 1: `m >= n`

Agar `m` array ke size se bada ya equal hai, toh bird maximum `n` trees hi visit kar sakta hai.

Is case mein hum poore array ka sum return kar denge.

### Step 2: Initial Window

Pehle `m` elements ka sum calculate karenge.

```text
arr[0] + arr[1] + ... + arr[m-1]
```

Ye hamari first window hogi.

### Step 3: Sliding Window

Ab window ko one-by-one position aage move karenge.

Har step mein:

* Jo element window se bahar ja raha hai → subtract
* Jo naya element window mein aa raha hai → add

Circular array ki wajah se new element ka index:

```text
(i + m - 1) % n
```

hoga.

### Step 4: Maximum Sum

Har window ka sum calculate karke maximum value store karenge.

Example:

```text
[1, 6, 2, 5, 3, 4]
m = 2
```

Possible windows:

```text
1 + 6 = 7
6 + 2 = 8   ← maximum
2 + 5 = 7
5 + 3 = 8   ← maximum
3 + 4 = 7
4 + 1 = 5   ← circular window
```

Answer:

```text
8
```

---

## Complexity

### Time Complexity

**O(N)**

Hum initial window banane ke baad har possible starting position ko ek baar process karte hain.

### Space Complexity

**O(1)**

Koi extra array nahi banate. Sirf kuch variables use karte hain.
