# Explanation

## Intuition

Do rectangles tab overlap nahi karte jab ek rectangle doosre se completely:

- left mein ho
- right mein ho
- upar ho
- neeche ho

Agar inmein se koi bhi condition true nahi hai, to rectangles ka positive-area overlap hoga.

Important baat ye hai ki agar rectangles sirf edge ya corner par touch kar rahe hain, to woh overlap nahi maane jayenge.

## Rectangle Coordinates

Rectangle:

`[x1, y1, x2, y2]`

mein:

- `x1` → left boundary
- `y1` → bottom boundary
- `x2` → right boundary
- `y2` → top boundary

For `rect1`:

- `rect1[0]` = x1
- `rect1[1]` = y1
- `rect1[2]` = x2
- `rect1[3]` = y2

Same way `rect2` ke coordinates hain.

## Approach

Hum directly intersection area calculate nahi karenge.

Instead, hum check karenge ki rectangles **kab definitely overlap nahi karte**.

### Case 1: rect1, rect2 ke completely left mein hai

Condition:

`rect1[2] <= rect2[0]`

Agar rect1 ka right edge, rect2 ke left edge se pehle ya exactly usi position par hai, to positive width ka overlap nahi hoga.

Yahan `<=` important hai.

Agar dono edges equal hain, to rectangles sirf touch kar rahe hain.

### Case 2: rect1, rect2 ke completely neeche hai

Condition:

`rect1[3] <= rect2[1]`

Agar rect1 ka top edge, rect2 ke bottom edge se pehle ya exactly usi position par hai, to positive height ka overlap nahi hoga.

### Case 3: rect2, rect1 ke completely left mein hai

Condition:

`rect2[2] <= rect1[0]`

### Case 4: rect2, rect1 ke completely neeche hai

Condition:

`rect2[3] <= rect1[1]`

Agar inmein se koi bhi condition true hoti hai, hum `false` return karenge.

Otherwise `true` return karenge.

## Dry Run

### Example 1

`rect1 = [0,0,2,2]`

`rect2 = [1,1,3,3]`

Check:

`rect1[2] <= rect2[0]`

`2 <= 1` → false

Check:

`rect1[3] <= rect2[1]`

`2 <= 1` → false

Check:

`rect2[2] <= rect1[0]`

`3 <= 0` → false

Check:

`rect2[3] <= rect1[1]`

`3 <= 0` → false

Koi bhi non-overlap condition true nahi hui.

Therefore:

`true`

### Example 2

`rect1 = [0,0,1,1]`

`rect2 = [1,0,2,1]`

Check:

`rect1[2] <= rect2[0]`

`1 <= 1` → true

Matlab rect1 ka right edge aur rect2 ka left edge exactly same hai.

Dono sirf edge par touch kar rahe hain.

Intersection area `0` hai.

Therefore:

`false`

## Why It Works

Positive-area overlap ke liye rectangles ko dono directions mein overlap karna zaroori hai:

1. X-axis direction
2. Y-axis direction

Agar kisi bhi axis par ek rectangle doosre se separate hai, to overlap possible nahi hai.

Hamari four conditions exactly ye four separation cases cover karti hain:

- left
- right
- above
- below

Agar koi separation nahi hai, to rectangles dono dimensions mein overlap kar rahe hain.

Therefore, positive-area overlap exist karega.

## Time Complexity

Sirf constant number of comparisons hain.

**Time Complexity: `O(1)`**

## Space Complexity

Koi extra data structure use nahi kiya.

**Space Complexity: `O(1)`**

## Edge Cases

### 1. Edge Par Touch

Example:

`rect1 = [0,0,1,1]`

`rect2 = [1,0,2,1]`

Dono ek edge share karte hain, lekin area overlap nahi hai.

Answer:

`false`

### 2. Corner Par Touch

Agar ek rectangle ka corner doosre rectangle ke corner ko touch karta hai, intersection area zero hota hai.

Answer:

`false`

### 3. Ek Rectangle Doosre Ke Andar

Example:

`rect1 = [0,0,5,5]`

`rect2 = [1,1,2,2]`

Yahan positive area ka overlap hai.

Answer:

`true`

## Interview Takeaway

"Main intersection area calculate karne ke bajay four non-overlap cases check karunga: left, right, above aur below. Agar koi bhi condition true hai to rectangles overlap nahi karte, otherwise unka positive-area overlap hoga."
