import java.util.HashMap;

class Solution {
    public int longestSubseq(int[] arr) {

        // Stores the longest valid subsequence length ending with each value
        HashMap<Integer, Integer> map = new HashMap<>();

        int max = 0;

        for (int i = 0; i < arr.length; i++) {

            int prev = 0;

            // Check for the value one less than the current value
            if (map.containsKey(arr[i] - 1)) {
                prev = Math.max(prev, map.get(arr[i] - 1));
            }

            // Check for the value one greater than the current value
            if (map.containsKey(arr[i] + 1)) {
                prev = Math.max(prev, map.get(arr[i] + 1));
            }

            // Add the current element to the best previous subsequence
            prev += 1;

            // Update the maximum subsequence length
            max = Math.max(prev, max);

            // If a better length for the current value already exists,
            // do not replace it with a smaller length
            if (map.containsKey(arr[i]) && map.get(arr[i]) > prev) {
                continue;
            }

            // Store the best subsequence length for the current value
            map.put(arr[i], prev);
        }

        return max;
    }
}
