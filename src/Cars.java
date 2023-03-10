//Cheese class 
//Bounces

import java.awt.*;

public class Cars {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;
    public int hits;

    // METHOD DEFINITION SECTION

    //This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Cars(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        width = 100;
        height = 56;
        dx = 5;
        dy = 0;
        isAlive = true;
        hits = 0;
        rec = new Rectangle(xpos, ypos, width, height);


    } // constructor


    public Cars(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 100;
        height = 56;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        hits = 0;
        rec = new Rectangle(xpos, ypos, width, height);


    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos - dx;
        ypos = ypos + dy;

        if (xpos < -100) {
            xpos=1000;

        }
        rec = new Rectangle(xpos, ypos, width, height);

    }


} //end of the Cheese object class  definition
