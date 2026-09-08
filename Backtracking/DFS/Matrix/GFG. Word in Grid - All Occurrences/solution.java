import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> searchWord(char[][] mat, String word) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        // Store all 8 possible directions
        int[][] dir = {
            {0, -1},   // Left
            {0, 1},    // Right
            {-1, 0},   // Up
            {1, 0},    // Down
            {1, 1},    // Down-right
            {-1, -1}, // Up-left
            {1, -1},  // Down-left
            {-1, 1}   // Up-right
        };

        // Check every cell as a possible starting position
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {

                // Only start if the first character matches
                if (word.charAt(0) == mat[i][j]) {

                    // Try all 8 directions
                    for (int k = 0; k < dir.length; k++) {

                        if (solve(i, j, mat, word, 0,
                                  dir[k][0], dir[k][1])) {

                            // Store the starting coordinate
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(i);
                            list.add(j);
                            ans.add(list);

                            // Avoid adding the same starting cell again
                            break;
                        }
                    }
                }
            }
        }

        return ans;
    }

    public boolean solve(int i, int j, char[][] mat,
                         String word, int idx, int x, int y) {

        // All characters of the word have been matched
        if (idx == word.length()) {
            return true;
        }

        // Check whether the current position is inside the grid
        if (i < 0 || i >= mat.length ||
            j < 0 || j >= mat[0].length) {
            return false;
        }

        // Current grid character must match the current word character
        if (word.charAt(idx) == mat[i][j]) {

            // Move to the next cell in the same direction
            return solve(i + x, j + y, mat, word,
                         idx + 1, x, y);
        }

        return false;
    }
}
