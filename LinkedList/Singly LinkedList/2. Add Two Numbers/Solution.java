//leetcode link: https://leetcode.com/problems/add-two-numbers/
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr=new ListNode(-1);
        ListNode ans=curr;
        int carry=0;
        while(l1!=null && l2!=null){
            int sum=l1.val+l2.val+carry;
            carry=sum/10;
            curr.next=new ListNode(sum%10);
            curr=curr.next;
            l1=l1.next;
            l2=l2.next;
        }
        while(l1!=null){
            int sum=l1.val+carry;
            carry=sum/10;
            curr.next=new ListNode(sum%10);
            curr=curr.next;
            l1=l1.next;
        }
        while(l2!=null){
            int sum=l2.val+carry;
            carry=sum/10;
            curr.next=new ListNode(sum%10);
            curr=curr.next;
            l2=l2.next;
        }
        if(carry!=0){
            curr.next=new ListNode(carry);
        }
        return ans.next;
    }
}
