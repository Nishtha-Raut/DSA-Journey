class Solution {

    public long countCommas(long n) {

        // Numbers below 1000 have no commas
        if (n < 1000) {
            return 0;
        }

        // Count how many comma levels are present
        long i = 1;
        int count = 0;

        while (Math.pow(1000, i) <= n) {
            i++;
            count++;
        }

        long ans = 0;

        // 999 means numbers starting from 1000
        long tosubtract = 999;

        while (count > 0) {

            // Count numbers that contribute this comma
            ans += n - tosubtract;

            // Move to the next comma level
            tosubtract = (tosubtract * 1000) + 999;

            count--;
        }

        return ans;
    }
}
