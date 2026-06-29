//https://leetcode.com/problems/zigzag-conversion/
class Solution {
    public String convert(String s, int numRows) {
        if(numRows>=s.length()) return s;
        StringBuilder arr[]=new StringBuilder[numRows];
        for(int i=0;i<arr.length;i++){
            arr[i]=new StringBuilder();
        }
        int i=0;
        while(i<s.length()){
            int j=0;
            while(i<s.length() && j<numRows){
                arr[j].append(""+s.charAt(i));
                i++;
                j++;
            }
            j=arr.length-2;
            while(i<s.length() && j>0){
                arr[j].append(""+s.charAt(i));
                i++;
                j--;
            }
        }
        String ans="";
        for(i=0;i<arr.length;i++){
            ans=ans+arr[i].toString();
        }
        return ans;
    }
}
