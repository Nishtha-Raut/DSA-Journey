class Solution {

    public int countCommas(int n) {

        // Numbers below 1000 have no commas
        if (n < 1000) {
            return 0;
        }

        // Every number from 1000 to n has exactly one comma
        return n - 999;
    }
}
