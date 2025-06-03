/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

Initialize a counter variable to store the profit.
Upon start, continue iterating until a minimum is reached. (meaning, until the next number is greater than current number)
Buy that minimum, then continue iterating until a maximum is reached. 
When a maximum is reached, sell it, and continue iterating until a minimum is reached.
Repeat this for the whole array. Stop when 'i' reaches the end of the array.
Return the profit made.
*/
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;
        
        int profit = 0;
        
        int cur = prices[0];
        int i = 0;
        
        // try to go as low as you can
        while(i < prices.length - 1 && prices[i + 1] <= prices[i])
        {
            i++;
            cur = prices[i];
        }
        
        if(i == prices.length - 1)
            return 0;
        
        while(i < prices.length)
        {
            // try to go as high as you can
            while(i < prices.length - 1 && prices[i + 1] >= prices[i])
                i++;
            
            // the while loop stops when next number does not go up
            
            profit += prices[i] - cur;
            
            // move on to the next element
            i++;
            
            if(i >= prices.length)
                break;
                
            cur = prices[i];
            
            // try to go as low as you can
            while(i < prices.length - 1 && prices[i + 1] <= prices[i])
            {
                i++;
                cur = prices[i];
            }
        }
        
        return profit;
    }
    
    // my solution after seeing the official solution to this problem
    // 1 ms, faster than 91.24%
    public int maxProfit2(int[] prices) {
        int profit = 0;
        
        for(int i = 0; i < prices.length - 1; i++)
            if(prices[i + 1] > prices[i])
                profit += prices[i + 1] - prices[i];
        
        return profit;
    }
}
