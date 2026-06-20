//leetcode link: https://leetcode.com/problems/maximum-building-height/?envType=daily-question&envId=2026-06-20
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]>list=new ArrayList<>();
        list.add(new int[]{1,0});
        for(int[] res: restrictions){
            if(res[0]!=1) list.add(res);
        }
        Collections.sort(list,(a,b)->Integer.compare(a[0],b[0]));
        if(list.get(list.size()-1)[0]!=n){
            list.add(new int[]{n,n-1});
        }
        
        int m=list.size();
        for(int i=1;i<m;i++){
            int diff=list.get(i)[0]-list.get(i-1)[0];
            int height=list.get(i-1)[1]+diff;
            list.get(i)[1]=Math.min(height,list.get(i)[1]);
        }
        for(int i=m-2;i>=0;i--){
            int diff=list.get(i+1)[0]-list.get(i)[0];
            int height=list.get(i+1)[1]+diff;
            list.get(i)[1]=Math.min(height, list.get(i)[1]);
        }
        int maxGlobalHeight=0;
        for(int i=0;i<m-1;i++){
            int id1=list.get(i)[0];
            int id2=list.get(i+1)[0];
            int h1=list.get(i)[1];
            int h2=list.get(i+1)[1];
            int maxPeak=(h1+h2+(id2-id1))/2;
            maxGlobalHeight=Math.max(maxGlobalHeight,maxPeak);
        }
        return maxGlobalHeight;
    }
}
"""
Step 1: Look Left-to-Right (The Forward Pass)
  You start from the very first building (Building #1, which must start at ground level, 0 floors) and walk forward. 
  You check each building and say: "Based on how tall the building to my left is, 
  what is the absolute maximum height I could possibly reach by going up 1 floor per step?"
  If the inspector's rule for the current building is lower than that, you use the inspector's rule. 
  You update the heights moving forward.
  
Step 2: Look Right-to-Left (The Backward Pass)
  Now you walk backward from the very last building to the first building. 
  You do the exact same check: "Based on how tall the building to my right is, did I build myself too tall?" 
  If a building on the right is very short, you are forced to shave floors off the building on its left to satisfy the "Step Rule."
Step 3: Find the Peaks in Between
  Now you look at the gaps between the restricted buildings. 
  If Building #3 is allowed to be 2 floors high, and Building #7 is allowed to be 4 floors high, what happens in the empty space between them (Buildings #4, #5, and #6)?
  You can build a "roof triangle" in that gap!
  You start at Building #3 (height 2) and go up as high as possible, floor by floor, until you are forced to start going down to safely land on Building #7 (height 4).
        Peak (Highest Point)
       /\
      /  \
     /    \  Building #7 (Height 4)
    /      \/
   /
Building #3 (Height 2)
  The code checks the middle gap between every single pair of restricted buildings, 
  finds the top peak of these triangles using a quick math formula, and remembers the highest peak it saw. 
  That highest peak is your final answer!
"""
"""
Time Complexity: (O(M log M))
  Sorting: (O(M log M)) to sort the M restrictions by building ID.
  Two-Pass Loops: O(M) to scan forward and backward to update heights.
  Peak Calculation: (O(M)) to find the highest point in the final loop.
  Sorting takes the most work, so it determines the final speed.
Space Complexity: (O(M))
  List Storage: (O(M)) memory to copy the restrictions into an ArrayList.
  Sorting Overhead: (O(M)) or (O(log M)) temporary memory used internally by Java's sorting algorithm (Collections.sort).
  The size of your memory strictly depends on how many restriction rules you are given. 
"""
