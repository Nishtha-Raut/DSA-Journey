class Solution {
    static int mod=(int)1e9+7;
    public static int countWays(String s1, String s2) {
        // code here
        int dp[][]=new int[s1.length()][s2.length()];
        for(int row[]:dp){
            Arrays.fill(row,-1);
        }
        return countSubSequence(0,0,s1,s2,dp);
    }
    public static int countSubSequence(int i, int j, String s1, String s2,int dp[][]){
        if(j==s2.length()) return 1;
        if(i==s1.length()) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int take=0;
        if(s1.charAt(i)==s2.charAt(j)){
            take=countSubSequence(i+1,j+1,s1,s2,dp);
        }
        int nontake=countSubSequence(i+1,j,s1,s2,dp);
        return dp[i][j]=(take+nontake)%mod;
    }
}
