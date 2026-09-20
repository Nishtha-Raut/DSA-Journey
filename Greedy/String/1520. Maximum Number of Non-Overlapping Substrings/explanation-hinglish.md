# Explanation - Hinglish

## Intuition

Is problem ka main idea ye hai ki agar kisi substring ke andar koi character `c` aa raha hai, to us substring me `c` ki **saari occurrences** honi chahiye.

Example:

`"adefaddaccc"`

Agar hum `a` se substring start karte hain, to `a` ki first occurrence index `0` par hai aur last occurrence index `6` par.

Ab is range ke andar jo bhi characters milenge, unki last occurrences bhi substring ke andar honi chahiye.

Isliye range ko zarurat padne par expand karte rahenge.

Is process ke baad hume kuch valid intervals milenge.

Phir problem ban jaati hai:

**Maximum number of non-overlapping intervals kaise select karein?**

Iske liye hum greedy interval scheduling use kar sakte hain.

---

## Step 1: First aur Last Occurrence Store Karna

Har character ke liye do arrays maintain karte hain:

- `first[c]` = character ki first occurrence
- `last[c]` = character ki last occurrence

Sirf lowercase English letters hain, isliye total 26 characters hi hain.

---

## Step 2: Valid Substrings Find Karna

Har character ke first occurrence se ek interval start karte hain.

Suppose:

`start = first[c]`

aur:

`end = last[c]`

Ab `start` se `end` tak scan karenge.

Agar beech me character `x` milta hai:

- Agar `first[x] < start`, iska matlab `x` ki ek occurrence substring ke bahar hai.
- To current substring valid nahi hai.

Otherwise:

`end = max(end, last[x])`

Isse range expand hoti rahegi aur hum ensure karenge ki substring me aaye har character ki saari occurrences included hain.

---

## Example

String:

`"adefaddaccc"`

Character `a` ke liye:

- first `a` = `0`
- last `a` = `6`

Initially:

`[0,6]`

Ab range ke andar characters check karte hain.

Agar kisi character ki last occurrence range se bahar hai, to `end` ko expand kar dete hain.

Is tarah `a` ke liye valid interval milta hai:

`[0,7]`

Similarly:

- `e` → `[1,1]`
- `f` → `[2,2]`
- `c` → `[8,10]`

---

## Step 3: Intervals Ko End Position Ke According Sort Karna

Ab hamare paas valid intervals hain.

Problem ab interval scheduling jaisi ho gayi hai.

Hum intervals ko unke `end` ke according sort karte hain.

Reason:

Jo interval sabse jaldi finish hota hai, usko choose karne se future ke liye maximum space bachta hai.

---

## Step 4: Greedy Selection

Ek variable rakhte hain:

`lastEnd = -1`

Har interval ke liye:

- Agar `start > lastEnd`, to interval overlap nahi kar raha.
- Us interval ko answer me add karo.
- `lastEnd = end` update karo.

Example:

`"adefaddaccc"`

Useful intervals:

- `[0,7]`
- `[1,1]`
- `[2,2]`
- `[8,10]`

End ke according sort karne ke baad:

- `[1,1]`
- `[2,2]`
- `[0,7]`
- `[8,10]`

Selection:

1. `[1,1]` → `"e"` select
2. `[2,2]` → `"f"` select
3. `[0,7]` → overlap, skip
4. `[8,10]` → `"ccc"` select

Final:

`["e","f","ccc"]`

---

## Greedy Kyu Kaam Karta Hai?

Har valid substring ko ek interval:

`[start, end]`

ke form me represent kar sakte hain.

Ab hume maximum number of non-overlapping intervals chahiye.

Classic interval scheduling me earliest ending interval choose karna optimal hota hai, kyunki uske baad future intervals ke liye maximum space available rehta hai.

Is problem me ek aur important observation hai:

Agar ek bada valid substring ke andar chhote valid substrings available hain, to chhote substrings ko choose karke hum same ya zyada number of substrings obtain kar sakte hain aur total length bhi kam rakh sakte hain.

Isliye earliest-ending intervals ko greedily choose karna maximum count aur minimum total length dono requirements satisfy karta hai.

---

## Dry Run

String:

`"abbaccd"`

Occurrences:

- `a` → first `0`, last `3`
- `b` → first `1`, last `2`
- `c` → first `4`, last `5`
- `d` → first `6`, last `6`

Valid intervals:

- `a` → `[0,3]` → `"abba"`
- `b` → `[1,2]` → `"bb"`
- `c` → `[4,5]` → `"cc"`
- `d` → `[6,6]` → `"d"`

End ke according sorting:

- `[1,2]`
- `[0,3]`
- `[4,5]`
- `[6,6]`

Greedy:

- `"bb"` select
- `"abba"` overlap karta hai → skip
- `"cc"` select
- `"d"` select

Result:

`["bb","cc","d"]`

Question ke according order important nahi hai, isliye:

`["d","bb","cc"]`

bhi correct hai.

---

## Time Complexity

Sirf 26 lowercase characters hain.

Har character ke liye maximum `n` positions scan ho sakti hain.

So:

**Time = O(26 × n)**

26 constant hai, therefore:

**Time = O(n)**

At most 26 intervals sort hote hain, jo practically constant hai.

---

## Space Complexity

Hum use karte hain:

- `first[26]`
- `last[26]`
- Maximum 26 valid intervals

Therefore auxiliary space:

**O(26) = O(1)**

Answer list ko include nahi kar rahe because woh output space hai.

---

## Edge Cases

### 1. Same character multiple times

Example:

`"aaaa"`

Sirf `"aaaa"` valid substring hai.

### 2. Sabhi characters unique

Example:

`"abc"`

Har character ka single-character substring valid hai:

`["a","b","c"]`

### 3. Nested intervals

Ek bada interval ke andar multiple chhote valid intervals ho sakte hain.

Greedy approach chhote/early-ending intervals ko preference deta hai.

### 4. Overlapping intervals

Agar:

`start <= lastEnd`

to interval previous selected interval ke saath overlap karta hai, isliye skip karte hain.

---

## Interview Takeaway

Is problem ko yaad rakhne ka simple way:

**First/Last Occurrence → Valid Intervals → Sort by End → Greedy Selection**

Yaani ek difficult string problem ko hum interval scheduling problem me convert kar dete hain.

Important concepts:

- First and last occurrence
- Range expansion
- Valid interval formation
- Greedy
- Interval scheduling
- Non-overlapping intervals
