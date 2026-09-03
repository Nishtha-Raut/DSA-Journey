class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        // Find the minimum element
        for (int i = 0; i < nums1.length; i++) {
            min = Math.min(nums1[i], min);
        }
        // If minimum is odd, we can make every element odd
        if (min % 2 != 0) {
            return true;
        }
        // If minimum is even, every element must already be even
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
