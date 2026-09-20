class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int first[] = new int[26];
        Arrays.fill(first, -1);

        int last[] = new int[26];
        Arrays.fill(last, -1);

        // Store the first and last occurrence of every character.
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        List<int[]> valid = new ArrayList<>();

        // Generate all valid intervals.
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) {
                continue;
            }

            int start = first[i];
            int end = last[i];
            boolean invalid = false;

            // Expand the interval so that all occurrences
            // of every character inside it are included.
            for (int j = start; j <= end; j++) {
                int ch = s.charAt(j) - 'a';

                // This character has an occurrence before start,
                // so this interval cannot be valid.
                if (first[ch] < start) {
                    invalid = true;
                    break;
                }

                end = Math.max(end, last[ch]);
            }

            if (!invalid) {
                valid.add(new int[]{start, end});
            }
        }

        // Sort intervals by their ending position.
        Collections.sort(valid, (a, b) -> Integer.compare(a[1], b[1]));

        List<String> ans = new ArrayList<>();
        int lastEnd = -1;

        // Greedily select non-overlapping intervals.
        for (int[] interval : valid) {
            int start = interval[0];
            int end = interval[1];

            if (start > lastEnd) {
                ans.add(s.substring(start, end + 1));
                lastEnd = end;
            }
        }

        return ans;
    }
}
