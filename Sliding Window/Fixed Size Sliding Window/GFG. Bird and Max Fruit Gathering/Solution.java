class Solution {

    public long maxFruit(int[] arr, int m) {

        int n = arr.length;

        // If we can visit all trees, collect all fruits.
        if (m >= n) {
            long total = 0;

            for (int fruit : arr) {
                total += fruit;
            }

            return total;
        }

        // Create a window of size m.
        long windowSum = 0;

        for (int i = 0; i < m; i++) {
            windowSum += arr[i];
        }

        long maxSum = windowSum;

        // Slide the window through the circular array.
        for (int i = 1; i < n; i++) {

            // Remove the element leaving the window.
            windowSum -= arr[(i - 1) % n];

            // Add the new element entering the window.
            windowSum += arr[(i + m - 1) % n];

            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}

