"""
  Even though this problem can be solved directly using only two variables, 
solving it with memoization and tabulation is important for building a solid foundation in dynamic programming."""
// Memoization
// Time Complexity: \(O(n)\)
// Space Complexity: \(O(n)\)
class Solution {
    int dp[];
    public int climbStairs(int n) {
        dp=new int[n+1];
        int ans=count(n);
        return ans;
    }
    public int count(int n){
        if(n<=1) return 1;
        int ways=0;
        if(dp[n]!=0) return dp[n];
        int onejump=count(n-1);
        int twojump=count(n-2);
        return dp[n]=onejump+twojump;
    }
}

// Tabulation way
// Time Complexity: \(O(n)\)
// Space Complexity: \(O(n)\)
class Solution {
    public int climbStairs(int n) {
        int dp[]=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
