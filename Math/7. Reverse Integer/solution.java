//leetcode link: https://leetcode.com/problems/reverse-integer/
class Solution {
    public int reverse(int x) {
        boolean neg=false;
        if(x<0) neg=true;
        x=Math.abs(x);
        long ans=0;
        while(x>0){
            ans=ans*10+(x%10);
            x/=10;
        }
        if(neg) ans=-ans;
        if(ans>Math.pow(2,31)-1 || ans<-Math.pow(2,31)) {
            return 0;
        }
        return (int)ans;
    }
}
