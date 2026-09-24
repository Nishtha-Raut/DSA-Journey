# Intuition

Hume har index `i` ke liye check karna hai ki `nums[i]` ke digits ka sum exactly `i` ke equal hai ya nahi.

Digit sum nikalne ke liye:

* `num % 10` se last digit milta hai.
* `num / 10` se last digit remove ho jata hai.

Hum array ko left se right traverse karenge. Jaise hi koi valid index milega, usko return kar denge.

Kyuki hum indices ko increasing order mein check kar rahe hain, isliye jo pehla valid index milega wahi **smallest index** hoga.

# Approach

1. Array ko index `0` se traverse karo.
2. Har `nums[i]` ka digit sum calculate karo.
3. Digit sum ko current index `i` se compare karo.
4. Agar `sum == i`, to `i` return kar do.
5. Agar poora array check ho gaya aur koi match nahi mila, to `-1` return karo.

# Digit Sum Kaise Nikalte Hain?

Suppose:

`num = 123`

Last digit:

`123 % 10 = 3`

Number ko reduce karo:

`123 / 10 = 12`

Again:

`12 % 10 = 2`

Then:

`12 / 10 = 1`

Again:

`1 % 10 = 1`

Digit sum:

`3 + 2 + 1 = 6`

# Dry Run

For:

`nums = [1,10,11]`

### i = 0

`nums[0] = 1`

Digit sum = `1`

`1 != 0`

Continue.

### i = 1

`nums[1] = 10`

Digit sum:

`1 + 0 = 1`

Ab:

`sum == i`

`1 == 1`

So answer `1` return kar denge.

Aage check karne ki zarurat nahi hai because hume smallest index chahiye.

# Complexity

Agar `d` maximum number of digits hai:

* **Time:** `O(n × d)`
* **Space:** `O(1)`

Given constraint `nums[i] <= 1000`, maximum 4 digits hain, so practically time complexity **O(n)** hai.

# Edge Cases

* Agar `nums[0] = 0`, to digit sum `0` hoga aur answer `0` ho sakta hai.
* Agar multiple indices valid hain, to smallest index return karna hai.
* Agar koi bhi index valid nahi hai, to `-1` return karna hai.

# Interview Takeaway

Ye ek basic **digit manipulation** problem hai.

Important technique:

`Last digit → num % 10`

`Remove last digit → num / 10`

Aur kyuki hum indices ko left-to-right check kar rahe hain, first valid index automatically smallest hoga.
