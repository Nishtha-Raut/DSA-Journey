// leetcode link: https://leetcode.com/problems/find-the-highest-altitude/description/?envType=daily-question&envId=2026-06-19
class Solution {
    public int largestAltitude(int[] gain) {
        int prev=0;
        int max=0;
        for(int i=0;i<gain.length;i++){
            int altitude=gain[i]+prev;
            max=Math.max(altitude,max);
            prev=altitude;
        }
        return max;
    }
}
"""
  🛠️ How It WorksTracking: 
  It uses prev for current height and max for the highest peak.
  Looping: It checks each gain/loss in the array one by one.
  Updating: It calculates the new height, saves it, and updates max if the new height is higher.
  Result: It returns the highest number recorded.
  ⏱️ Performance
  Time: O(n) (loops through the array once).
  Space: O(1) (uses minimal, constant memory).
"""
