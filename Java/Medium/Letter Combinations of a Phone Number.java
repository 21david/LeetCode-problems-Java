//  https://leetcode.com/problems/letter-combinations-of-a-phone-number/

class Solution {
    public List<String> letterCombinations(String digits) {
        // 1 ms, faster than 72%
        // 37.6 mb, less than 81.18%
        // Solved in about 46+ minutes
        
        /*
        for each letter that each digit represents, we have to pair it with all the letters
        the next one would represent. This would form a tree in the long run.
        
        For example, 832
        
                 t                                  u                                  v                 
               / | \                              / | \                              / | \               
             /   |   \                          /   |   \                          /   |   \             
           /     |     \                      /     |     \                      /     |     \           
         /       |       \                  /       |       \                  /       |       \         
        d        e         f               d        e         f               d        e         f       
      / | \    / | \     / | \           / | \    / | \     / | \           / | \    / | \     / | \     
     a  b  c  a  b  c   a  b  c         a  b  c  a  b  c   a  b  c         a  b  c  a  b  c   a  b  c  
     
        Each of these paths in the trees above represents a combination.
        So the answer for "832" would be
        tda
        tdb
        tdc
        tea
        teb
        tec
        tfa
        tfb
        tfc
        uda
        udb
        udc
        uea
        ...
        */
        
        if(digits.length() == 0)
            return new ArrayList<String>();
        
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        
        List<String> letters = new ArrayList<>();
        for(char c : digits.toCharArray())
            letters.add(map.get(c - 48));
        
        recursive(new StringBuilder(), letters);
        
        return solution;
    }
    
    private List<String> solution = new ArrayList<>();
    
    public void recursive(StringBuilder a, List<String> b) {
        // once the list is empty, then we have a valid letter combination in a
        if(b.isEmpty()) {
            solution.add(a.toString());
            return;
        }
        
        // we take the first string in b and call recursive functions for each letter
        String first = b.get(0);
        
        // so for each letter, we add it to a the string that will represent a letter combination
        StringBuilder aNew;
        for(char c : first.toCharArray()) {
            
            b.remove(0);
            aNew = new StringBuilder(a);
            recursive(aNew.append(c), b);
            b.add(0, first);
        }
    }
}
