# Explanation

## Intuition

We need to find every cell from which the given word can be formed.

The word can move in any of the 8 directions, but once a direction is selected, we must continue in the same direction.

So, for every cell:

1. Check whether it matches the first character of the word.
2. If it matches, try all 8 possible directions.
3. For each direction, keep moving one cell at a time.
4. If all characters of the word match, the starting cell is a valid answer.
5. Since we scan the matrix from top-left to bottom-right, the answers are automatically generated in lexicographically smallest order.

## 8 Directions

The 8 possible directions are:

- Left: `(0, -1)`
- Right: `(0, 1)`
- Up: `(-1, 0)`
- Down: `(1, 0)`
- Down-right: `(1, 1)`
- Up-left: `(-1, -1)`
- Down-left: `(1, -1)`
- Up-right: `(-1, 1)`

## Approach

For every cell `(i, j)`:

- If `mat[i][j]` is not equal to `word.charAt(0)`, skip it.
- Otherwise, check all 8 directions.
- Call `solve()` with:
  - Current row and column
  - Current index in the word
  - Direction `(x, y)`

Inside `solve()`:

- If `idx == word.length()`, the complete word has been matched, so return `true`.
- If the current position is outside the grid, return `false`.
- If the current grid character does not match the current word character, return `false`.
- Otherwise, move to the next cell in the same direction.

If any direction successfully forms the complete word, add the starting coordinate to the answer and stop checking other directions for that starting cell.

## Why We Do Not Need a Visited Array

The word must be formed in a straight line without changing direction.

Every recursive call moves by the same direction `(x, y)`.

Since each direction moves continuously forward, the same cell cannot be visited again during one occurrence.

Therefore, no separate `visited` array is required.

## Dry Run

Consider:

`mat = {{a,b,a,b},{a,b,e,b},{e,b,e,b}}`

`word = "abe"`

Start at `(0,0)`:

- `mat[0][0] = 'a'` → matches `'a'`
- Try down-right direction `(1,1)`
- `(1,1) = 'b'` → matches `'b'`
- `(2,2) = 'e'` → matches `'e'`
- Complete word found.

So `(0,0)` is added.

Start at `(0,2)`:

- `'a'` matches.
- Try down-left direction `(1,-1)`
- `(1,1) = 'b'`
- `(2,0) = 'e'`
- Complete word found.

So `(0,2)` is added.

Start at `(1,0)`:

- `'a'` matches.
- Try right direction `(0,1)`
- `(1,1) = 'b'`
- `(1,2) = 'e'`
- Complete word found.

So `(1,0)` is added.

Final answer:

`{{0,0}, {0,2}, {1,0}}`

## Why It Works

Every possible starting position is checked.

For each valid starting position, all 8 possible directions are checked.

For a chosen direction, `solve()` verifies every character of the word in that straight line.

Therefore, if the word exists from a starting cell in any allowed direction, that cell will be found.

The row-major traversal also guarantees lexicographical order because coordinates are considered from smaller row to larger row, and within each row from smaller column to larger column.

## Complexity

Let the grid have `n * m` cells and the word length be `L`.

For each cell, we can try 8 directions and check up to `L` characters.

- Time: `O(n * m * 8 * L)` = `O(n * m * L)`
- Space: `O(L)` due to recursion stack

There is no visited array or other large auxiliary data structure.

## Edge Cases

- Word length is `1`: every cell containing that character is a valid starting position.
- Word is longer than the possible straight-line path: no answer is found from that direction.
- Starting cell does not match the first character: skip immediately.
- Word occurs in multiple directions from the same starting cell: add the starting coordinate only once.
- Word does not occur anywhere: return an empty list.

## Interview Takeaway

"We check every cell as a possible starting point and try all 8 directions. Once a direction is chosen, we recursively move in the same direction and match the remaining characters. Since we scan the grid row by row, the resulting coordinates are already in lexicographical order."
