// gfg link: https://www.geeksforgeeks.org/problems/coverage-of-all-zeros-in-a-binary-matrix4024/1
class Solution {
    public int findCoverage(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        int[] colTotal = new int[n];
        for(int i=0;m>i;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==1){
                    colTotal[j]+=1;
                }
            }
        }
        int[] colPrefix = new int[n];
        int ans=0;
        for(int i=0;i<m;i++){
            int rowTotal = 0;
            for (int j = 0; j < n; j++) {
               if (mat[i][j] == 1) rowTotal++;
            }
            int leftones=0;
            for(int j=0;j<n;j++){
                if(mat[i][j]==0){
                    if(leftones>0) ans++;
                    if(colTotal[j]>colPrefix[j]) ans++;
                    if(colPrefix[j]>0) ans++;
                    if(rowTotal>leftones) ans++;
                }
                else{
                    colPrefix[j]+=1;
                    leftones++;
                }
            }
        }
        return ans;
    }
}
"""
Complexity Summary
  Time Complexity: (O(M * N))
  You iterate through the M × N matrix exactly twice.
  Each inner operation inside the nested loops runs in O(1) constant time.
Space Complexity: O(N)
  The extra memory is used by two 1D tracking arrays (colTotal and colPrefix), each of size equal to the number of columns N.
"""
