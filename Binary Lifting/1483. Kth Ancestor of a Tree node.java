""" 
  leetcode link: https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
  Time Complexity:
  Constructor:  {O}(N log N)) (Precomputes binary steps).
  Query: {O}(log K)) (Loops through bits of K).
  Space Complexity
  Total Space: {O}(N log N) (Stored in the 2D ancestors array).
  Query Space: {O}(log K) (Used by the temporary string allocation).
"""
class TreeAncestor {
    int ancestors[][];
    public TreeAncestor(int n, int[] parent) {
        int cols=(int)(Math.log(n)/Math.log(2))+1;
        ancestors=new int[n][cols];
        for(int an[]:ancestors){
            Arrays.fill(an,-1);
        }
        for(int i=0;parent.length>i;i++){
            ancestors[i][0]=parent[i];
        }
        for(int i=1;cols>i;i++){
            for(int node=0;node<n;node++){
                if(ancestors[node][i-1]!=-1){
                    ancestors[node][i]=ancestors[ancestors[node][i-1]][i-1];
                }
            }
        }
    }
    public int getKthAncestor(int node, int k) {
        String str=Integer.toBinaryString(k);
        int pos=0;
        for(int i=str.length()-1;i>=0;i--){
            if(str.charAt(i)=='1'){
                node=ancestors[node][pos];
                if(node==-1) return -1;
            }
            pos++;
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
