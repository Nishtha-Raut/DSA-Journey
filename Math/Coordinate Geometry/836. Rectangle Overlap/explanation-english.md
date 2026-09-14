# Explanation

## Intuition

Two rectangles do not overlap when one rectangle is completely to the:

- left of the other rectangle
- right of the other rectangle
- above the other rectangle
- below the other rectangle

If none of these conditions is true, then the rectangles must have a positive-area intersection.

The important point is that rectangles that only touch at an edge or corner are not considered overlapping.

## Rectangle Coordinates

For a rectangle:

`[x1, y1, x2, y2]`

- `x1` → left boundary
- `y1` → bottom boundary
- `x2` → right boundary
- `y2` → top boundary

For `rect1`:

- `rect1[0]` = x1
- `rect1[1]` = y1
- `rect1[2]` = x2
- `rect1[3]` = y2

Similarly for `rect2`.

## Approach

Instead of directly calculating the intersection area, we check when the rectangles are definitely not overlapping.

### Case 1: rect1 is completely to the left of rect2

Condition:

`rect1[2] <= rect2[0]`

If the right edge of `rect1` is at or before the left edge of `rect2`, there is no positive-width overlap.

The `<=` is important because if both edges are equal, the rectangles only touch.

### Case 2: rect1 is completely below rect2

Condition:

`rect1[3] <= rect2[1]`

If the top edge of `rect1` is at or below the bottom edge of `rect2`, there is no positive-height overlap.

### Case 3: rect2 is completely to the left of rect1

Condition:

`rect2[2] <= rect1[0]`

### Case 4: rect2 is completely below rect1

Condition:

`rect2[3] <= rect1[1]`

If any one of these four conditions is true, return `false`.

Otherwise, return `true`.

## Dry Run

### Example 1

`rect1 = [0,0,2,2]`

`rect2 = [1,1,3,3]`

Check whether `rect1` is left of `rect2`:

`2 <= 1` → false

Check whether `rect1` is below `rect2`:

`2 <= 1` → false

Check whether `rect2` is left of `rect1`:

`3 <= 0` → false

Check whether `rect2` is below `rect1`:

`3 <= 0` → false

None of the non-overlap conditions is true.

Therefore:

`true`

### Example 2

`rect1 = [0,0,1,1]`

`rect2 = [1,0,2,1]`

Check:

`rect1[2] <= rect2[0]`

`1 <= 1` → true

The right edge of `rect1` and left edge of `rect2` are exactly the same.

So they only touch at the edge.

Therefore:

`false`

## Why It Works

For two axis-aligned rectangles to have positive-area overlap, they must overlap in both:

1. X-axis direction
2. Y-axis direction

If one rectangle ends before or exactly where the other begins on either axis, the intersection cannot have positive area.

The four conditions cover all possible ways the rectangles can be separated.

If none of them occurs, there must be positive overlap in both dimensions.

## Time Complexity

We perform only a constant number of comparisons.

**Time Complexity: `O(1)`**

## Space Complexity

No extra data structure is used.

**Space Complexity: `O(1)`**

## Edge Cases

### 1. Touching at an Edge

Example:

`rect1 = [0,0,1,1]`

`rect2 = [1,0,2,1]`

They share an edge but have zero intersection area.

Answer:

`false`

### 2. Touching at a Corner

If one rectangle's corner exactly touches another rectangle's corner, the intersection area is zero.

Answer:

`false`

### 3. One Rectangle Completely Inside Another

Example:

`rect1 = [0,0,5,5]`

`rect2 = [1,1,2,2]`

The rectangles overlap with positive area.

Answer:

`true`

## Interview Takeaway

"Instead of calculating the intersection area, I check the four cases where the rectangles are completely separated: left, right, above, or below. If any separation condition is true, I return false; otherwise, they have a positive-area overlap."
