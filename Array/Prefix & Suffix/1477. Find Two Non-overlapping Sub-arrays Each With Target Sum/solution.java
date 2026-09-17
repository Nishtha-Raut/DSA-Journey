class Solution {

```
public int minSumOfLengths(int[] arr, int target) {

    int prefixmin[] = new int[arr.length];
    int suffixmin[] = new int[arr.length];

    int prelen = 1000000;
    int psum = 0;

    int suflen = 1000000;
    int ssum = 0;

    // Find the shortest target-sum subarray
    // ending at or before each index.
    int j = 0;

    for (int i = 0; i < arr.length; i++) {

        psum += arr[i];

        while (psum > target) {
            psum -= arr[j];
            j++;
        }

        if (psum == target) {
            prelen = Math.min(prelen, i - j + 1);
        }

        prefixmin[i] = prelen;
    }

    // Find the shortest target-sum subarray
    // starting at or after each index.
    j = arr.length - 1;

    for (int i = arr.length - 1; i >= 0; i--) {

        ssum += arr[i];

        while (ssum > target) {
            ssum -= arr[j];
            j--;
        }

        if (ssum == target) {
            suflen = Math.min(suflen, j - i + 1);
        }

        suffixmin[i] = suflen;
    }

    int ans = Integer.MAX_VALUE;

    // Try every split between i and i + 1.
    // The two chosen subarrays will not overlap.
    for (int i = 0; i < arr.length - 1; i++) {

        if (prefixmin[i] != 1000000 &&
            suffixmin[i + 1] != 1000000) {

            ans = Math.min(
                ans,
                prefixmin[i] + suffixmin[i + 1]
            );
        }
    }

    return (ans == Integer.MAX_VALUE) ? -1 : ans;
}
```

}
