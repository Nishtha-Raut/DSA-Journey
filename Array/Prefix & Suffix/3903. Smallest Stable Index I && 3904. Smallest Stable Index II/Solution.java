class Solution {

    public int firstStableIndex(int[] nums, int k) {

        // rightmin[i] stores the minimum element
        // from index i to the end of the array
        int rightmin[] = new int[nums.length];

        int min = Integer.MAX_VALUE;

        // Traverse from right to left to calculate suffix minimum
        for (int i = nums.length - 1; i >= 0; i--) {

            min = Math.min(min, nums[i]);
            rightmin[i] = min;
        }

        int ans = -1;

        // Stores the maximum element from index 0 to current index
        int leftmax = Integer.MIN_VALUE;

        // Traverse from left to right
        for (int i = 0; i < nums.length; i++) {

            // Update maximum in the left part
            leftmax = Math.max(leftmax, nums[i]);

            // Instability score = prefix maximum - suffix minimum
            // If score <= k, current index is stable
            if (leftmax - rightmin[i] <= k && ans == -1) {
                ans = i;
            }
        }

        // Return the smallest stable index
        // If no stable index exists, return -1
        return ans;
    }
}
