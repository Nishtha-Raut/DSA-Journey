# Explanation

## Intuition

Humein count karna hai ki string `s` se string `t` ko subsequence ke form mein kitne different ways se bana sakte hain.

Har position par basically do choices hoti hain:

1. Current character ko **take** karo, agar woh target ke current character ke equal hai.
2. Current character ko **skip** karo.

Isliye ye naturally Dynamic Programming problem ban jaati hai.

Hum do indices use karenge:

- `i` → `s` mein current position
- `j` → `t` mein current position

`solve(i, j)` ka matlab hai:

> `s[i...]` ka use karke `t[j...]` ko banane ke kitne ways hain.

## Approach

Har state `(i, j)` par:

### Case 1: Characters Same Hain

Agar:

`s[i] == t[j]`

to humare paas do choices hain.

### Take

Current character ko target ke current character ke liye use karo:

`solve(i + 1, j + 1)`

Dono pointers aage badhenge.

### Non-Take

Current character ko ignore karo aur baaki `s` se target ko banane ki try karo:

`solve(i + 1, j)`

Isliye:

`solve(i, j) = solve(i + 1, j + 1) + solve(i + 1, j)`

### Case 2: Characters Different Hain

Agar:

`s[i] != t[j]`

to current character ko target ke liye use nahi kar sakte.

Isliye sirf usko skip karenge:

`solve(i, j) = solve(i + 1, j)`

## Base Cases

### Target Complete Ho Gaya

Agar:

`j == t.length()`

iska matlab poora target successfully ban gaya.

Isliye ek valid way mila.

Return:

`1`

### Source Complete Ho Gaya

Agar:

`i == s.length()`

lekin target abhi complete nahi hua, to target banana possible nahi hai.

Return:

`0`

## Memoization

Normal recursion mein same `(i, j)` state baar-baar calculate ho sakti hai.

Isliye hum memoization use karenge.

`memo[i][j]` store karega:

> `s[i...]` se `t[j...]` banane ke kitne ways hain.

Agar koi state pehle calculate ho chuki hai, to dobara recursion karne ki zarurat nahi hai. Direct stored answer return kar denge.

## Dry Run

Consider:

`s = "babgbag"`

`t = "bag"`

Starting mein:

`i = 0` → `s[i] = 'b'`

`j = 0` → `t[j] = 'b'`

Dono characters same hain.

To humare paas do choices hain:

- Current `b` ko take karo.
- Current `b` ko skip karo.

Dono possibilities ko recursively explore kiya jayega.

Jab bhi characters match karenge, ye branching continue hogi.

Finally, `"bag"` ko form karne ke `5` different ways milte hain.

Important point ye hai ki hum **different indices select karne ke ways** count kar rahe hain.

## Take + Non-Take Kyun Karte Hain?

Jab current characters same hain, to saare valid subsequences do groups mein divide ho jaate hain:

**Take group:**

Jo current character ko use karte hain.

**Non-Take group:**

Jo current character ko use nahi karte.

Dono groups alag hain, isliye unke counts ko add kar sakte hain.

`answer = take + nonTake`

## Complexity

Let:

- `m = s.length()`
- `n = t.length()`

Maximum `m × n` states ho sakti hain.

### Time Complexity

**O(m × n)**

Har `(i, j)` state sirf ek baar calculate hoti hai.

### Space Complexity

**O(m × n)** memoization table ke liye.

Recursion stack ke liye worst case mein **O(m + n)** extra space ho sakti hai.

## Edge Cases

- Agar target completely match ho gaya → `1`.
- Agar source khatam ho gaya aur target abhi baaki hai → `0`.
- Agar `s` aur `t` same hain → `1`.
- Agar `t`, `s` se longer hai → `0`.
- Repeated characters ki wajah se multiple different subsequences ho sakti hain.

## Important Implementation Point

Yahan sirf `i` ke basis par memoization nahi kar sakte.

Humein **2D memoization** chahiye:

`memo[i][j]`

Kyunki same `i` par answer alag ho sakta hai depending on `j`.

Matlab hum `s` ke same position par ho sakte hain, lekin `t` ke different positions ko match kar rahe honge.

## Interview Takeaway

Ye ek classic **2D DP + subsequence counting** problem hai.

Jab characters match karte hain, hum:

- current character ko take kar sakte hain, ya
- current character ko skip kar sakte hain.

Isliye recurrence:

`dp[i][j] = dp[i+1][j+1] + dp[i+1][j]`

### One-Line Interview Explanation

> Main 2D memoization use karta hoon jahan `memo[i][j]` remaining source se remaining target banane ke ways store karta hai. Characters match hone par take aur non-take dono possibilities ko add karta hoon.
