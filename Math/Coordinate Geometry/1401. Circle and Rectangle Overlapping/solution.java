class Solution {

```
public boolean checkOverlap(int radius, int xCenter, int yCenter,
                            int x1, int y1, int x2, int y2) {

    // Find the closest x-coordinate of the rectangle to the circle center.
    int nx = xCenter;

    if (x1 >= xCenter) {
        nx = x1;
    }

    if (x2 <= xCenter) {
        nx = x2;
    }

    // Find the closest y-coordinate of the rectangle to the circle center.
    int ny = yCenter;

    if (y1 >= yCenter) {
        ny = y1;
    }

    if (y2 <= yCenter) {
        ny = y2;
    }

    // Calculate the squared distance between the closest point
    // and the center of the circle.
    int dx = nx - xCenter;
    int dy = ny - yCenter;

    int distanceSquared = (dx * dx) + (dy * dy);

    // Check if the closest point lies inside or on the circle.
    return distanceSquared <= (radius * radius);
}
```

}
