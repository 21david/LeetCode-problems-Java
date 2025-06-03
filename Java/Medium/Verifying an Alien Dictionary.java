//  https://leetcode.com/problems/verifying-an-alien-dictionary/

/*
We can go through the list of words, comparing each word with the next word, until we
reach the end of the list. As we compare, if two words are not lexicographically in order,
we return false. If all pairs of words return true (if they are in order), then we return 
true at the end.
We need a method that takes in two strings and checks that they are in order.
To do this, we iterate through both words, comparing the letters one by one.
If the letters are equal or if they are in order, we move to the next.
If they are out of order, we return false for the whole algorithm right away.
I think we can use a HashMap that maps letters to their position in the alphabet
to help us check if two words are in order in the method we will write.
*/

// 0 ms, faster than 100%
// 37.4 mb, less than 84.08%
// Solved in about 35 minutes

class Solution {
    int[] letterPositions = new int[26];
    
    public boolean isAlienSorted(String[] words, String order) {
        if(words.length == 1)
            return true;
        
        // fill up the hashmap with the letters and their positions
        int pos = 1;
        for(char c : order.toCharArray())
            letterPositions[c-97] =  pos++;
            
        for(int i = 0; i < words.length - 1; i++) {
            if(!isSorted(words[i], words[i+1]))
                return false;
        }
        
        return true;
    }

    public boolean isSorted(String a, String b) {
        int len = Math.min(a.length(), b.length());
        
        int curCharA;
        int curCharB;
        
        for(int i = 0; i < len; i++) {
            curCharA = a.charAt(i) - 97;
            curCharB = b.charAt(i) - 97;
            
            if(letterPositions[curCharA] == letterPositions[curCharB])
                continue;
            else if(letterPositions[curCharA] > letterPositions[curCharB])
                return false;
            else
                return true;
        }
        
        // at this point, if all letters were equal, then we have 3 cases:
        //      1) equal length words (so the words are exactly the same) ==> return true
        //      2) a is shorter than b. this is defined as being in order ==> return true
        //      3) b is shorter than a. this is defined as not being in order ==> return false
        
        if(a.length() <= b.length())
            return true;
        else
            return false;
    }
}

/*
Sample input:
["hello","leetcode"]
"hlabcdefgijkmnopqrstuvwxyz"

["qwer", "qwert", "qwerty", "qert", "qerty", "moon", "moonbase"]
"qwertyuiopasdfghjklzxcvbnm"

["hello","leetcode"]
"hlabcdefgijkmnopqrstuvwxyz"

["qwerty", "qwert", "qwer", "moonbase", "moon"]
"qwertyuiopasdfghjklzxcvbnm"
*/
