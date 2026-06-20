//leetcode link: https://leetcode.com/problems/candy/
class Solution {
    public int candy(int[] ratings) {
        int n=ratings.length;
        int arr[]=new int[n];
        Arrays.fill(arr,1);
        for(int i=1;n>i;i++){
            if(ratings[i]>ratings[i-1]){
                arr[i]=arr[i-1]+1;
            }
        }
        for(int i=n-2;i>=0;i--){
            if(ratings[i]>ratings[i+1]){
                arr[i]=Math.max(arr[i],arr[i+1]+1);
            }
        }
        int ans=0;
        for(int i=0;arr.length>i;i++){
            ans+=arr[i];
        }
        return ans;
    }
}
"""
Explanation:
1. Initialise: Give every single kid 1 candy as a baseline.
2. Forward Pass (Left-to-Right): Loop forward. 
  If a kid has a higher rating than the kid on their left, give them one more candy than that left neighbor.
3. Backward Pass (Right-to-Left): Loop backward. 
  If a kid has a higher rating than the kid on their right, check if they need more candies. 
  Use Math.max to give them enough to beat the right neighbor without ruining their forward-pass advantage.
  Finally, sum up all the candies in the array for the total.
"""
"""
Complexities
  Time Complexity: O(N) because it loops through the array of N children exactly three times.
  Space Complexity: O(N) to store the candy count for each child in the arr array.
"""
