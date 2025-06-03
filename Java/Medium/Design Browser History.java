//  https://leetcode.com/problems/design-browser-history/

class BrowserHistory {
    // 44 ms, faster than 97.49%
    // 47.5 mb, less than 48.94%
    // Solved in 17 minutes 10 seconds

    int cur;
    ArrayList<String> history;
    
    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        cur = 0;
    }
    
    public void visit(String url) {
        // Need code to remove forward history
        if(cur < history.size() - 1) { // if cur is not at the end
            // remove everything after cur
            while(cur < history.size() - 1)
                history.remove(cur+1);
            
        }
        
        history.add(url);
        cur++;
    }
    
    public String back(int steps) {
        cur -= steps;
        
        if(cur < 0) {
            cur = 0;
        }
        
        return history.get(cur);
    }
    
    public String forward(int steps) {
        if(steps + cur >= history.size()) {
            // just move cur to the end, and return
            cur = history.size() - 1;
            return history.get(cur);
        }
        
        cur += steps;
        return history.get(cur);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
