//  https://leetcode.com/problems/angle-between-hands-of-a-clock/

class Solution {
    public double angleClock(int hour, int minutes) {
        // 0 ms, faster than 100%
        // 36 mb, less than 79.55%
        // Solved in 15 mins 30 seconds
        
        /*
            The hour hand will start out exactly on the hour. Then, depending on the minute, it will move (miunutes/60 * 100)% to the next hour. 
            This will tell us how far it is from the north position, and we can calculate the degrees with this.
            
            To calculate how many degrees the minute hand is from the north position, we can use (minutes/60)*360. 
            
            When we know how many degrees the hour and minute hands are from the north position, we can subtract the lesser one from the greater 
            one to know the angle. If this angle is greater than 180, we subtract it from 360 and return it, if not, then we just return it.
        */
        
        if(hour == 12)
            hour = 0;
        
        double hourDegree = ((double)hour/12) * 360 + ((double)minutes/60) * 30;
        double minuteDegree = ((double)minutes/60) * 360;

        double greater = Math.max(hourDegree, minuteDegree);
        double lesser = Math.min(hourDegree, minuteDegree);
        
        double result = greater - lesser;
        
        if(result >= 180)
            result = 360 - result;
        
        return result;
        
    }
}
