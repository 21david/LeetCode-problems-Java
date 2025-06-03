//  https://leetcode.com/problems/powerful-integers
        
/*
Brute force approach works.
Try every combination of i and j, until
their value (x^i + y^j) is bigger than
the bound. A nested loop works. The inner
loop tries all values of j until it 
gets too big. The outer one tries all
values of i until it gets too big.
Gotta be careful with edge cases.

1 ms, faster than 98.44%
36.9 mb, less than 40.10%
*/
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
       if(bound < 2)
            return new ArrayList<Integer>();
        
        if(x == 1 || y == 1)
            return xOrYEquals1(x, y, bound);
        
        HashSet<Integer> solSet = new HashSet<>();
        
        int i = 0, j = 0;
        int val = plugIntoFormula(x, i, y, j);
        
        while(plugIntoFormula(x, i, y, j) <= bound) {
            val = plugIntoFormula(x, i, y, j);
            
            while(val <= bound) {
                solSet.add(val);
                j++;
                val = plugIntoFormula(x, i, y, j);
            }
            
            i++;
            j = 0;
        }
        
        // Convert HashSet<Integer> to List<Integer>
        List<Integer> sol = new ArrayList<>();
        
        for(int solNum : solSet)
            sol.add(solNum);
        
        return sol;
    }
    
    public List<Integer> xOrYEquals1(int x, int y, int bound) {
        if(x == 1 && y == 1)
            return new ArrayList<Integer>(Arrays.asList(2));
        
        // make x == 1 and y != 1 (swap if x != 1 already, leave if x == 1 already)
        if(y == 1) {
            y = x;
            x = 1;
        }
        
        // now x is the one that is 1, and y is not
        HashSet<Integer> solSet = new HashSet<>();
        
        int j = 0;
        int val = plugIntoFormula(x, 0, y, j);
        
        while(plugIntoFormula(x, 0, y, j) <= bound) {
            solSet.add(val);
            j++;
            val = plugIntoFormula(x, 0, y, j);
        }
        
        // Convert HashSet<Integer> to List<Integer>
        List<Integer> sol = new ArrayList<>();
        
        for(int solNum : solSet)
            sol.add(solNum);
        
        return sol;
    }
    
    public int plugIntoFormula(int x, int i, int y, int j) {
        return (int) (Math.pow(x, i)) + (int) (Math.pow(y, j));
    }
}

/*
Sample input (lot of edge cases):
1
1
15
1
1
0
1
1
1
1
1
2
1
3
20
3
1
20
2
3
10
67
98
1000000
*/
