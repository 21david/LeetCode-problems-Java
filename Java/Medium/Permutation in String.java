/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3333/

May Leetcoding challenge, day 18

- didn't use any hints, nor solution
- solved within about ~45 minutes
*/

/*
Approach, O(s1 + s2) I think:
-Create an array of length 26 that counts the frequency of each letter in s1 (a multiset)
-Create a temporary array of length 26, to be used to find possible permutations
-Also create a HashSet containing all the characters in s1, so we can check if a letter 
is in s1 with O(1) time
-Create a count variable
-Iterate through each character in s2, check if each letter is in the HashSet
  -if it is, increment count by 1, and increment the frequency of that letter in the temporary array
  -if not, set count to 0
-if count reaches the size of s1, then we have a potential "candidate" that could be a 
 permutation of s1. So we compare the multiset of s1 with this multiset. If they are the
 exact same, then we found a permutation, so we can return true.
-if count gets bigger than the size of s1, then we need to remove whatever is at the "tail" of the current list of characters we have. We need a tail variable (int) for this, to store the index of the "tail"
-if we reach the end of the array and never found a permutation, then we return false

13 ms, faster than 43.48%
42 mb, less than 7.69%
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // Set up multiset and HashSet for s1
        HashSet<Character> s1Chars = new HashSet<>();  // (i think we can change to algorithm to work w/out this)
        int[] s1CharFreqs = new int[26];
        
        for(char c : s1.toCharArray()) {
            s1Chars.add(c);
            s1CharFreqs[c - 'a']++;
        }
        
        int[] temp = new int[26];
        int count = 0;
        
        int tail = -1;
        int i = 0;
        
        // iterate through each letter in s2
        for(char c : s2.toCharArray()) {
            if(s1Chars.contains(c)) {
                count++;
                temp[c - 'a']++;
                
                if(count > s1.length()) { // we gotta remove the tail letter
                    temp[s2.charAt(tail) - 'a']--;
                    tail++;
                    count--;
                }
                
                if(tail == -1)
                    tail = i;
            }
            else {
                count = 0;
                tail = -1;
                temp = new int[26];
            }
            
            if(count == s1.length()) {
                if(equals(s1CharFreqs, temp)) // if multisets are equal, we can return true bc we found a permut
                    return true;
            }
            
            i++; // index of current letter
        }
        
        return false;
    }
    
    // returns true if two arrays (that represent multisets, or the frequencies of each letter in a string)
    // are equal. If they are equal, this is equivalent to both the strings they represent being permutations
    // O(1) runtime
    public boolean equals(int[] arr1, int[] arr2) {
        for(int i = 0; i < 26; i++) {
            if(arr1[i] != arr2[i])
                return false;
        }
        
        return true;
    }
}
