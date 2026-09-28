# Intuition

This is a **Dynamic Programming + Recursion + Memoization** problem.

We need to match the entire string `s` with the entire pattern `p`.

The difficult part is `*` because it has **two choices**:

1. `*` matches **zero occurrences** of the previous character.
2. `*` matches **one or more occurrences** of the previous character.

Because these choices can lead to different possibilities, we use recursion to explore them and memoization to avoid solving the same `(i, j)` state repeatedly.

## State

We use:

```text
memo[i][j]
```

to represent whether:

```text
s[i...] matches p[j...]
```

So `i` tells us where we currently are in `s`, and `j` tells us where we currently are in `p`.

The `ch` parameter is not actually needed for the decision; the current pattern character `p[j]` is sufficient.

## Base Cases

### Both strings are completely consumed

```text
i == s.length() && j == p.length()
```

Then everything matched successfully:

```text
true
```

### Pattern is finished but string is not

```text
j == p.length()
```

There is still something left in `s`, so the complete string cannot match:

```text
false
```

## Handling `*`

Suppose:

```text
p = "a*"
```

When we are at `a` and the next character is `*`, we have two choices.

### Choice 1: `*` matches zero characters

Skip both `a` and `*`:

```text
solve(i, j + 2)
```

For example:

```text
s = "b"
p = "a*b"
```

`a*` can match zero `a`s, so we move directly to `b`.

### Choice 2: `*` matches the current character

If the current string character matches the character before `*`:

```text
s.charAt(i) == p.charAt(j)
```

or the pattern character is `.`:

```text
p.charAt(j) == '.'
```

then `*` can consume the current character:

```text
solve(i + 1, j)
```

Notice that `j` does **not** increase.

Why?

Because `*` can match more occurrences of the same pattern character.

For example:

```text
s = "aaa"
p = "a*"
```

We can consume:

```text
a → a → a
```

while staying at the same pattern position `a*`.

## Normal Character Matching

If the next pattern character is not `*`, then we simply check whether the current characters match.

A match occurs when:

```text
s.charAt(i) == p.charAt(j)
```

or:

```text
p.charAt(j) == '.'
```

Then move both pointers:

```text
solve(i + 1, j + 1)
```

`.` can match any single character.

## Why `*` Creates Two Recursion Paths

Consider:

```text
s = "aaa"
p = "a*a"
```

For `a*`, we don't know immediately how many `a`s it should consume.

It could consume:

```text
0 a's
```

or:

```text
1 a
```

or:

```text
2 a's
```

or:

```text
3 a's
```

The recursion explores these possibilities.

Memoization makes sure that if the same `(i, j)` state is reached again, we return the stored answer instead of solving it again.

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

Pattern character is `a`, and next character is `*`.

So we have two choices.

### Zero occurrences

Skip `a*`:

```text
solve(0, 2)
```

Now `j == p.length()` but `i != s.length()`.

So:

```text
false
```

### One or more occurrences

Current characters match:

```text
s[0] == p[0]
```

So:

```text
solve(1, 0)
```

Again we have `a*`.

We can consume another `a`:

```text
solve(2, 0)
```

Now:

```text
i == s.length()
```

We can skip `a*`:

```text
solve(2, 2)
```

Both string and pattern are finished:

```text
true
```

Therefore the original answer is `true`.

## Example: `.*`

Consider:

```text
s = "ab"
p = ".*"
```

`.` matches any character and `*` allows zero or more occurrences.

So:

```text
.* → a
.* → b
```

The recursion keeps consuming characters while staying at the same pattern position.

Eventually:

```text
i == s.length()
```

and we skip `.*`, reaching the end of the pattern.

Therefore the answer is `true`.

## Why Memoization Is Necessary

Without memoization, the same `(i, j)` state can be reached through different choices of `*`.

For example, different numbers of characters consumed by `*` can lead to the same remaining string and pattern.

`memo[i][j]` stores the result of that state.

So when we encounter it again:

```text
if (memo[i][j] != null)
    return memo[i][j];
```

we immediately reuse the answer.

## Complexity

There are at most:

`(s.length() + 1) × (p.length() + 1)`

different `(i, j)` states.

Each state performs only constant work apart from recursive calls.

**Time Complexity:** `O(m × n)`

where:

- `m = s.length()`
- `n = p.length()`

**Space Complexity:** `O(m × n)`

for the memoization table, plus `O(m + n)` recursion stack in the worst case.

## Interview Takeaway

The most important part of this problem is understanding `*`.

Remember:

```text
x* has two choices

1. Match zero x
   → solve(i, j + 2)

2. Match current x
   → solve(i + 1, j)
```

The second choice keeps `j` unchanged because `*` can continue matching more characters.

The overall pattern is:

```text
Normal character / .
        ↓
match one character
        ↓
(i + 1, j + 1)

Character followed by *
        ↓
       / \
      /   \
 zero     consume one
  /         \
j + 2        i + 1
             same j
```
