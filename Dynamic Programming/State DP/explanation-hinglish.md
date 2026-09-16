# Explanation

## Intuition

Hum points ko left se right process karenge.

Har point par 2 states ho sakti hain:

- `draw = 0` → abhi koi segment open nahi hai.
- `draw = 1` → ek segment already open hai.

Agar segment open hai, to hum 2 kaam kar sakte hain:

1. Current segment ko next point tak continue karein.
2. Current segment ko current point par end karein.

Segments endpoints share kar sakte hain.

Example:

`(0,1), (1,2)`

Ye valid hai kyunki dono segments point `1` ko share kar rahe hain.

Same state baar-baar calculate na ho, isliye hum memoization use karte hain.

## DP State

`solve(i, n, k, draw)` ka meaning:

- `i` = current point.
- `k` = abhi kitne segments complete karne hain.
- `draw` = kya koi segment currently open hai?

Hum:

`memo[i][k][draw]`

mein us state ka answer store karte hain.

## Base Cases

### `k == 0`

Agar required saare segments complete ho gaye hain, to ek valid way mil gaya.

Isliye:

`return 1`

### `i >= n`

Agar saare points khatam ho gaye lekin abhi bhi segments banane hain, to ye possible nahi hai.

Isliye:

`return 0`

## Jab Koi Segment Open Nahi Hai

Agar:

`draw == 0`

to 2 choices hain.

### 1. Naya Segment Start Karna

Current point se segment start karte hain:

`solve(i + 1, n, k, 1)`

Hum `i + 1` par jaate hain kyunki segment ko kam se kam 2 points cover karne hain.

### 2. Current Point Skip Karna

Current point ko use nahi karte:

`solve(i + 1, n, k, 0)`

Isliye:

`total = startNew + skip`

## Jab Segment Already Open Hai

Agar:

`draw == 1`

to 2 choices hain.

### 1. Current Segment End Karna

Current point par segment end karte hain:

`solve(i, n, k - 1, 0)`

Yahan `i + 1` nahi kiya.

Reason: segments endpoints share kar sakte hain.

Example:

`(0,1), (1,2)`

Pehla segment `1` par end hua aur doosra bhi `1` se start ho sakta hai.

Ek segment complete hua, isliye:

`k - 1`

kar diya.

### 2. Segment Continue Karna

Current segment ko next point tak continue karte hain:

`solve(i + 1, n, k, 1)`

Isliye:

`total = endAndStartNext + skip`

## Har Segment Mein Kam Se Kam 2 Points Kaise Hain?

Jab segment start hota hai:

`solve(i + 1, n, k, 1)`

Hum next point par move karte hain.

Isliye segment same point par start aur end nahi ho sakta.

Minimum segment:

`(0,1)`

hoga.

Isliye har segment kam se kam 2 points cover karta hai.

## Dry Run

Consider:

`n = 3, k = 1`

Points:

`0   1   2`

Starting state:

`solve(0, 3, 1, 0)`

Abhi koi segment open nahi hai.

Agar point `0` se segment start karein:

`solve(1, 3, 1, 1)`

Ab segment open hai.

Hum segment ko point `2` tak continue kar sakte hain:

`solve(2, 3, 1, 1)`

Phir point `2` par segment end kar sakte hain:

`solve(2, 3, 0, 0)`

Ab `k == 0`, so ek valid way mil gaya.

Ye segment hai:

`(0,2)`

Baaki choices se:

`(0,1)`

aur:

`(1,2)`

bhi milte hain.

Total answer:

`3`

## Memoization Kyu?

Same `(i, k, draw)` state different paths se multiple times aa sakti hai.

Isliye result ko:

`memo[i][k][draw]`

mein store kar dete hain.

Agar same state dobara aati hai, to recursion dobara nahi chalani padti.

Direct stored answer return kar dete hain.

## Complexity

States:

- `i` ke `n` possibilities.
- `k` ke `k + 1` possibilities.
- `draw` ki 2 possibilities.

Total:

`O(n * k * 2) = O(nk)`

Har state mein constant work hai.

### Time Complexity

`O(n * k)`

### Space Complexity

Memoization:

`O(n * k)`

Recursion stack:

`O(n)`

Overall:

`O(n * k)`

## Edge Cases

- `k == 0` → saare segments complete ho gaye.
- `i >= n` aur `k > 0` → segments banana possible nahi hai.
- Segments endpoints share kar sakte hain.
- Har segment minimum 2 points cover karta hai.
- Answer ko `10^9 + 7` se modulo kiya gaya hai.

## Interview Takeaway

Ye **3-state Memoization DP** hai.

State mein hum track karte hain:

`current point + remaining segments + segment open hai ya nahi`

Main idea ye hai ki open segment ko ya to next point tak continue kar sakte hain, ya current point par end kar sakte hain, jisse next segment wahi se start ho sakta hai.
