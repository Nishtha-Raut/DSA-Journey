// leetcode link:https://www.geeksforgeeks.org/problems/maximize-dot-product2649/1
class Solution {
    int dp[][];
    public int maxDotProduct(int[] a, int[] b) {
        // code here
        dp=new int[a.length][b.length];
        for(int i=0;dp.length>i;i++){
            Arrays.fill(dp[i],-1);
        }
        int ans=dfs(a,b,0,0);
        return ans;
    }
    public int dfs(int a[], int b[], int i, int j){
        if(j==b.length){
            return 0;
        }
        if(i==a.length){
            return Integer.MIN_VALUE;
        }
        if(dp[i][j]!=-1) return dp[i][j];
        int take=a[i]*b[j]+dfs(a,b,i+1,j+1);
        int nontake=dfs(a,b,i+1,j);
        return dp[i][j]=Math.max(take,nontake);
    }
}
