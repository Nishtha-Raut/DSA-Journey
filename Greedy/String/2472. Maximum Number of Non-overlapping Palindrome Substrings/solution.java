class Solution {

    public int maxPalindromes(String s, int k) {

        int count = 0;

        for (int i = 0; i <= s.length() - k; i++) {

            // Check palindrome of length k
            if (checkPalindrome(i, i + k - 1, s)) {

                // Skip the selected palindrome
                i = i + k - 1;

                count++;
            }

            // If length k is not a palindrome, check length k + 1
            else if (i + k < s.length()
                    && checkPalindrome(i, i + k, s)) {

                // Skip the selected palindrome
                i = i + k;

                count++;
            }
        }

        return count;
    }

    public boolean checkPalindrome(int i, int j, String s) {

        // Compare characters from both ends
        while (i <= j) {

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
