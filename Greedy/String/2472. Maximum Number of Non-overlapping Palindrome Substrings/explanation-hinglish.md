# Explanation

## Intuition

Humein maximum number of **non-overlapping palindromic substrings** select karne hain jinki length at least `k` ho.

Main idea ye hai ki hum string ko **left se right** traverse karenge aur jo sabse pehla possible valid palindrome milega, usko select kar lenge.

Har position par:

1. Pehle length `k` ka substring check karenge.
2. Agar woh palindrome nahi hai, to length `k + 1` ka substring check karenge.
3. Agar valid palindrome mil gaya, to usko select karenge.
4. Phir us palindrome ke end ke baad se search continue karenge.

Isse selected substrings non-overlapping rahenge.

## Approach

Initially:

`count = 0`

Phir string ko left se right traverse karenge.

Har index `i` par:

### Step 1: Length k Check

Check karo:

`s[i ... i+k-1]`

kya palindrome hai.

Agar palindrome hai:

- Us substring ko select karo.
- `count++`
- `i` ko us substring ke end tak move karo.

### Step 2: Length k + 1 Check

Agar length `k` palindrome nahi hai, to check karo:

`s[i ... i+k]`

Agar ye palindrome hai:

- Isko select karo.
- `count++`
- `i` ko is substring ke end tak move karo.

### Step 3: Agar Dono Valid Nahi Hain

Agar length `k` aur `k+1` dono palindrome nahi hain, to next starting position par move kar jao.

## Palindrome Check Kaise Ho Raha Hai?

`checkPalindrome()` mein two pointers use ho rahe hain:

- Ek pointer left se start hota hai.
- Ek pointer right se start hota hai.
- Dono characters compare karte hain.
- Phir left ko aage aur right ko peeche move karte hain.

Agar kisi bhi point par characters different mil gaye:

`false`

Otherwise:

`true`

## k Ko Pehle Kyun Check Karte Hain?

Agar same position se length `k` aur length `k+1` dono palindromes possible hain, to humein shorter palindrome choose karna better hai.

Example:

`k = 3`

Agar length `3` ka palindrome mil gaya, to 3 characters use honge.

Agar hum length `4` choose karte, to ek extra character consume ho jata.

Shorter palindrome choose karne se aage ke characters zyada available rahte hain, isliye maximum number of substrings banane ka chance badhta hai.

Isliye code pehle `k` length check karta hai.

## Dry Run

Example:

`s = "abaccdbbd"`

`k = 3`

Initially:

`i = 0`

### i = 0

Length `3` ka substring:

`"aba"`

`"aba"` palindrome hai.

Isko select karenge.

`count = 1`

Ab `"aba"` ke end ke baad search continue hoga.

Next starting position:

`i = 3`

### i = 3

Length `3`:

`"ccd"`

Palindrome nahi hai.

Ab length `4` check:

`"ccdb"`

Ye bhi palindrome nahi hai.

Next index par move karenge.

### i = 4

Length `3`:

`"cdb"`

Palindrome nahi hai.

Length `4`:

`"dbbd"`

Ye palindrome hai.

Isko select karenge.

`count = 2`

Ab valid substrings ki maximum count:

`2`

Answer:

`2`

## Example 2 Dry Run

`s = "adbcda"`

`k = 2`

Length `2` ke substrings:

`ad`

`db`

`bc`

`cd`

`da`

Koi bhi palindrome nahi hai.

Length `3` ke substrings bhi check karte hain:

`adb`

`dbc`

`bcd`

`cda`

Inmein bhi koi palindrome nahi hai.

Isliye:

`count = 0`

Answer:

`0`

## Why It Works

Hum string ko left se right process kar rahe hain aur earliest possible valid palindrome select kar rahe hain.

Jab ek palindrome select ho jata hai, uske andar ke characters kisi future substring mein use nahi ho sakte.

Isliye hum directly uske end ke baad se search continue karte hain.

Saath hi, agar possible ho to shorter length `k` palindrome choose karte hain, taaki minimum characters consume hon aur future mein zyada substrings select ki ja saken.

Is tarah greedy selection maximum number of non-overlapping valid substrings provide karta hai.

## Complexity

Palindrome check mein two pointers use hote hain, isliye ek check:

`O(k)`

time leta hai.

String ko traverse karte hue checks kiye jaate hain.

Therefore:

**Time Complexity: `O(n * k)`**

Worst case mein:

`k = n`

To:

`O(n²)`

**Space Complexity: `O(1)`**

Koi extra array ya data structure use nahi hota.

## Edge Cases

### 1. k = 1

Har single character palindrome hota hai.

Isliye answer string ki length ke equal hoga.

### 2. Koi Valid Palindrome Nahi Hai

Agar `k` ya usse badi length ka koi palindrome nahi hai, answer:

`0`

### 3. Puri String Palindrome Hai

Agar complete string palindrome hai, tab bhi hum shorter valid palindromes ko prefer karenge agar woh available hain, kyunki humein maximum number of substrings chahiye.

### 4. Repeated Characters

Example:

`"aaa"`

Ye palindrome hai.

Agar length `k` ke according valid hai, to ise select kiya ja sakta hai.

## Interview Takeaway

"Main greedy left-to-right approach use karta hoon. Har position par pehle length `k` ka palindrome check karta hoon, aur agar woh nahi milta to `k+1` length check karta hoon. Jaise hi valid palindrome milta hai, main usko select karke pointer ko uske end ke baad move kar deta hoon, taaki substrings non-overlapping rahen. Palindrome check ke liye two pointers use karta hoon."
