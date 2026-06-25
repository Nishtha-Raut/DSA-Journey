### ⚠️ Interview Trap: Static State Pollution

* **The Problem:** Declaring result collections (e.g., `ArrayList`) as `static` globals retains data across test runs.
* **The Symptom:** Passes local tests, but fails on online judges (LeetCode/GFG) with weirdly bloated outputs.
* **The Cause:** Online judges run test cases sequentially in the same JVM session without resetting `static` fields. So previous inputs are already stored while running next.
* **The Fix:** Instantiate the collection locally OR explicitly **reinitialize/clear** the static variable inside the main method before any logic starts.

```java
// ❌ BAD: State persists between runs
class Solution {
    static ArrayList<Integer> list = new ArrayList<>(); 
}

//  GOOD (Option 1): Reinitialize/Clear the static list at the start
class Solution {
    static ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> solve() {
        list = new ArrayList<>(); // OR list.clear();
        dfs();
        return list;
    }
}

//  GOOD (Option 2): Fresh local instance passed as a reference
class Solution {
    public ArrayList<Integer> solve() {
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(ans);
        return ans;
    }
}
```
