# Explanation

## Intuition

We need to check whether a circle and an axis-aligned rectangle have at least one common point.

The easiest way is to find the **closest point of the rectangle to the center of the circle**.

Once we know that point `(nx, ny)`, we calculate its distance from the circle center.

If this distance is less than or equal to the radius, the circle and rectangle overlap.

Instead of calculating the actual distance using square root, we compare squared distances.

---

## Step 1: Find the Closest X Coordinate

The circle center has x-coordinate `xCenter`.

For the rectangle, the valid x-coordinate range is:

`[x1, x2]`

There are three cases:

### Case 1: Circle center is to the left of the rectangle

If:

`xCenter < x1`

the closest x-coordinate is `x1`.

### Case 2: Circle center is to the right of the rectangle

If:

`xCenter > x2`

the closest x-coordinate is `x2`.

### Case 3: Circle center is horizontally inside the rectangle

If:

`x1 <= xCenter <= x2`

the closest x-coordinate is `xCenter` itself.

The given code handles these cases using:

```text
if (x1 >= xCenter)
    nx = x1;

if (x2 <= xCenter)
    nx = x2;
