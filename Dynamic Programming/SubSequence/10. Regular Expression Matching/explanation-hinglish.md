# Intuition

Ye **Dynamic Programming + Recursion + Memoization** problem hai.

Hume `s` aur `p` ko completely match karna hai.

Sabse tricky part `*` hai, kyunki `*` ke paas do choices hoti hain:

1. Previous character ko **zero times** match kare.
2. Previous character ko **one or more times** match kare.

Isliye recursion mein dono possibilities explore karni padti hain.

Aur same `(i, j)` state baar-baar solve na karni pade, isliye `memo` use karte hain.

## State

```text
memo[i][j]
```

batata hai:

```text
s[i...] kya p[j...] se match karta hai?
```

Yahan:

- `i` → string `s` ka current index
- `j` → pattern `p` ka current index

`ch` parameter ki actually zarurat nahi hai, kyunki current pattern character `p[j]` se hi required information mil jaati hai.

## Base Cases

### Dono completely finish ho gaye

Agar:

```text
i == s.length() && j == p.length()
```

to dono completely match ho gaye.

Return:

```text
true
```

### Pattern finish ho gaya but string baaki hai

Agar:

```text
j == p.length()
```

aur `s` abhi baaki hai, to complete match possible nahi hai.

Return:

```text
false
```

## `*` Ko Kaise Handle Karte Hain?

Suppose:

```text
p = "a*"
```

Jab `p[j]` ke next mein `*` hai, tab do possibilities hain.

### Choice 1: Zero Occurrences

`a*` zero `a` ko match kar sakta hai.

Isliye `a` aur `*` dono skip kar do:

```text
solve(i, j + 2)
```

Example:

```text
s = "b"
p = "a*b"
```

Yahan `a*` ko zero `a` match karne do.

Directly `b` par chale jayenge.

### Choice 2: Current Character Consume Karo

Agar current string character `p[j]` se match karta hai:

```text
s.charAt(i) == p.charAt(j)
```

ya pattern character `.` hai:

```text
p.charAt(j) == '.'
```

to `*` current character ko consume kar sakta hai.

Isliye:

```text
solve(i + 1, j)
```

Notice karo `j` same hai.

### `j` Same Kyun?

Kyuki `*` same character ko multiple times match kar sakta hai.

Example:

```text
s = "aaa"
p = "a*"
```

`a*`:

```text
a
a
a
```

teeno characters match kar sakta hai.

Isliye har character consume karne ke baad pattern mein aage nahi badhte. `j` same rehta hai.

## Normal Character Matching

Agar next pattern character `*` nahi hai, to normal matching hogi.

Current characters match hone chahiye:

```text
s.charAt(i) == p.charAt(j)
```

ya pattern character:

```text
p.charAt(j) == '.'
```

Agar match ho gaya:

```text
solve(i + 1, j + 1)
```

Dono pointers ek step aage.

`.` kisi bhi single character ko match kar sakta hai.

## `*` Ke Do Paths Kyun?

Example:

```text
s = "aaa"
p = "a*a"
```

Hume decide karna padega ki `a*` kitne `a` consume kare.

Possible hai:

```text
0 a
1 a
2 a
3 a
```

Isliye recursion different possibilities try karta hai.

Memoization ensure karta hai ki same `(i, j)` state dobara calculate na ho.

## Dry Run

Consider:

```text
s = "aa"
p = "a*"
```

Initially:

```text
i = 0
j = 0
```

`p[0] = 'a'` hai aur next character `*` hai.

So two choices.

### Choice 1: Zero `a`

```text
solve(0, 2)
```

Ab `j == p.length()` hai, lekin string mein `"aa"` abhi baaki hai.

So:

```text
false
```

### Choice 2: Ek `a` Consume Karo

Current characters match:

```text
s[0] == p[0]
```

So:

```text
solve(1, 0)
```

Again `a*`.

Ek aur `a` consume kar sakte hain:

```text
solve(2, 0)
```

Ab string completely finish ho gayi.

`a*` ko zero occurrences choose karke skip kar sakte hain:

```text
solve(2, 2)
```

Ab:

```text
i == s.length()
j == p.length()
```

So:

```text
true
```

Therefore final answer `true`.

## `.*` Kaise Work Karta Hai?

Example:

```text
s = "ab"
p = ".*"
```

`.` kisi bhi character ko match kar sakta hai.

Aur `*` zero ya more occurrences allow karta hai.

So:

```text
.* → a
.* → b
```

`*` ki wajah se hum same pattern position par rehkar characters consume karte rahenge.

Finally string finish hone ke baad `.*` ko skip karenge aur pattern bhi finish ho jayega.

Answer:

```text
true
```

## Memoization Kyun?

Without memoization, `*` ke multiple choices ki wajah se same `(i, j)` state multiple times calculate ho sakti hai.

Hum:

```text
memo[i][j]
```

mein result store kar dete hain.

Agar same state dobara aaye:

```text
if (memo[i][j] != null)
    return memo[i][j];
```

Direct stored answer return kar denge.

## Complexity

Agar:

- `m = s.length()`
- `n = p.length()`

to maximum states:

```text
(m + 1) × (n + 1)
```

honge.

Har state par constant work hota hai.

**Time Complexity:** `O(m × n)`

**Space Complexity:** `O(m × n)`

Memoization table ke liye, plus recursion stack.

## Interview Takeaway

`*` ko hamesha do choices ke form mein yaad rakho:

```text
x*

1. Zero x
   → solve(i, j + 2)

2. Current x consume karo
   → solve(i + 1, j)
```

Second case mein `j` same rehta hai because `*` aur characters consume kar sakta hai.

Overall:

```text
Normal character / .
        ↓
One character match
        ↓
(i + 1, j + 1)

Character + *
        ↓
      /   \
   zero   consume
    /       \
 j + 2     i + 1
           same j
```
