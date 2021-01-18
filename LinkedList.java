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
 
 // Reverse 2 linked lists and add the values and put them in a new list
class Solution {
    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode newHead1 = reverseLinkedList(a);
        ListNode newHead2 = reverseLinkedList(b);
        
        ListNode current1 = newHead1;
        ListNode current2 = newHead2;
        int carry = 0;
        
        ListNode sumHead = null;
        ListNode finalHead = null;
        
        while(current1 != null && current2 != null) {
            int val = current1.val + current2.val + carry;
            if(val > 9) {
                carry = 1;
                val = val - 10;
            } else {
                carry = 0;
            }
            
           // System.out.println("Val is " + val);

            if(sumHead == null) {
                ListNode element = new ListNode(val);
                sumHead = element;
                finalHead = element;
                //System.out.println(sumHead.val);
            } else {
                ListNode element = new ListNode(val);
                sumHead.next = element;
                sumHead = element;
                //System.out.println(sumHead.val);
            }
            
            current1 = current1.next;
            current2 = current2.next;
            
        }
        return finalHead;   
    }
    
    
    static ListNode reverseLinkedList(ListNode head) {
        if(head.next == null) {
            return head;
        }
        ListNode next = null;
        ListNode prev = null;
        ListNode current = head;
        
        while(current != null) {
            next = current.next; 
            current.next = prev;
            prev = current;
            current = next;
        }
        
        head = prev;
        //iterateLinkedList(head);
        return head; 
    }
    
    /*static void iterateLinkedList(ListNode head) {
        if(head.next == null) {
            System.out.println(head.val);
        }
        ListNode current = head;
        
        while(current != null) {
            System.out.println(current.val);
            current = current.next;
        }
        
        System.out.println("--------------");
    }*/

}
