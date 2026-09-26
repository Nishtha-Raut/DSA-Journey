# Intuition

Hume string ke andar jitne bhi `(key)` pairs hain unko unki corresponding value se replace karna hai.

Sabse pehle problem ye hai ki kisi key ki value quickly kaise find karein.

`knowledge` mein `10^5` tak entries ho sakti hain, isliye har key ke liye poori list search karna inefficient hoga.

Isliye hum `HashMap` banayenge:

```text
key → value
```

Example:

```text
"name" → "bob"
"age"  → "two"
```

Phir string ko left se right scan karenge.

Jab `(` milega, uska index `open` mein store karenge.

Jab `)` milega, to `(` aur `)` ke beech ka text key hoga.

Phir:

- Key map mein hai → uski value append karo.
- Key map mein nahi hai → `?` append karo.
- Brackets ke bahar normal characters hain → unhe directly answer mein append karo.

# Approach

1. `knowledge` ke saare key-value pairs ko `HashMap` mein store karo.
2. Result banane ke liye `StringBuilder` banao.
3. `open` variable mein latest `(` ka index store karo.
4. String ko traverse karo.
5. Agar `(` mile:
   - `open = i`
6. Agar `)` mile:
   - `open + 1` se `i` tak key extract karo.
   - Map mein key search karo.
   - Value mile to append karo.
   - Nahi mile to `?` append karo.
7. Agar character bracket ke bahar hai, to directly append karo.
8. Final `StringBuilder` return karo.

# `open` Variable Kyun?

Suppose:

```text
s = "hi(name)"
```

Jab `(` milega, uska index store kar lenge.

Phir `)` milne par:

```text
(name)
 ^   ^
 |   |
open i
```

In dono ke beech ka text key hai.

Isliye:

```text
s.substring(open + 1, i)
```

se:

```text
"name"
```

mil jayega.

# Dry Run

Suppose:

```text
s = "(name)is(age)yearsold"
knowledge = [["name","bob"],["age","two"]]
```

Pehla pair:

```text
(name)
```

Key:

```text
"name"
```

Map mein `"name"` ki value:

```text
"bob"
```

So answer mein:

```text
bob
```

append hoga.

Uske baad `is` bracket ke bahar hai, to directly append hoga.

Next:

```text
(age)
```

Key:

```text
"age"
```

Map se value:

```text
"two"
```

milti hai.

Final answer:

```text
bobistwoyearsold
```

# Unknown Key

Suppose:

```text
s = "hi(name)"
knowledge = [["a","b"]]
```

Map mein `"name"` nahi hai.

Isliye:

```text
(name)
```

ko:

```text
?
```

se replace karenge.

Result:

```text
hi?
```

# Same Key Multiple Times

Suppose:

```text
s = "(a)(a)(a)aaa"
knowledge = [["a","yes"]]
```

Har `(a)` ke liye same map lookup hoga.

Result:

```text
yesyesyesaaa
```

Lekin last wale `aaa` bracket ke andar nahi hain, isliye unhe replace nahi karna hai.

# `StringBuilder` Kyun?

Java mein `String` immutable hota hai.

Agar baar-baar:

```text
result = result + something
```

karenge, to unnecessary intermediate strings ban sakti hain.

`StringBuilder` efficiently result construct karne deta hai.

# Complexity

Maan lo:

- `n = s.length()`
- `m = knowledge.length`

HashMap banane mein:

`O(m)`

String scan karne mein:

`O(n)`

HashMap lookup average:

`O(1)`

Therefore:

**Time Complexity: `O(n + m)`**

HashMap aur result ke liye extra space:

**Space Complexity: `O(n + m)`**

# Interview Takeaway

Is problem mein teen important concepts hain:

```text
HashMap
   ↓
Fast key → value lookup

String Traversal
   ↓
(key) identify karna

StringBuilder
   ↓
Efficient result banana
```

Overall flow:

```text
knowledge
    ↓
HashMap

String scan karo
    ↓
(key) mila?
    ↓
key lookup karo
    ↓
value / ?
    ↓
result mein append
```
