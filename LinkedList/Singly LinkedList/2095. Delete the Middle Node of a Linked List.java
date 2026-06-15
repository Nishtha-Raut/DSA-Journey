//leetcode link:https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/?envType=daily-question&envId=2026-06-15
"""
Time Complexity: ({O}(N))
  The fast pointer skips nodes by two, meaning the loop runs in roughly {N}/{2} steps, scaling linearly with the size of the list.
Space Complexity: ({O}(1))
  The list is modified in place using only two temporary pointer references (fast and slow), consuming constant extra memory.
"""
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
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast=head.next.next;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;
    }
}
"""
Scenario 1: Odd Length List (5 Nodes)
  Input List: [10 -> 20 -> 30 -> 40 -> 50] (Target middle to delete: 30)
  1. Initialization
     ->slow = 10
     ->fast = head.next.next = 30
  2. Loop Execution
     ->Iteration 1:
        -Check: fast != null && fast.next != null (30 and 40 are valid).
        -Update: fast moves to 50 (fast.next.next).
        -Update: slow moves to 20 (slow.next).
     ->Iteration 2 Check:
        -Check: fast != null && fast.next != null → fast is 50, but fast.next is null.
        -Result: Loop breaks.
  3. Deletion Step
     ->Current state: slow is at 20.
     ->Code runs: slow.next = slow.next.next (Changes 20's next pointer from 30 to 40).
     ->Final List: [10 -> 20 -> 40 -> 50] (Successfully deleted 30)
Scenario 2: Even Length List (4 Nodes)
  Input List: [10 -> 20 -> 30 -> 40] (Target middle to delete: 30)
  1. Initialization
     ->slow = 10
     ->fast = head.next.next = 30
  2. Loop Execution
     ->Iteration 1:
       -Check: fast != null && fast.next != null (30 and 40 are valid).
       -Update: fast moves to null (two steps past 30).
       -Update: slow moves to 20.
     ->Iteration 2 Check:
       -Check: fast != null && fast.next != null → fast is null.
       -Result: Loop breaks.
  3. Deletion Step
     ->Current state: slow is at 20.
     ->Code runs: slow.next = slow.next.next (Changes 20's next pointer from 30 to 40).
     ->Final List: [10 -> 20 -> 40] (Successfully deleted 30).
"""
  
