//leetcode link:https://leetcode.com/problems/process-string-with-special-operations-ii/description/?envType=daily-question&envId=2026-06-17
class Solution {
    public char processStr(String s, long k) {
        long l=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='a' && s.charAt(i)<='z'){
                l++;
            }
            else if(s.charAt(i)=='*'){
                if(l>0) l--;
            }
            else if(s.charAt(i)=='#'){
                l=2*l;
            }
            else{
                continue;
            }
        }
        if(k>=l) return '.';
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)>='a' && s.charAt(i)<='z'){
                l--;
            }
            else if(s.charAt(i)=='*'){
                l++;
            }
            else if(s.charAt(i)=='#'){
                l/=2;
                if(k>=l) k=k-l;
                
            }
            else {
                k=l-k-1;
            }
            if(k==l) return s.charAt(i);
        }
        return '.';
    
    }
}
"""
The code breaks down the problem into two distinct steps:
  Step 1: Forward Pass (Calculate Total Length)
  The first for loop goes from left to right to calculate the final length l of the string after all operations:
   -> Lowercase letters (a-z): Increments the current length (l++).
   -> Asterisk (*): Acts as a backspace. It removes the last character by decrementing length (l--).
   -> Hash (#): Duplicates the entire existing string, which doubles the length (l = 2 * l).
  If the requested index k is greater than or equal to the final length l, it safely returns '.'.
  Step 2: Backward Pass (Locate the k-th Character)
  The second for loop moves backwards from the end of the input string to trace exactly where the k-th character originated:
    -> Reversing Operations: As it moves backward, it reverses the forward math (e.g., # divides l by 2; * increments l).
    -> The Magic of # (Duplication): If a # operation doubled the string, the string is split into two identical halves.
       -If k falls into the second half (k >= l), it maps k back to its matching position in the first half by doing k = k - l.
    -> The Target Match: When k matches the current tracked length boundary (k == l), it means the current character s.charAt(i) is exactly the one responsible for the k-th position. The code returns it instantly.
  """
"""
Complexity Analysis
  Time Complexity: \(O(N)\)
  The code runs exactly two independent linear loops over the string of length \(N\). 
  There are no nested loops, making it highly efficient.
  
  Space Complexity: \(O(1)\)
  It only uses a few primitive variables (l, k, i).
  It does not create any new strings or use extra array data structures, resulting in optimal constant space memory usage.
"""  
