# Explanation - Hinglish

## Intuition

Har character ke liye hume do values chahiye:

1. Reversed alphabet me uski position.
2. String me uski 1-indexed position.

Phir dono ko multiply karke answer me add karna hai.

Normal alphabet:

`a = 1, b = 2, ..., z = 26`

Reversed alphabet:

`a = 26, b = 25, ..., z = 1`

Code me:

`ch = s.charAt(i) - 'a'`

se character ka zero-based index milta hai.

Phir:

`26 - ch`

se reversed alphabet ki position mil jaati hai.

---

## Approach

String ko left se right traverse karenge.

Har index `i` ke liye:

1. `ch = s.charAt(i) - 'a'`
2. Reversed position = `26 - ch`
3. String position = `i + 1`
4. Contribution ko answer me add karenge.

Formula:

`ans += (26 - ch) * (i + 1)`

---

## Dry Run

`s = "abc"`

### `'a'`

`ch = 0`

Reverse position:

`26 - 0 = 26`

String position:

`1`

Contribution:

`26 * 1 = 26`

### `'b'`

`ch = 1`

Reverse position:

`26 - 1 = 25`

String position:

`2`

Contribution:

`25 * 2 = 50`

### `'c'`

`ch = 2`

Reverse position:

`26 - 2 = 24`

String position:

`3`

Contribution:

`24 * 3 = 72`

Total:

`26 + 50 + 72 = 148`

---

## Kyu Work Karta Hai?

`ch = s.charAt(i) - 'a'` character ka zero-based alphabet index deta hai.

Isliye:

`26 - ch`

exactly reversed alphabet position deta hai.

Phir `i + 1` character ki 1-indexed string position hai.

Dono ko multiply karke har character ka required contribution milta hai.

Sab contributions add karne par reverse degree mil jaata hai.

---

## Complexity

**Time Complexity:** `O(n)`

String ko sirf ek baar traverse karte hain.

**Space Complexity:** `O(1)`

Sirf kuch variables use hote hain.

---

## Edge Cases

1. **Single character**

   * `"a"` → `26`

2. **`'z'`**

   * Reversed alphabet me `'z'` ki position `1` hai.

3. **Repeated characters**

   * Har occurrence ki position different ho sakti hai, isliye har occurrence separately calculate hota hai.

4. **Different string lengths**

   * Same formula kisi bhi valid length ke liye work karta hai.

---

## Interview Takeaway

Main formula yaad rakho:

`ch = s.charAt(i) - 'a'`

`reversePosition = 26 - ch`

`ans += reversePosition * (i + 1)`

Ye **String Traversal + Character Mapping** ka simple problem hai.
