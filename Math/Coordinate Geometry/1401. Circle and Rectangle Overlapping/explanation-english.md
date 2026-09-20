# Explanation

## Intuition

We need to check whether a circle and an axis-aligned rectangle have at least one common point.

The easiest way is to find the **closest point of the rectangle to the center of the circle**.

Once we know that point `(nx, ny)`, we calculate its squared distance from the circle center.

If this distance is less than or equal to the squared radius, the circle and rectangle overlap.

---

## Step 1: Find the Closest X Coordinate

The rectangle's x-coordinate range is `[x1, x2]`.

* If `xCenter < x1`, the closest x-coordinate is `x1`.
* If `xCenter > x2`, the closest x-coordinate is `x2`.
* Otherwise, `xCenter` is already inside the range, so it is the closest x-coordinate.

This gives us `nx`.

---

## Step 2: Find the Closest Y Coordinate

The same logic is applied to the y-coordinate.

The rectangle's y-coordinate range is `[y1, y2]`.

* If `yCenter < y1`, the closest y-coordinate is `y1`.
* If `yCenter > y2`, the closest y-coordinate is `y2`.
* Otherwise, `yCenter` is already inside the range.

This gives us `ny`.

---

## Step 3: Calculate Squared Distance

Now `(nx, ny)` is the closest point of the rectangle to the circle center.

Calculate:

`dx = nx - xCenter`

`dy = ny - yCenter`

Then:

`distanceSquared = dx² + dy²`

Instead of calculating the actual distance using square root, compare squared values:

`distanceSquared <= radius²`

If this is true, the closest point is inside or on the circle, so the circle and rectangle overlap.

---

## Dry Run

Consider:

`radius = 1`

Circle center:

`(0, 0)`

Rectangle:

`(1, -1)` to `(3, 1)`

The closest x-coordinate is `1` because the circle center is to the left of the rectangle.

The y-coordinate `0` is already inside `[-1, 1]`, so the closest y-coordinate is `0`.

Closest point:

`(1, 0)`

Squared distance:

`(1 - 0)² + (0 - 0)² = 1`

Squared radius:

`1² = 1`

Since:

`1 <= 1`

the answer is `true`.

---

## Why the Closest Point Works

The closest point of the rectangle to the circle center gives the minimum possible distance between the circle center and any point of the rectangle.

* If this minimum distance is at most the radius, the shapes overlap.
* If this minimum distance is greater than the radius, every point of the rectangle is outside the circle.

Therefore, checking only the closest point is sufficient.

---

## Why We Use Squared Distance

The normal distance formula contains a square root:

`distance = sqrt(dx² + dy²)`

We only need to compare the distance with the radius.

So instead of:

`distance <= radius`

we use:

`distance² <= radius²`

This avoids the square root and gives the same result.

---

## Complexity

**Time Complexity:** `O(1)`

**Space Complexity:** `O(1)`

---

## Edge Cases

1. If the circle only touches the rectangle, `distanceSquared == radius²`, so the answer is `true`.
2. If the circle center is inside the rectangle, the closest point is the center itself and the answer is `true`.
3. If the rectangle is completely outside the circle, the closest point will have a distance greater than the radius.
4. The closest point can be a rectangle corner when the circle is diagonally outside the rectangle.

---

## Interview Takeaway

Remember this pattern:

**Circle center → Find closest point on rectangle → Calculate squared distance → Compare with radius²**

This is a common **Coordinate Geometry** technique for intersection and collision problems.
