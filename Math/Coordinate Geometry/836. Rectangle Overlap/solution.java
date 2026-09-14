class Solution {

    public boolean isRectangleOverlap(int[] rect1, int[] rect2) {

        // Check if rect1 is completely to the left of rect2
        if (rect1[2] <= rect2[0] || rect1[3] <= rect2[1]) {
            return false;
        }

        // Check if rect2 is completely to the left of rect1
        // or completely below rect1
        if (rect2[2] <= rect1[0] || rect2[3] <= rect1[1]) {
            return false;
        }

        // If none of the non-overlap conditions is true,
        // the rectangles have positive-area overlap
        return true;
    }
}
