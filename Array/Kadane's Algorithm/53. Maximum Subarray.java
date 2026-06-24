// https://leetcode.com/problems/maximum-subarray/
class Solution {
    public int maxSubArray(int[] nums) {
        int currsum=0;
        int globalmax=Integer.MIN_VALUE;
        int j=0;
        for(int i=0;i<nums.length;i++){
            currsum+=nums[i];
            globalmax=Math.max(globalmax,currsum);
            if (currsum < 0) {
                currsum = 0;
            }
        }
        return globalmax;
    }
}
"""
Core Idea: Track running sum (currsum). If currsum drops below 0, discard it and reset to 0 because a negative prefix decreases any future subarray sum.

Complexity AnalysisTime 
Complexity: O(N)— The array is traversed exactly once.
Space Complexity: O(1) — Only two scalar variables (globalMax and currSum) are used.
  """
