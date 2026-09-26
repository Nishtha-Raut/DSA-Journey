# Intuition

We need to replace every `(key)` in the string with its corresponding value from `knowledge`.

The main problem is to quickly find the value of a key.

Since there can be up to `10^5` entries in `knowledge`, searching the list every time would be inefficient.

So we first store all key-value pairs in a `HashMap`.

```text
key → value
```

For example:

```text
"name" → "bob"
"age"  → "two"
```

Then we scan the string from left to right.

When we find `(`, we remember its position.

When we find `)`, the characters between the stored `(` and current `)` form the key.

We look up that key in the `HashMap`:

- If the key exists → append its value.
- If the key does not exist → append `?`.

Characters outside brackets are directly added to the answer.

## Approach

1. Create a `HashMap<String, String>` from `knowledge`.
2. Create a `StringBuilder` for the result.
3. Maintain `open`, which stores the index of the latest `(`.
4. Traverse the string:
   - If the character is `(`, store its index in `open`.
   - If the character is `)`, extract the key using:
     `s.substring(open + 1, i)`.
   - Check the key in the map.
   - Append the corresponding value or `?`.
   - If `open == -1` and the character is normal text, append it directly.
5. Return the constructed string.

## Why `open` Is Needed

Suppose:

```text
s = "hi(name)"
```

When we reach `(`, we store its index.

When we reach `)`, we know that everything between these two positions is the key:

```text
(name)
 ^   ^
 |   |
open i
```

So:

```text
s.substring(open + 1, i)
```

gives:

```text
"name"
```

## Dry Run

Consider:

```text
s = "(name)is(age)yearsold"
knowledge = [["name","bob"],["age","two"]]
```

First bracket pair:

```text
(name)
```

Extract:

```text
key = "name"
```

Map contains `"name"`:

```text
"bob"
```

Append:

```text
bob
```

Then the characters:

```text
is
```

are outside brackets, so append them directly.

Next:

```text
(age)
```

Extract:

```text
key = "age"
```

Map gives:

```text
"two"
```

Final result:

```text
bobistwoyearsold
```

## Why Use `StringBuilder`?

Strings in Java are immutable.

Repeatedly doing:

```text
result = result + character
```

can create many intermediate strings.

`StringBuilder` allows us to append characters and strings efficiently while constructing the answer.

## Complexity

Let `n = s.length()` and `m = knowledge.length`.

### Time Complexity

Building the map:

`O(m)`

Scanning the string:

`O(n)`

HashMap lookup is `O(1)` on average.

Therefore:

**Time: `O(n + m)`**

### Space Complexity

The HashMap stores all key-value pairs:

`O(m)`

The result string requires:

`O(n)` space.

Therefore:

**Space: `O(n + m)`**

## Edge Cases

### Unknown key

If:

```text
s = "hi(name)"
knowledge = [["a","b"]]
```

`name` is not present in the map, so append:

```text
?
```

Result:

```text
hi?
```

### Same key multiple times

For:

```text
(a)(a)(a)
```

we perform the same HashMap lookup each time.

If `a → yes`, result becomes:

```text
yesyesyes
```

### Normal characters outside brackets

Only keys inside bracket pairs are evaluated.

For:

```text
(a)aaa
```

the final `aaa` remains unchanged.

## Interview Takeaway

This problem combines:

- **HashMap** for fast key-value lookup.
- **String traversal** for processing bracket pairs.
- **StringBuilder** for efficiently constructing the result.

The important idea is:

```text
knowledge
   ↓
HashMap

scan string
   ↓
find (key)
   ↓
lookup key
   ↓
value / ?
```
