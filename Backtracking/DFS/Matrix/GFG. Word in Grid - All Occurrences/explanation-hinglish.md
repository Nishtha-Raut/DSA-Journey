# Explanation

## Intuition

Humein grid mein har woh starting cell find karna hai jahan se given word ban sakta hai.

Word 8 directions mein ban sakta hai:

- Left
- Right
- Up
- Down
- 4 diagonal directions

Lekin ek baar direction choose karne ke baad direction change nahi kar sakte.

Isliye har cell ko starting point maan kar saari 8 directions check karenge.

## Approach

Har cell `(i, j)` ke liye:

1. Check karo ki current character word ke first character ke equal hai ya nahi.
2. Agar equal hai, to 8 directions try karo.
3. Har direction mein next cell par move karo.
4. Word ke characters ko one by one match karo.
5. Agar complete word mil jata hai, to starting coordinate answer mein add karo.
6. Agar kisi ek direction mein word mil gaya, to same starting cell ke liye baaki directions check karne ki zarurat nahi hai.

## 8 Directions

Directions ko hum `(x, y)` ke form mein store kar sakte hain:

- Left: `(0, -1)`
- Right: `(0, 1)`
- Up: `(-1, 0)`
- Down: `(1, 0)`
- Down-right: `(1, 1)`
- Up-left: `(-1, -1)`
- Down-left: `(1, -1)`
- Up-right: `(-1, 1)`

## solve() Function

`solve()` current position aur current word index ko check karta hai.

Sabse pehle:

`idx == word.length()`

Agar true hai, iska matlab complete word match ho gaya, so `true` return karo.

Phir boundary check karo.

Agar current cell grid ke bahar hai, to `false`.

Agar current character word ke current character se match nahi karta, to `false`.

Otherwise, same direction mein next cell par move karo.

## Visited Array Ki Zarurat Kyun Nahi Hai?

Normally grid DFS problems mein `visited` array ki zarurat ho sakti hai.

Lekin yahan word ko straight line mein banana hai aur direction change nahi kar sakte.

Har recursive call mein same `(x, y)` direction use ho raha hai.

Isliye hum continuously forward move karte hain aur same cell dobara visit nahi hota.

Therefore, `visited` array ki zarurat nahi hai.

## Dry Run

Example:

`mat = {{a,b,a,b},{a,b,e,b},{e,b,e,b}}`

`word = "abe"`

### Starting at `(0,0)`

Current character:

`a`

Word ka first character bhi `a` hai.

Down-right direction `(1,1)` try karte hain:

`(0,0) = a`

`(1,1) = b`

`(2,2) = e`

Word `"abe"` complete ho gaya.

So `(0,0)` answer mein add hoga.

### Starting at `(0,2)`

Down-left direction `(1,-1)`:

`(0,2) = a`

`(1,1) = b`

`(2,0) = e`

Word mil gaya.

So `(0,2)` add hoga.

### Starting at `(1,0)`

Right direction `(0,1)`:

`(1,0) = a`

`(1,1) = b`

`(1,2) = e`

Word mil gaya.

So `(1,0)` add hoga.

Final answer:

`{{0,0}, {0,2}, {1,0}}`

## Why It Works

Hum grid ke har possible starting cell ko check karte hain.

Har matching starting cell ke liye saari 8 directions check karte hain.

Har direction mein `solve()` word ke saare characters match karta hai.

Isliye agar word kisi bhi allowed direction mein present hai, hum us starting cell ko find kar lenge.

Grid ko row-major order mein traverse karne ki wajah se coordinates automatically lexicographically smallest order mein milte hain.

## Complexity

Maan lo:

- Grid size = `n * m`
- Word length = `L`

Har cell ke liye 8 directions aur maximum `L` characters check hote hain.

- Time: `O(n * m * 8 * L)` = `O(n * m * L)`
- Space: `O(L)` recursion stack ke liye

## Edge Cases

- Word ki length `1` hai → jis cell mein woh character hai, woh valid answer hai.
- Word bahut bada hai aur kisi direction mein fit nahi hota → answer nahi milega.
- Starting character match nahi karta → immediately skip.
- Same starting cell se multiple directions mein word milta hai → coordinate sirf ek baar add karna hai.
- Word kahin bhi nahi milta → empty list return hogi.

## Interview Takeaway

Interview mein bol sakte ho:

"Main har cell ko starting point maan kar check karunga. Agar first character match karta hai, to main 8 directions try karunga. Ek direction choose karne ke baad same direction mein recursively move karke remaining characters match karunga. Row-major traversal ki wajah se answer lexicographically sorted bhi rahega."
