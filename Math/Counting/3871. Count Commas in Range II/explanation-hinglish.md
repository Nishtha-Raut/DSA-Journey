# Explanation

## Intuition

Previous problem mein `n` chhota tha, isliye `1000` se `n` tak har number mein exactly one comma tha.

Lekin yahan:

`n <= 10^15`

hai, isliye kuch numbers mein multiple commas ho sakte hain.

For example:

`1,000` → 1 comma

`1,000,000` → 2 commas

`1,000,000,000` → 3 commas

Isliye humein har comma position ka contribution separately count karna hai.

## Key Observation

First comma `1000` par aata hai.

Isliye:

`1000` se `n` tak ke sabhi numbers kam se kam 1 comma contribute karte hain.

Second comma `1,000,000` par aata hai.

Isliye:

`1,000,000` se `n` tak ke sabhi numbers ek extra comma contribute karte hain.

Similarly:

- `1000` se → 1 comma
- `1000000` se → ek additional comma
- `1000000000` se → ek additional comma
- `1000000000000` se → ek additional comma

## Approach

Sabse pehle check karo:

`n < 1000`

Agar true hai, to answer `0`.

Otherwise:

1. Find karo ki `n` tak kitne comma levels exist karte hain.
2. Initial threshold `999` rakho.
3. Har comma level ke liye:
   - `n - threshold` ko answer mein add karo.
   - Threshold ko next `1000` level par move karo.
4. Final answer return karo.

Thresholds honge:

`999`

`999999`

`999999999`

`999999999999`

etc.

Kisi threshold `x` ke liye:

`x + 1` se `n` tak total numbers:

`n - x`

honge.

## Dry Run

Maan lo:

`n = 1,000,002`

### First comma

`1000` se `1,000,002` tak sabhi numbers mein at least one comma hai.

Contribution:

`1,000,002 - 999 = 999,003`

### Second comma

`1,000,000` se `1,000,002` tak numbers mein ek extra comma hai.

Contribution:

`1,000,002 - 999,999 = 3`

Total:

`999,003 + 3 = 999,006`

Answer = `999006`

## Why It Works

Hum har number individually check nahi kar rahe.

Instead, har comma position ka contribution calculate kar rahe hain.

`1000` se `n` tak sabhi numbers first comma contribute karte hain.

`1000000` se `n` tak sabhi numbers second comma contribute karte hain.

`1000000000` se `n` tak sabhi numbers third comma contribute karte hain.

Isliye har threshold ke liye `n - threshold + 1` numbers count karke sabko add karna correct answer deta hai.

## Complexity

`1000` ki powers bahut quickly grow karti hain.

Isliye sirf:

`O(log₁₀₀₀ n)`

iterations hongi.

- Time: `O(log₁₀₀₀ n)`
- Space: `O(1)`

## Edge Cases

### n < 1000

Example:

`n = 998`

Kisi number mein comma nahi hai.

Answer = `0`

### n = 1000

Sirf `1,000` mein comma hai.

Answer = `1`

### n = 999999

`1000` se `999999` tak sabhi numbers mein one comma hai.

Answer:

`999999 - 999 = 999000`

### n = 1000000

`1,000,000` mein 2 commas hain.

Isliye ye number first aur second dono comma contributions mein count hoga.

## Interview Takeaway

Interview mein bol sakte ho:

"Main har comma position ko separately count karunga. `1000` se n tak numbers first comma contribute karte hain, `10^6` se n tak second comma contribute karte hain, `10^9` se n tak third comma contribute karte hain, and so on. In sab contributions ko add karke total commas mil jayenge."
