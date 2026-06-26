# 🔄 Count Distinct Subsequences (Top-Down DP)

### 💡 Core Intuition
To count how many times string `s2` appears as a subsequence in `s1`, use a **take / non-take** decision tree at each index `i` of `s1`:
* **Take:** If `s1[i] == s2[j]`, match the characters and move both pointers forward (`i+1, j+1`).
* **Non-Take:** Regardless of a match, skip `s1[i]` and look for a match for `s2[j]` further down the string (`i+1, j`).

---

### ⚠️ The Look-up Trap
Always validate string boundary conditions (`i == s1.length()` or `j == s2.length()`) **before** checking the `dp[i][j]` cache. Checking the cache early triggers an `ArrayIndexOutOfBoundsException` because `j` hits the array index boundary when matching the last character.

---

### 🔄 Approach Breakdown

#### 1. Dynamic Programming State Definition
* **State Representation:** `dp[i][j]` stores the total number of valid ways to form the suffix substring `s2[j...]` using the suffix substring `s1[i...]`.
* **State Initialization:** A 2D array of size `[s1.length()][s2.length()]` is filled with `-1` to identify uncomputed pathways.

#### 2. Terminal Conditions (Base Cases)
* **Success Boundary (`j == s2.length()`):** The entire target string `s2` has been successfully matched. This complete branch returns a value of `1`.
* **Failure Boundary (`i == s1.length()`):** The source string `s1` is completely exhausted, but characters remain unmatched in `s2`. This incomplete branch returns a value of `0`.

#### 3. Transition Decisions
* **When characters match (`s1[i] == s2[j]`):** The total ways for this state is the sum of consuming the match (`take`) plus skipping the character to look for other matches (`nontake`).
* **When characters mismatch (`s1[i] != s2[j]`):** The algorithm is forced to skip the current character of `s1` (`nontake`).

---

### 📊 Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N \times M)$  
  *Where $N$ is the length of $s1$ and $M$ is the length of $s2$.*  
  Each individual subproblem coordinate state `(i, j)` is calculated exactly once due to the lookup cache grid. Each lookup computation runs in $\mathcal{O}(1)$ time.
* **Space Complexity:** $\mathcal{O}(N \times M)$  
  Required memory space to maintain the 2D cache grid framework. The maximum call stack depth allocation is safely bounded by $\mathcal{O}(N)$.
