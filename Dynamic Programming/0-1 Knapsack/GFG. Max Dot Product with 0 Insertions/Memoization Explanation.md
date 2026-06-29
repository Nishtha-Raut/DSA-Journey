# 📈 Maximize Dot Product (Top-Down DP / Knapsack Variant)

### 💡 Core Intuition
Given two arrays `a` and `b` (where $size(a) \ge size(b)$), we want to maximize their dot product by inserting zeros into `b`. This can be modeled as a strict **Take / Non-Take** choice for each element in array `a`:
* **Take:** Multiply $a[i] \times b[j]$ to get a product contribution, and move both pointers forward (`i+1, j+1`).
* **Non-Take:** Skip $a[i]$ (conceptually multiplying it by an inserted $0$), and move only the pointer for array `a` forward (`i+1, j`).

---

### ⚠️ The Integer.MIN_VALUE Underflow Trap
When a choice leads to an invalid pathway (running out of elements in array `a` before matching all elements in `b`), the branch must be heavily penalized. 
Using exactly `Integer.MIN_VALUE` can cause an **integer underflow** bug if your recursive stack adds a positive product value to it later. This flips the extreme negative number into a massive positive number, corrupting the `Math.max()` calculation. Always use a safe infinity penalty value like `-1e8` instead.

---

### 🔄 Approach Breakdown

#### 1. Dynamic Programming State Definition
* **State Representation:** `dp[i][j]` stores the maximum achievable dot product using the remaining elements of array `a` starting from index `i` and array `b` starting from index `j`.
* **State Initialization:** A 2D array of size `[a.length][b.length]` is filled with `-1` to identify uncomputed pathways.

#### 2. Terminal Conditions (Base Cases)
* **Success Boundary (`j == b.length`):** All elements of array `b` have been successfully matched. Remaining unused elements of `a` are skipped (multiplied by inserted $0$s), returning a base value of `0`.
* **Failure Boundary (`i == a.length`):** Array `a` is completely exhausted, but elements remain unmatched in `b`. This is an impossible configuration, so it returns a safe negative penalty (`-100000000`).

#### 3. Connection to the Knapsack Pattern
* **Why it's like Knapsack:** It utilizes a bounded 0/1 choice (Take/Non-Take) for every element in the source array. Choosing an item yields an immediate value reward ($a[i] \times b[j]$) and consumes a resource (advancing the pointer $j$).
* **The Structural Twist:** Unlike a standard Knapsack where item selection order does not matter, this problem enforces a strict sequential arrangement. The resource limit is not a single capacity integer, but a sequential timeline index pointer moving through a fixed target array.

---

### 📊 Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N \times M)$  
  *Where $N$ is the length of array $a$ and $M$ is the length of array $b$.*  
  Each unique coordinate combination subproblem state `(i, j)` is calculated exactly once due to the lookup cache grid. Every individual lookup runs in $\mathcal{O}(1)$ constant time.
* **Space Complexity:** $\mathcal{O}(N \times M)$  
  Required memory space to maintain the 2D cache grid framework. The maximum call stack execution depth is bounded linearly by $\mathcal{O}(N)$.
