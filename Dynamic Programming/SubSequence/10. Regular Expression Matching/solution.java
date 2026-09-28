class Solution {

    Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return solve(0, 0, ' ', s, p);
    }

    public boolean solve(int i, int j, Character ch, String s, String p) {

        // Both string and pattern are completely matched.
        if (i == s.length() && j == p.length()) {
            return true;
        }

        // Pattern is finished but string is still remaining.
        if (j == p.length()) {
            return false;
        }

        // Return the already calculated result.
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean ans = false;

        // If the current pattern character is followed by '*'.
        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {

            // '*' matches zero occurrences.
            ans |= solve(i, j + 2, ch, s, p);

            // '*' matches the current character and can continue matching.
            if (i < s.length()
                    && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {

                ans |= solve(i + 1, j, p.charAt(j), s, p);
            }
        }

        // Normal character matches the current string character.
        if (i < s.length() && s.charAt(i) == p.charAt(j)) {
            ans |= solve(i + 1, j + 1, s.charAt(i), s, p);
        }

        // '.' matches any single character.
        else if (i < s.length() && p.charAt(j) == '.') {
            ans |= solve(i + 1, j + 1, '.', s, p);
        }

        return memo[i][j] = ans;
    }
}
