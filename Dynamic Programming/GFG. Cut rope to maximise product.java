// link:https://www.geeksforgeeks.org/problems/max-rope-cutting1312/1
//Memoixation solution 
class Solution {
    int dp[];
    public int maxProduct(int n) {
        // code here
        dp=new int[n+1];
        Arrays.fill(dp,-1);
        return products(n);
    }
    public int products(int n){
        if(n==1) return 1;
        if(dp[n]!=-1) return dp[n];
        int ans=0;
        for(int i=1;i<n;i++){
            ans=Math.max(ans,Math.max(products(n-i)*i,(n-i)*i));
        }
        return dp[n]=ans;
    }
}

"""
Imagine you are trying to break the number n into parts to get the biggest multiplication product.
  1. The Strategy (Break vs. Keep)
     When you chop a piece of size i off from a number n, you are left with a remaining piece of size (n - i).
     You have exactly two choices for that remaining piece:
      ->Keep it whole: Just multiply it directly  (n - i) * i.
      ->Break it further: Call your function recursively to find its best split  products(n - i) * i.
     Your code loops through every possible cut i (from \(1\) up to \(n-1\)), calculates both choices, and tracks the absolute highest product found.
  2. The Power of the Cache (dp array)
  Without a cache, your code recalculates the same numbers repeatedly.
  For example, calculating products(10) will trigger products(5) dozens of separate times, making your code terribly slow.
  To fix this, we use a memoization notebook called dp:
    ->Before solving any number n, we look at our notebook: if (dp[n] != -1) return dp[n];
    ->If the answer is written there, we grab it instantly and skip all calculations.
    ->If it is blank (-1), we do the math loop, and right before returning, we write it down for future reference: return dp[n] = ans;
  """
  """
  Complexity Analysis
 - Time Complexity: (O(N^2))
   -> Why? The dp cache ensures we calculate the answer for each number from 1 to N exactly once. For each number, the for loop runs up to N times.
 - Space Complexity: (O(N))
   -> Why? We use a 1D array of size (N + 1) for the cache, and the recursive function call stack grows up to a maximum depth of (N).
