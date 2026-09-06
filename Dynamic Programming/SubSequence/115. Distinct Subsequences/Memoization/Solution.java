class Solution {

    public int numDistinct(String s, String t) {

        // memo[i][j] stores the number of ways to form t[j...]
        // using s[i...]
        int memo[][] = new int[s.length()][t.length()];

        // Initialize all states as -1, meaning not calculated yet
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return solve(0, 0, s, t, memo);
    }

    public int solve(int i, int j, String s, String t, int memo[][]) {

        // If the entire target is matched, we found one valid way
        if (j == t.length()) {
            return 1;
        }

        // If source is exhausted before matching the target
        if (i == s.length()) {
            return 0;
        }

        // Return the already calculated result for this state
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int take = 0;

        // If current characters match, we can take the current character
        if (s.charAt(i) == t.charAt(j)) {
            take = solve(i + 1, j + 1, s, t, memo);
        }

        // We can always skip the current character of s
        int nontake = solve(i + 1, j, s, t, memo);

        // Store the total number of ways for the current state
        return memo[i][j] = take + nontake;
    }
}
