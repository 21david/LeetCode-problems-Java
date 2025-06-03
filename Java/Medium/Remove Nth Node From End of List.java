//  https://leetcode.com/problems/remove-nth-node-from-end-of-list/

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

/*
Approach after seeing optimal solution: 
We can iterate to the (n+1)th node using a pointer, then, we can
set another pointer to the beginning and iterate both
pointers at the same speed until the second pointer
reaches the null at the end. They will have a gap of
n+1 in between, and this gap will put our leftmost pointer
on the node right before the one we want to remove.
Then, we just remove the node and return the head.
Time complexity: O(N) (one pass through the linked list)
Space complexity: O(1), no extra memory is used except pointers

0 ms, faster than 100%
36.8 mb, less than 65.70%
Solved in about 30+ minutes
*/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null)
            return null;
        
        // iterate a pointer n+1 nodes
        ListNode pointer2 = head;
        while(n >= 0) {
            if(pointer2 == null)
                return head.next;
            pointer2 = pointer2.next;
            n--;
        }
        
        // iterate that pointer until it reaches the end
        // and iterate another pointer starting from the beginning
        // this will put pointer1 right behind the node we want to remove
        ListNode pointer1 = head;
        while(pointer2 != null) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        
        // remove the unwanted node
        pointer1.next = pointer1.next.next;
        
        return head;
    }
}

/*
Sample input:
[1,2,3,4,5]
2

[2,4,6,8,10]
1

[2,4,6,8,10]
3

[2,4,6,8,10]
4

[2,4,6,8,10]
5

[1]
1
*/
