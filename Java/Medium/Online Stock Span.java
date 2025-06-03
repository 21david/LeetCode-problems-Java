/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3334/

May Leetcoding challenge, day 19
*/

/*
1014 ms, faster than 5.21%
90.3 mb, less than 10%
*/

class StockSpanner {
    ArrayList<Integer> prices;
    
    public StockSpanner() {
        prices = new ArrayList<>();
    }
    
    public int next(int price) {
        prices.add(price);
        
        int count = 1;
        
        for(int i = prices.size() - 2; i >= 0; i--) {
            if(prices.get(i) <= price)
                count++;
            else
                break;
        }
        
        return count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
