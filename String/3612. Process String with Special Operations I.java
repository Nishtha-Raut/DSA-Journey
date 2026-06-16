// leetcode line: https://leetcode.com/problems/process-string-with-special-operations-i/description/?envType=daily-question&envId=2026-06-16
class Solution {
    public String processStr(String s) {
        StringBuilder sb=new StringBuilder("");
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='a' && s.charAt(i)<='z'){
                sb.append(s.charAt(i));
            }
            else if(sb.length()>0){
                if(s.charAt(i)=='*'){
                    sb.deleteCharAt(sb.length()-1);
                }
                else if(s.charAt(i)=='#'){
                    sb.append(sb.toString());
                }
                else{
                    sb.reverse();
                }
            }
            
        }
        return sb.toString();
    }
}
"""
⏱️ Time Complexity
  -> Worst Case:{O}(2^N)(Exponential) — Triggered if # appears repeatedly, doubling the string size every time.
  -> Average Case: {O}(N^2) (Quadratic) — Triggered by the else block because sb.reverse() takes {O}(N) time and runs inside a loop.
  -> Best Case: {O}(N) (Linear) — If the input only contains letters a-z and backspaces *.
💾 Space Complexity
  -> Worst Case: mathcal{O}(2^N) — The memory doubles exponentially with every #.
  -> Normal Case: \(\mathcal{O}(N)\) — The memory scales linearly to store the characters in sb.
