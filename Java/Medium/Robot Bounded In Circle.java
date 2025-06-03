//  https://leetcode.com/problems/robot-bounded-in-circle/

/*
I think there are only 3 cases. If the pattern fits one of these 3 cases,
the answer is true. If not, the answer is false.

Case 1) The robot ends up at (0,0) after the first iteration
Case 2) It ends up at (0,0) after 2 iterations
Case 3) It ends up at (0,0) after 4 iterations

I could try running the instructions, keeping track of the position after
1, 2, and 4 iterations. If it reaches (0,0) after any of the iterations, 
return true right away. If it never reaches (0,0), return false.
*/

// 0 ms, faster than 100%
// 36.8 mb, less than 90.64%
// Solved in 24 minutes
class Solution {
    int xPos, yPos;
    int dir;
    public boolean isRobotBounded(String instructions) {
        // 0 is north, 1 is east, 2 is south, 3 is west
        int dir = 0; 
        
        // Execute them 1 time
        execute(instructions);
        if(xPos == 0 && yPos == 0)
            return true;
        
        // Execute them 2 times total
        execute(instructions);
        if(xPos == 0 && yPos == 0)
            return true;

        // Execute them 4 times total
        execute(instructions);
        execute(instructions);
        if(xPos == 0 && yPos == 0)
            return true;
        
        return false;
    }
    
    // Execute the instructions 1 time, moving the robot's xPos and yPos
    public void execute(String instructions) {
        for(char c : instructions.toCharArray()) {
            if(c == 'G')
                move(dir);  // move it in whatever direction it is currently facing
            else if (c == 'L')
                dir = (dir + 3) % 4; // change the direction
            else
                dir = (dir + 1) % 4; // change the direction
        }
        
        // At this point, the robot has followed the instructions for 1 iteration
    }
    
    // Move the robot 1 step ahead based off of its current direction
    public void move(int dir) {
        if(dir == 0) // move north
            yPos++;
        else if(dir == 1) // move east
            xPos++;
        else if(dir == 2) // move south
            yPos--;
        else // move west
            xPos--;
    }
}

/*
Sample input:
"GGLLGG"
"GG"
"GL"
"GRGLGR"
"GRGLGLG"
"GLLGRRGGLGLRLGLLLGRRGGGRL"


*/
