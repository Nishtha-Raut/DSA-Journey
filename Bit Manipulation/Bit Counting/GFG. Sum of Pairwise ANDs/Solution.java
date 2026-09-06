class Solution {
    public long pairAndSum(int[] nums) {

        // Stores how many numbers have each bit set
        int[] arr = new int[32];

        // Count set bits at every position
        for (int i = 0; i < nums.length; i++) {

            String str = Integer.toBinaryString(nums[i]);

            int k = 31;

            // Traverse the binary representation from right to left
            for (int j = str.length() - 1; j >= 0; j--) {
                arr[k] += str.charAt(j) - '0';
                k--;
            }
        }

        long ans = 0;

        // Calculate the contribution of every bit
        for (int i = 31; i >= 0; i--) {

            // At least two numbers must contain this bit
            if (arr[i] > 1) {

                // Choose any two numbers having this bit set
                long pairs = ((long) arr[i] * (arr[i] - 1)) / 2;

                // Calculate the value of this bit
                long val = 1L << (31 - i);

                // Add this bit's contribution to the answer
                ans += val * pairs;
            }
        }

        return ans;
    }
}
