import java.util.*;

class Solution {

    int mod = (int) 1e9 + 7;

    // memo[i][k][draw]
    // i = current point
    // k = number of segments still needed
    // draw = 0 if no segment is open, 1 if a segment is open
    int memo[][][];

    public int numberOfSets(int n, int k) {

        memo = new int[n][k + 1][2];

        // -1 means the state has not been calculated yet
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }

        return solve(0, n, k, 0);
    }

    public int solve(int i, int n, int k, int draw) {

        // All required segments have been completed
        if (k == 0) {
            return 1;
        }

        // No points are left but segments are still required
        if (i >= n) {
            return 0;
        }

        // Return the already calculated answer
        if (memo[i][k][draw] != -1) {
            return memo[i][k][draw];
        }

        long total = 0;

        if (draw == 1) {

            // End the current segment at point i.
            // Another segment can start from the same endpoint.
            long endAndStartNext = solve(i, n, k - 1, 0);

            // Continue the current segment to the next point
            long skip = solve(i + 1, n, k, 1);

            total = (endAndStartNext + skip) % mod;

        } else {

            // Start a new segment from the current point
            long startNew = solve(i + 1, n, k, 1);

            // Skip the current point
            long skip = solve(i + 1, n, k, 0);

            total = (startNew + skip) % mod;
        }

        return memo[i][k][draw] = (int) total;
    }
}
