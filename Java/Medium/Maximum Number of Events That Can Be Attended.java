//  https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended

class Solution {
    public int maxEvents(int[][] events) {
        // Time limit exceeded. Passes 40/42 test cases.
        
        // find maximum number
        int max = 0;
        
        for(int[] evt : events)
            max = Math.max(max, Math.max(evt[0], evt[1]));
        
        // sort by end first, by start second
        Arrays.sort(events, (x,y) -> { 
            if(x[1] == y[1]) {
                if(x[0] < y[0])
                    return -1;
                else if(x[0] > y[0])
                    return 1;
                else
                    return 0;
            }
            else if(x[1] < y[1])
                return -1;
            else
                return 1;
        });
        
  //      System.out.println(Arrays.deepToString(events));
        
        // for each event, check if it can still be visited
        // if event[1] is >= d, then the current event can still be visited
        
        Set<Integer> set = new HashSet<>();  // keep track of days that we've used up on an event
        
        int curEvent[];
        
        int count = 0;  // num of events attended
        
        outer:
        for(int i = 0; i < events.length; i++) {
            curEvent = events[i];
            
            for(int j = curEvent[0]; j <= curEvent[1]; j++) {
                if(!set.contains(j)) {  // we found a day we can visit
                    count++;
                    set.add(j);
                    continue outer;
                }
            }
        
        }
        
        return count;
    }
}
