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
class Solution {
    public int pairSum(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode twin2=reverse(slow.next);
        ListNode twin1=head;
        int max=0;
        while(twin2!=null){
            max=Math.max(max, twin1.val+twin2.val);
            twin1=twin1.next;
            twin2=twin2.next;
        }
        return max;
    }
    public ListNode reverse(ListNode head){
        ListNode reverse=null;
        ListNode remain=head;
        while(remain!=null){
            ListNode temp=remain.next;
            remain.next=reverse;
            reverse=remain;
            remain=temp;
        }
        return reverse;
    }
}
"""
⏱️ Time Complexity: O(N)
  The overall time complexity is linear, where N is the total number of nodes in the linked list. 
  The code processes the list in three distinct, sequential phases:
  1. Finding the Middle Element:
      i.The fast pointer moves two nodes at a time while the slow pointer moves one node at a time.
      ii.This traversal scans through the list once and takes \(\frac{N}{2}\) operations.
  2. Reversing the Second Half:
      i.The reverse method iterates node-by-node through the second half of the list.
      ii.This takes \(\frac{N}{2}\) operations.
  3. Calculating Max Twin Sum:
      i.The final while(twin2 != null) loop advances twin1 and twin2 together to calculate the sums.
      ii.Since twin2 represents exactly half the list, this takes N/2 operations.
          Total Time =(N/2)+(N/2)+(N/2)=(3N/2) => O(N)\)
  💾 Space Complexity: O(1)
  The space complexity is constant, which is the most optimal space footprint possible for this problem.
      i.In-Place Reversal: Your reverse function modifies the existing node pointers 
        (remain.next = reverse) directly rather than creating new node objects.
      ii.Pointers Only: The algorithm only allocates memory for a few reference variables (slow, fast, twin1, twin2, temp, reverse, remain) and an integer (max).
      iii.No auxiliary data structures like ArrayList, arrays, or stacks are used.
