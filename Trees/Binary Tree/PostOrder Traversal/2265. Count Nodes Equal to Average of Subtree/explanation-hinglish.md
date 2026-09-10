# Explanation

## Intuition

Har node ke liye humein check karna hai ki uski value uske complete subtree ke average ke equal hai ya nahi.

Average nikalne ke liye humein 2 cheezein chahiye:

1. Subtree ka total sum
2. Subtree mein total nodes

Iske liye hum **postorder traversal** use karenge:

`Left → Right → Root`

Reason ye hai ki current node ka average nikalne ke liye pehle uske left aur right subtree ki information chahiye.

Hamari `solve()` function 3 values return karti hai:

- `arr[0]` → subtree ka sum
- `arr[1]` → subtree mein total nodes
- `arr[2]` → subtree mein valid nodes ki count

## Approach

Har node ke liye:

1. Left subtree ko recursively solve karo.
2. Right subtree ko recursively solve karo.
3. Current subtree ka sum nikalo.
4. Current subtree mein total nodes nikalo.
5. Left aur right subtree ke valid nodes ko add karo.
6. Average calculate karo:
   `sum / count`
7. Check karo ki average `root.val` ke equal hai ya nahi.
8. Agar equal hai, valid-node count ko increment karo.
9. Ye teen values parent node ko return kar do.

## Returned Array Ka Meaning

`solve()` jo array return karta hai usme:

`arr[0]` = subtree ka sum

`arr[1]` = subtree mein nodes ki count

`arr[2]` = valid nodes ki count

Example ke liye agar subtree mein values hain:

`0, 1, 8`

To:

Sum = `9`

Count = `3`

Valid nodes = `2`

To return hoga:

`{9, 3, 2}`

## Leaf Node

Agar node ke left aur right dono children nahi hain, to woh leaf node hai.

Leaf ka subtree sirf woh khud hota hai.

Isliye:

`average = root.val / 1 = root.val`

Matlab har leaf node automatically valid hota hai.

Isliye code leaf ke liye directly return karta hai:

`{root.val, 1, 1}`

## Dry Run

Tree:

        4
       / \
      8   5
     / \   \
    0   1   6

Hum ise postorder mein process karenge.

### Step 1: Node 0

Node 0 leaf hai.

Sum = `0`

Count = `1`

Average:

`0 / 1 = 0`

`0 == 0`

Isliye valid hai.

Return:

`{0, 1, 1}`

### Step 2: Node 1

Node 1 leaf hai.

Sum = `1`

Count = `1`

Average:

`1 / 1 = 1`

`1 == 1`

Isliye valid hai.

Return:

`{1, 1, 1}`

### Step 3: Node 8

Left result:

`{0, 1, 1}`

Right result:

`{1, 1, 1}`

Ab node 8 ka subtree calculate karte hain.

Sum:

`0 + 1 + 8 = 9`

Count:

`1 + 1 + 1 = 3`

Children se valid nodes:

`1 + 1 = 2`

Average:

`9 / 3 = 3`

Ab check:

`3 != 8`

Isliye node 8 valid nahi hai.

Return:

`{9, 3, 2}`

### Step 4: Node 6

Node 6 leaf hai.

Sum = `6`

Count = `1`

Average:

`6 / 1 = 6`

`6 == 6`

Isliye valid hai.

Return:

`{6, 1, 1}`

### Step 5: Node 5

Node 5 ka sirf right child hai.

Left result:

`{0, 0, 0}`

Right result:

`{6, 1, 1}`

Sum:

`0 + 6 + 5 = 11`

Count:

`0 + 1 + 1 = 2`

Children se valid nodes:

`0 + 1 = 1`

Average:

`11 / 2 = 5`

Java integer division ki wajah se result `5` aayega.

Ab check:

`5 == 5`

Isliye node 5 valid hai.

Return:

`{11, 2, 2}`

### Step 6: Node 4

Left result:

`{9, 3, 2}`

Right result:

`{11, 2, 2}`

Ab complete subtree ka calculation:

Sum:

`9 + 11 + 4 = 24`

Count:

`3 + 2 + 1 = 6`

Children se valid nodes:

`2 + 2 = 4`

Average:

`24 / 6 = 4`

Ab check:

`4 == 4`

Isliye node 4 bhi valid hai.

Final result:

`{24, 6, 5}`

Array ka third value answer hota hai.

Therefore:

`Answer = 5`

## Why It Works

Postorder traversal ki wajah se current node ko process karne se pehle left aur right subtree ki complete information mil jati hai.

Phir hum dono subtree ke sum aur count ko combine karke current subtree ka sum aur count nikalte hain.

Uske baad current node ka average calculate karke check karte hain ki:

`average == root.val`

Agar equal hai to valid count ko increase kar dete hain.

Is tarah har node exactly ek baar check hota hai.

## Time Complexity

Har node ko exactly ek baar visit karte hain.

**Time Complexity: `O(n)`**

jahan `n` total nodes hain.

## Space Complexity

Recursive calls ke liye recursion stack use hota hai.

**Space Complexity: `O(h)`**

jahan `h` tree ki height hai.

Worst case mein tree completely skewed ho sakta hai:

`h = n`

To worst-case space:

`O(n)`

## Edge Cases

### 1. Single Node

Agar tree mein sirf ek node hai:

`average = value / 1 = value`

Isliye woh node valid hoga.

### 2. Leaf Node

Har leaf node valid hota hai kyunki uska subtree sirf woh khud hota hai.

### 3. Non-integer Average

Problem mein average ko floor karna hai.

Example:

`11 / 2 = 5`

Java integer division automatically decimal part remove kar deti hai, isliye `11 / 2` ka result `5` aata hai.

## Interview Takeaway

"Main postorder traversal use karunga kyunki current node ko process karne ke liye mujhe left aur right subtree ki information pehle chahiye. Har subtree se main sum, node count aur valid nodes ki count return karunga. Phir current subtree ka average calculate karke check karunga ki woh current node ki value ke equal hai ya nahi."
