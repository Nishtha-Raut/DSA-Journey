# Explanation

## Intuition

For every character, we need:

1. Its position in the reversed alphabet.
2. Its 1-indexed position in the string.

Then multiply these two values and add the result to the answer.

The normal alphabet has:

`a = 1, b = 2, ..., z = 26`

For the reversed alphabet:

`a = 26, b = 25, ..., z = 1`

If:

`ch = s.charAt(i) - 'a'`

then `ch` ranges from `0` to `25`.

Therefore:

`26 - ch`

gives the reversed alphabet position.

---

## Approach

Traverse the string from left to right.

For every index `i`:

* Calculate the normal zero-based character index using `s.charAt(i) - 'a'`.
* Calculate reversed alphabet position using `26 - ch`.
* The string position is `i + 1`.
* Add `(26 - ch) * (i + 1)` to `ans`.

---

## Dry Run

For `s = "abc"`:

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

## Why It Works

For every character, `26 - (ch - 'a')` gives exactly its position in the reversed alphabet.

Multiplying this value by `i + 1` gives the required contribution of that character.

Since every character is processed exactly once and all contributions are added, the final value is the reverse degree.

---

## Complexity

**Time Complexity:** `O(n)`

We traverse the string once.

**Space Complexity:** `O(1)`

Only a few variables are used.

---

## Edge Cases

1. **Single character**

   * `"a"` → `26 * 1 = 26`

2. **Character `'z'`**

   * `'z'` has reverse position `1`.

3. **Repeated characters**

   * Every occurrence uses its own position in the string.

4. **All characters are different**

   * Each character is processed independently.

---

## Interview Takeaway

Remember the formula:

`ch = s.charAt(i) - 'a'`

`reversePosition = 26 - ch`

`ans += reversePosition * (i + 1)`

This is a simple **String Traversal + Character Mapping** problem.
