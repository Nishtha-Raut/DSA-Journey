```java
class Solution {

    public int smallestIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int sum = 0;

            // Calculate the sum of digits of nums[i].
            while (nums[i] > 0) {
                sum += nums[i] % 10;
                nums[i] /= 10;
            }

            // Check if digit sum is equal to the current index.
            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}
```
