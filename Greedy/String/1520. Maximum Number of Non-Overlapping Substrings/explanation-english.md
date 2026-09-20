# Explanation

## Intuition

The important observation is that a valid substring must contain **every occurrence of every character that appears inside it**.

For example, consider:

`"adefaddaccc"`

If we start with the first occurrence of `a`, its last occurrence is far away. While checking that range, we encounter `d`, whose last occurrence is also inside the range, so the range expands.

This process continues until we obtain the interval:

`[0, 7] -> "adefadda"`

However, smaller valid substrings such as `"e"` and `"f"` also exist inside it.

The problem can therefore be converted into finding valid intervals and selecting the maximum number of non-overlapping intervals.

---

## Step 1: Store First and Last Occurrence

For every lowercase character, store:

- `first[c]` = first position where `c` occurs.
- `last[c]` = last position where `c` occurs.

Since there are only 26 lowercase letters, this takes constant extra space.

---

## Step 2: Generate Valid Intervals

For every character that occurs in the string:

- Start an interval at its first occurrence.
- Initially set `end` to its last occurrence.
- Scan from `start` to `end`.
- Whenever we find a character `c`, its first occurrence must not be before `start`.

If:

`first[c] < start`

then the current interval is invalid because the substring would contain only some occurrences of `c`.

Otherwise, expand:

`end = max(end, last[c])`

This guarantees that if a character occurs inside the substring, all its occurrences are included.

### Example

For:

`"adefaddaccc"`

Starting from `a`:

- `a` occurs from index `0` to `6`
- The initial interval is `[0, 6]`
- We encounter `d`, whose last occurrence is `5`
- Other characters expand the interval if necessary

The resulting valid interval is:

`[0, 7]`

For `e`:

`[1, 1]`

For `f`:

`[2, 2]`

For `c`:

`[8, 10]`

So the useful valid intervals are:

- `[0,7]`
- `[1,1]`
- `[2,2]`
- `[8,10]`

---

## Step 3: Sort Intervals by Ending Position

Now the problem becomes similar to the classic **Activity Selection / Interval Scheduling** problem.

Sort valid intervals by their ending index.

Why by ending index?

Choosing the interval that finishes earliest leaves the maximum amount of space for future substrings.

---

## Step 4: Greedily Select Non-Overlapping Intervals

Maintain:

`lastEnd = -1`

For every interval:

- If `start > lastEnd`, it does not overlap with the previously selected substring.
- Select it.
- Update `lastEnd = end`.

For the example:

Sorted intervals:

`[1,1], [2,2], [0,7], [8,10]`

Selection:

1. `[1,1]` → `"e"`
2. `[2,2]` → `"f"`
3. `[0,7]` → overlaps, skip
4. `[8,10]` → `"ccc"`

Answer:

`["e", "f", "ccc"]`

---

## Why the Greedy Approach Works

Every valid substring can be represented as an interval `[start, end]`.

After generating all valid intervals, we need the maximum number of non-overlapping intervals.

For interval scheduling, choosing the interval with the earliest ending position is optimal because it leaves the largest possible remaining portion of the string for other intervals.

There is also an important property of these intervals: if a valid larger interval overlaps with smaller valid intervals inside it, selecting the smaller intervals can give at least as many substrings and a smaller total length.

Therefore, sorting by `end` and greedily selecting non-overlapping intervals gives the required maximum number of substrings and the minimum total length among maximum-count solutions.

---

## Dry Run

For:

`"abbaccd"`

First and last positions:

- `a`: first = 0, last = 3
- `b`: first = 1, last = 2
- `c`: first = 4, last = 5
- `d`: first = 6, last = 6

Valid intervals:

- `a` → `[0,3]` → `"abba"`
- `b` → `[1,2]` → `"bb"`
- `c` → `[4,5]` → `"cc"`
- `d` → `[6,6]` → `"d"`

After sorting by ending position:

- `[1,2]` → `"bb"`
- `[0,3]` → `"abba"`
- `[4,5]` → `"cc"`
- `[6,6]` → `"d"`

Greedy selection:

- Select `"bb"`
- Skip `"abba"` because it overlaps
- Select `"cc"`
- Select `"d"`

Result:

`["bb", "cc", "d"]`

The order does not matter, so this is equivalent to:

`["d", "bb", "cc"]`

---

## Time Complexity

There are only 26 possible starting characters.

For each character, we scan at most `n` positions.

Therefore:

**Time:** `O(26 × n) + O(26 log 26)`

Since 26 is constant:

**Time = O(n)**

Sorting at most 26 intervals is also effectively constant.

---

## Space Complexity

We use:

- `first[26]`
- `last[26]`
- At most 26 valid intervals
- The answer list

The auxiliary space apart from the returned answer is:

**O(26) = O(1)**

---

## Edge Cases

1. **Only one character**
   - Example: `"aaaa"`
   - Only `"aaaa"` is valid.

2. **All characters are unique**
   - Every character forms a single-character substring.
   - Example: `"abc"` → `["a","b","c"]`

3. **Nested intervals**
   - A larger valid substring may contain smaller valid substrings.
   - The greedy strategy prefers the smaller intervals when they finish earlier.

4. **Characters appearing multiple times**
   - Every occurrence must be included in the selected substring.

5. **Overlapping intervals**
   - Only intervals whose start is greater than the previous selected end are chosen.

---

## Interview Takeaway

This problem combines three important ideas:

1. **First and last occurrence tracking**
2. **Interval expansion to validate substrings**
3. **Greedy interval scheduling**

The key transformation is:

**String problem → Generate valid intervals → Maximum non-overlapping intervals**

Whenever a problem asks for the maximum number of non-overlapping ranges, it is useful to think about sorting intervals by their ending position and applying greedy selection.
