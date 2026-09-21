class Solution {
public long[] resultArray(int[] nums, int k) {
long result[] = new long[k];
long prevcount[] = new long[k];

```
    for (int i = 0; i < nums.length; i++) {
        long currcount[] = new long[k];

        // Handle negative numbers safely.
        int currentRemainder = ((nums[i] % k) + k) % k;

        // A subarray starting with the current element.
        currcount[currentRemainder]++;

        // Extend all previous subarrays with the current element.
        for (int j = 0; j < k; j++) {
            if (prevcount[j] > 0) {
                int newRemain = (j * currentRemainder) % k;
                currcount[newRemain] += prevcount[j];
            }
        }

        // Add current subarrays to the final result.
        for (int x = 0; x < k; x++) {
            result[x] += currcount[x];

            // Keep these counts for future transitions.
            prevcount[x] += currcount[x];
        }
    }

    return result;
}
```

}
