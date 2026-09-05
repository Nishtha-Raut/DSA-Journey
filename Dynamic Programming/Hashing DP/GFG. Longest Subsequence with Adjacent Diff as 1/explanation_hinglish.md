# Explanation

## Intuition

Har current element `x` ke liye previous element ka absolute difference exactly `1` hona chahiye.

Isliye previous element sirf:

- `x - 1`
- `x + 1`

ho sakta hai.

Hum HashMap use karenge jisme har value ke liye us value par end hone wali longest valid subsequence ki length store hogi.

## Approach

Har element `x` ke liye:

1. `x - 1` ko HashMap mein check karo.
2. `x + 1` ko HashMap mein check karo.
3. Dono mein se maximum length lo.
4. Current element ko add karo.
5. `x` ke liye best length HashMap mein store karo.
6. Overall maximum answer update karo.

Agar `x - 1` aur `x + 1` dono nahi milte, to current element ek new subsequence start karega aur length `1` hogi.

## Ye Approach Kyun Kaam Karti Hai?

Maan lo current value `5` hai.

Iske liye valid previous value sirf `4` ya `6` ho sakti hai.

Kyunki:

`|5 - 4| = 1`

`|5 - 6| = 1`

Agar `4` par end hone wali longest valid subsequence ki length `3` hai, to `5` ko add karke length `4` ho jayegi.

Same logic `6` ke liye bhi apply hota hai.

Isliye humein har previous element ko check karne ki zarurat nahi hai. Sirf do possible values check karni hain.

## Dry Run

Array:

`[10, 9, 4, 5, 4, 8, 6]`

| Current | Check Kiya | Current Length |
|---------|------------|----------------|
| 10 | 9, 11 | 1 |
| 9 | 8, 10 | 2 |
| 4 | 3, 5 | 1 |
| 5 | 4, 6 | 2 |
| 4 | 3, 5 | 3 |
| 8 | 7, 9 | 3 |
| 6 | 5, 7 | 3 |

Maximum answer `3` hai.

## Time Complexity

**O(n) average**

Har element ke liye constant number of HashMap operations hote hain.

Isliye total time complexity `O(n)` hai.

## Space Complexity

**O(n)**

Worst case mein HashMap mein `n` different values store ho sakti hain.

## Edge Cases

- Sirf ek element → answer `1`.
- Saare elements same → answer `1`.
- Koi valid pair nahi → answer `1`.
- Repeated values valid ho sakti hain agar consecutive selected values ka difference `1` ho.

## Interview Takeaway

Main HashMap mein har value ke liye us value par end hone wali longest valid subsequence ki length store karta hoon.

Current value `x` ke liye sirf `x - 1` aur `x + 1` check karna hai.

### One-Line Interview Explanation

Main HashMap mein har value ki best subsequence length store karta hoon aur current value `x` ke liye `x - 1` aur `x + 1` ki length check karke best subsequence ko extend karta hoon.
