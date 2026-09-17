# Explanation

## Intuition

Humein do **non-overlapping subarrays** find karne hain jinka sum exactly `target` ho.

Dono subarrays ki total length minimum honi chahiye.

Kyuki array ke saare elements positive hain, hum **Sliding Window** use kar sakte hain.

Hum do arrays banayenge:

* `prefixmin[i]` = `0` se `i` tak target-sum subarray ki minimum length.
* `suffixmin[i]` = `i` se end tak target-sum subarray ki minimum length.

Phir har possible split `i` aur `i+1` ke beech check karenge.

Agar left aur right dono me valid subarray hai:

`prefixmin[i] + suffixmin[i + 1]`

ek valid answer hoga.

Dono different sides par hain, isliye non-overlapping honge.

## Approach

### Step 1: `prefixmin` banana

Array ko left se right traverse karte hain.

Maintain karte hain:

* `psum` = current window ka sum
* `j` = left boundary
* `prelen` = ab tak mili shortest valid subarray ki length

Agar:

`psum > target`

to left se elements remove karte hain:

`psum -= arr[j]`

aur `j++`.

Agar:

`psum == target`

to current window valid hai.

Uski length:

`i - j + 1`

hai.

Hum:

`prelen = min(prelen, i - j + 1)`

kar dete hain.

Phir:

`prefixmin[i] = prelen`

store karte hain.

### Step 2: `suffixmin` banana

Ab same process right se left karte hain.

Maintain karte hain:

* `ssum` = current window ka sum
* `j` = right boundary
* `suflen` = shortest valid subarray ki length

Agar:

`ssum > target`

to right side se elements remove karte hain.

Agar:

`ssum == target`

to current window valid hai.

Uski length:

`j - i + 1`

hogi.

Phir:

`suflen = min(suflen, j - i + 1)`

aur:

`suffixmin[i] = suflen`

store karte hain.

### Step 3: Dono Sides Combine Karna

Har split check karte hain:

`i | i+1`

Left side:

`prefixmin[i]`

Right side:

`suffixmin[i+1]`

Agar dono valid hain:

`prefixmin[i] + suffixmin[i+1]`

possible answer hai.

Sabhi splits me se minimum answer le lenge.

Agar koi valid pair nahi mila, to `-1` return karenge.

## Why It Works

Har do non-overlapping subarrays ke beech ek split point zaroor hota hai.

Us split ke liye:

* `prefixmin[i]` left ka shortest valid subarray deta hai.
* `suffixmin[i+1]` right ka shortest valid subarray deta hai.

Hum har possible split check kar rahe hain, isliye optimal pair miss nahi hoga.

Positive elements hone ki wajah se Sliding Window efficiently target sum find kar sakta hai.

## Dry Run

`arr = [7,3,4,7]`

`target = 7`

Valid subarrays:

* `[7]` → length `1`
* `[3,4]` → length `2`
* `[7]` → length `1`

First element ke baad split karne par:

`prefixmin[0] = 1`

aur:

`suffixmin[1] = 1`

So:

`1 + 1 = 2`

Answer `2` hai.

## Complexity

**Time:** `O(n)`

* Left-to-right sliding window → `O(n)`
* Right-to-left sliding window → `O(n)`
* Final split traversal → `O(n)`

Total → `O(n)`.

**Space:** `O(n)`

`prefixmin` aur `suffixmin` ke liye space chahiye.

## Edge Cases

* Sirf ek target-sum subarray hai → `-1`.
* Do valid subarrays overlap karte hain → valid pair nahi hai.
* Multiple valid subarrays hain → minimum total length choose karenge.
* Do single-element subarrays target ke equal hain → answer `2`.
* Koi target-sum subarray nahi hai → `-1`.

## Interview Takeaway

Is problem ka main combination hai:

**Sliding Window + Prefix/Suffix Minimum + Split Point**

Har pair ko directly compare karne ke bajay hum left aur right side ka best subarray pehle store kar lete hain.

Isse solution `O(n)` time me solve ho jata hai.
