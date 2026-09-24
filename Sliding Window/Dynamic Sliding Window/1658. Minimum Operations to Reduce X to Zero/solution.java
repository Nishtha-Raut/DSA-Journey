class Solution {
public int minOperations(int[] nums, int x) {

```
    // Required sum of the subarray that should remain.
    int k = -x;

    for (int i = 0; i < nums.length; i++) {
        k += nums[i];
    }

    int ans = -1;
    int j = 0;
    int sum = 0;

    // Find the longest subarray having sum k.
    for (int i = 0; i < nums.length; i++) {

        sum += nums[i];

        // Shrink the window if its sum becomes too large.
        while (sum > k && j <= i) {
            sum -= nums[j];
            j++;
        }

        // Store the maximum length of a valid subarray.
        if (sum == k) {
            ans = Math.max(i - j + 1, ans);
        }
    }

    // Remove all elements outside the longest valid subarray.
    return (ans == -1) ? -1 : nums.length - ans;
}
```

}
