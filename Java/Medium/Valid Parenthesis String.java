/*
https://leetcode.com/problems/valid-parenthesis-string/
LeetCode 30-day challenge, day 16
*/

/*
Somewhat builds off of an approach to a regular "Valid Parenthesis String" problem without the * rule.
The regular approach:
Simply having a counter variable that increases by 1 with a '(' and decreases by 1 with a ')'
If at the end, this number is not 0, or if it ever has the value -1, the parenthesis are not valid.

This approach considers a minimum counter, sort of representing the case where all *s are ')'
and a maximum counter, representing the case where all *s are '('. Any number in between these two
represent mixed cases where the *s represent '(', ')', and empty strings. If at the end, either value 
is 0, then the string can be made valid with replacements to *s. Note, however, that the maximum
value can never have a value of -1, so it returns false if it ever does.
*/

class Solution {
    public boolean checkValidString(String s) {
        int high = 0;
        int low = 0;
        
        for(char c : s.toCharArray())
        {
            if(c == '(')
            {
                high++;
                low++;
            }
            else if(c == ')')
            {
                high--;
                
                if(high < 0) // if im not wrong, if high < 0, invalid output ( i.e ")()" or "()())" )
                    return false;
                
                if(low > 0)
                    low--;
            }
            else if(c == '*')
            {
                high++;
                
                if(low > 0)
                    low--;
            }
            else 
                return false;
        }
        
        if(high == 0 || low == 0)
            return true;
        else
            return false;
    }
}
