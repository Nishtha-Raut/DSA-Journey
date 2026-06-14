//leetcode link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
class Solution {
    public int removeDuplicates(int[] nums) {
        int i=0;
        for(int j=1;nums.length>j;j++){
            if(nums[i]!=nums[j]){
                nums[i+1]=nums[j];
                i++;
            }
        }
        return i+1;
    }
}
"""
  Complexity AnalysisTime Complexity: \(\mathcal{O}(n)\) — The loop visits each array element exactly once.
  Space Complexity: \(\mathcal{O}(1)\) — The modifications occur entirely inside the input array without creating extra structures
  """
