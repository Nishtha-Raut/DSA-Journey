//leetcode link: https://www.geeksforgeeks.org/problems/optimal-array--170647/1
class Solution {
    public ArrayList<Integer> optimalArray(int[] arr) {
        // code here
        ArrayList<Integer>ans=new ArrayList<>();
        int presum[]=new int[arr.length];
        int psum=0;
        for(int i=0;i<arr.length;i++){
            psum+=arr[i];
            presum[i]=psum;
            int mid=i/2;
            int median=arr[mid];
            int firsthalf=0;
            if(mid-1>=0) firsthalf=Math.abs(presum[mid-1]-(mid)*median);
            int secondhalf=(presum[i]-presum[mid])-((i-mid)*median);
            ans.add(firsthalf+secondhalf);
        }
        return ans;
    }
}
"""
Complexity Analysis
  Time Complexity: O(N), where N is the length of the array. 
  The solution uses a single loop that iterates through the array exactly once, performing constant time O(1) operations inside.
  Space Complexity: O(N) to store the prefix sum array (presum), excluding the space used for the output list.
  """
  
"""
Solution Explanation
  The problem requires finding the minimum operations to make all elements from index 0 to i equal. 
  For any subarray, the minimum total absolute deviation is achieved by converting all elements to their median.
  The code optimizes this calculation using a prefix sum array:
     1. Tracks Prefix Sums: It builds a presum array on the fly to compute the sum of any subarray in O(1) time.
     2. Finds the Median: For the current subarray 0 to i, the median is always at mid = i / 2 because the input array is sorted.
     3. Splits Calculations: It divides the subarray into two parts around the median to count operations:
         -> First Half: Calculates how much the elements before the median need to change to match the median value.
         -> Second Half: Calculates how much the elements from the median to index i need to change to match the median value.
     4. Aggregates Results: It sums the operations from both halves and adds the total to the answer list.
"""
