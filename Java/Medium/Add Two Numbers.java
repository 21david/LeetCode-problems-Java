//  https://leetcode.com/problems/add-two-numbers/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        // 2 ms, faster than 63.72%
        // (on 3rd submit, it ran 1 ms, faster than 100%)
        // 39.7 mb, less than 99.69%
        
        ListNode t1 = l1;
        ListNode t2 = l2;
        
        
        ListNode answer = new ListNode(); // stays on the head of the answer, to return it at the end
        ListNode answerTemp = answer; // moves along in the answer linked list, adding new nodes
        
        int tens = 0;
        
        while(t1 != null && t2 != null) // as long as there are still digits in either
        {
            
            int sum = t1.val + t2.val;
            
            if(tens == 1)
            {
                sum += 1;
                tens = 0;
            }
                
            if(sum >= 10)
            {
                tens = 1;
                sum -= 10;
            }

            answerTemp.val = sum;
            
            if(t1.next == null && t2.next == null && tens == 0)
            {
                t1 = t1.next;
                t2 = t2.next;
                break;
            }
            
            answerTemp.next = new ListNode();
            answerTemp = answerTemp.next;
            answerTemp.val = -1; // (doing this for the 2 while loops at the bottom)
            
            t1 = t1.next;
            t2 = t2.next;
        }
        
        // after the while loop, at least one linked list has reached the end (possibly both)
        
        
        if(tens == 0)
            answerTemp.next = null;
        
        
        // if there is still an overflow value that needs to be added, take care of it
        while(tens == 1)
        {
            if(t1 != null)
            {
                int sum = tens + t1.val;
                t1 = t1.next;
                
                if(sum >= 10)
                {
                    tens = 1;
                    sum -= 10;
                }
                else
                    tens = 0;
                
                answerTemp.val = sum; // ?
                
                if(t1 != null)
                {
                    answerTemp.next = new ListNode();
                    answerTemp = answerTemp.next;
                    answerTemp.val = -1; // (doing this for the 2 while loops at the bottom)
                }
                
                if(t1 == null && tens == 1)
                {
                    answerTemp.next = new ListNode();
                    answerTemp = answerTemp.next;
                    answerTemp.val = 1;
                }
            }
            else if(t2 != null)
            {
                int sum = tens + t2.val;
                t2 = t2.next;
                
                if(sum >= 10)
                {
                    tens = 1;
                    sum -= 10;
                }
                else
                    tens = 0;
                
                answerTemp.val = sum;
                
                if(t2 != null)
                {
                    answerTemp.next = new ListNode();
                    answerTemp = answerTemp.next;
                    answerTemp.val = -1; // (doing this for the 2 while loops at the bottom)
                }
                
                if(t2 == null && tens == 1)
                {
                    answerTemp.next = new ListNode();
                    answerTemp = answerTemp.next;
                    answerTemp.val = 1;
                }
            }
            else if(t1 == null && t2 == null)
            {
                answerTemp.val = 1;
                tens = 0;
            }
            
        
        }
        
        
        // after the while loop, if there are still elements in l1, add to 'answer'
        while(t1 != null)
        {
            if(answerTemp.val != -1)
            {
                answerTemp.next = new ListNode();
                answerTemp = answerTemp.next;
            }
            
            answerTemp.val = t1.val;
            
            t1 = t1.next;
            
        }
        
        // after the while loop, if there are still elements in l2, add to 'answer'
        while(t2 != null)
        {
            if(answerTemp.val != -1)
            {
                answerTemp.next = new ListNode();
                answerTemp = answerTemp.next;
            }

            answerTemp.val = t2.val;
            
            t2 = t2.next;
        }
        
      //  answerTemp.next = null;
        
        return answer;
    }
    
    
    // using strings and actual addition (with BigInteger)
     public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        
        // 22 ms, faster than 5.07%
        // 40.8 mb, less than 93.73%
        
        /*
        Turn input numbers into strings
        Reverse the strings
        Turn strings into integers
        Add the integers
        Turn the result into a string
        Reverse the string
        Create a final linked list and return it
        */
        
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();
        
        while(l1 != null)
        {
            string1.append(l1.val);
            l1 = l1.next;
        }
        
        while(l2 != null)
        {
            string2.append(l2.val);
            l2 = l2.next;
        }
        
        string1 = string1.reverse();
        string2 = string2.reverse();
        
        java.math.BigInteger int1 = new java.math.BigInteger(string1.toString());
        java.math.BigInteger int2 = new java.math.BigInteger(string2.toString());
        
        
        StringBuilder ans = new StringBuilder("" + (int1.add(int2)));
        System.out.println(ans);
        ans = ans.reverse();
        
        ListNode answer = new ListNode();
        ListNode answerTemp = answer;
        
        for(int i = 0; i < ans.length(); i++)
        {
            answerTemp.val = ans.charAt(i) - '0';
            
            if(i + 1 < ans.length())
            {
                answerTemp.next = new ListNode();
                answerTemp = answerTemp.next;
            }
        }
        
        return answer;
    }
    
}
