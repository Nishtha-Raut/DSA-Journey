// leetcode link: https://leetcode.com/problems/merge-two-sorted-lists/description/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
"""
  Time Complexity: \(\mathcal{O}(N + M)\)
  Space Complexity: \(\mathcal{O}(1)\) (Constant Space) """
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null) return list2;
        if(list2==null) return list1;
        ListNode temp=new ListNode(-1);
        ListNode ans=temp;
        while(list1!=null && list2!=null){
            if(list1.val<=list2.val){
                temp.next=list1;
                list1=list1.next;
            }
            else{
                temp.next=list2;
                list2=list2.next;
            }
            temp=temp.next;
        }
        if(list1!=null){
            temp.next=list1;
        }
        if(list2!=null){
            temp.next=list2;
        }
        return ans.next;
    }
}
"""
  Algorithm: Merge Two Sorted Lists (In-Place)
  1. Handle Empty Lists (Base Cases)
     i. If list1 is empty, return list2.
     ii. If list2 is empty, return list1.
  2. Initialize Tracker Nodes
     i. Create a temporary Dummy Node to act as a fixed starting anchor.
     ii. Create a Tail Pointer (temp) initialized to this Dummy Node to build the merged list.
     iii. Create a Head Reference (ans) pointing to the Dummy Node to remember the start of the list.
  3. Compare and Link (Main Loop)
     i. While both list1 and list2 are not empty:
         * Compare the values of the current nodes of list1 and list2.
         * If the value in list1 is smaller or equal:
             -> Connect the Tail Pointer's next link to list1.
             -> Advance list1 to its next node.
         * Else (the value in list2 is smaller):
             -> Connect the Tail Pointer's next link to list2.
             -> Advance list2 to its next node.
         * Move the Tail Pointer forward to its newly attached node.
  4. Attach the Remaining Tail
         * Once the loop ends (one list is exhausted):
             -> If list1 still has elements, connect the Tail Pointer's next link directly to the remainder of list1.
             -> If list2 still has elements, connect the Tail Pointer's next link directly to the remainder of list2.
  5. Return Result
     i.Return the node immediately following the Dummy Node (ans.next).
  """
