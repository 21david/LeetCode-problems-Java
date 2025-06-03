//  https://leetcode.com/problems/partition-list/

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
A basic approach would be to create a new LinkedList
and traverse the input list twice. The first time, adding
nodes that are < x to the new list, and the second time,
adding nodes that are >= x.

1 ms, faster than 7.02%
38.1 mb, less than 83.21%
*/
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null)
            return null;
        
        ArrayList<Integer> temp = new ArrayList<>();
        ListNode iterate = head;
        
        while(iterate != null) {
            if(iterate.val < x)
                temp.add(iterate.val);
            
            iterate = iterate.next;
        }
        
        iterate = head;
        
        while(iterate != null) {
            if(iterate.val >= x)
                temp.add(iterate.val);
            
            iterate = iterate.next;
        }
        
        ListNode newList = new ListNode(temp.get(0));
        ListNode newListHead = newList;
        
        for(int i = 1; i < temp.size(); i++) {
            newList.next = new ListNode(temp.get(i));
            newList = newList.next;
        }
        
        return newListHead;
    }
}
