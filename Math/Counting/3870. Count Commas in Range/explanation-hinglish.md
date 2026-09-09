# Explanation

## Intuition

Comma sirf un numbers mein aata hai jinke paas kam se kam 4 digits hain.

`1` se `999` tak kisi number mein comma nahi hota.

`1000` se `n` tak har number mein exactly one comma hota hai, kyunki constraint hai:

`n <= 10^5`

Isliye humein bas `1000` se `n` tak numbers count karne hain.

## Approach

- Agar `n < 1000` hai, to koi comma nahi hoga, isliye `0` return karo.
- Agar `n >= 1000` hai, to comma wale numbers hain:

  `1000, 1001, ..., n`

- In numbers ki count hogi:

  `n - 1000 + 1`

- Isko simplify karne par:

  `n - 999`

Isliye answer `n - 999` hoga.

## Dry Run

### Example 1

`n = 1002`

Comma wale numbers:

`1000, 1001, 1002`

Total:

`1002 - 999 = 3`

Answer = `3`

### Example 2

`n = 998`

Since:

`998 < 1000`

Isliye koi comma nahi hai.

Answer = `0`

## Why It Works

`1` se `999` tak sabhi numbers mein 4 digits se kam hain, isliye unmein comma nahi aata.

`1000` se `100000` tak har number mein exactly one comma aata hai.

Isliye `1000` se `n` tak numbers ki count hi total commas ki count hai.

## Complexity

- Time: `O(1)`
- Space: `O(1)`

## Edge Cases

- `n = 1` → `0`
- `n = 999` → `0`
- `n = 1000` → `1`
- `n = 1002` → `3`

## Interview Takeaway

Interview mein bol sakte ho:

"Since `n` is at most `10^5`, numbers from `1` to `999` have no commas, and every number from `1000` to `n` has exactly one comma. So the answer is `n - 999` if `n >= 1000`, otherwise `0`."
