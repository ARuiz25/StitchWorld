//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class StitchWorld implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image CarPic1;
    public Image logPic;
    public Image stitchPic;
    public Image background;
    public Image stitchDeadPic;

    //Declare the character objects
    public Log[] log1;
    public Cars[] Car1;
    public Stitch user;



    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        StitchWorld myApp = new StitchWorld();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.

    public StitchWorld() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        CarPic1 = Toolkit.getDefaultToolkit().getImage("Car_1.png");
        logPic = Toolkit.getDefaultToolkit().getImage("log.png");
        stitchPic = Toolkit.getDefaultToolkit().getImage("StitchBack4.png");
        background = Toolkit.getDefaultToolkit().getImage("background.png");
        stitchDeadPic = Toolkit.getDefaultToolkit().getImage("StitchDead2.png");


        //create (construct) the objects needed for the game
        log1 = new Log[4];
        for (int x=0; x<4; x=x+1){
            log1[x] = new Log(1000, 65*x+45, (int)(Math.random()*4+1), 0, logPic);
        }
            user = new Stitch(250, 250, 0, 0, stitchPic);

        Car1 = new Cars[5];
        for (int x=0; x<5; x=x+1){
            Car1[x]= new Cars(1000,55*x+374,(int)(Math.random()*5+1),0,CarPic1);
        }


    } // StitchWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        for (int x=0; x<4; x= x + 1) {
            log1[x].move();
        }
        user.move();
        if (user.isStuck == false) {
            user.move();
        } else {
            for (int x=0; x<4; x= x + 1) {
                user.xpos = log1[x].xpos + 100;
            user.ypos = log1[x].ypos + 10;
        }
        }
        for (int x = 0; x < 5; x = x + 1) {
            Car1[x].move();
        }
    }
    public void collision() {
        for (int x = 0; x < 4; x++) {
            if (user.rec.intersects(log1[x].rec) && user.right == false && user.left == false && user.up == false && user.down == false) {
                user.isStuck = true;
            } else {
                user.isStuck = false;
            }
        }
            for (int x = 0; x < 5; x++) {
                if (user.rec.intersects(Car1[x].rec) && user.isIntersecting == false) {
                    user.isIntersecting = true;
                    user.isAlive = false;
                }
            }
        }


    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            render();// paint the graphics
            collision();
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw characters to the screen
        g.drawImage(background,0, 0, 1000, 700, null);
        for (int x=0;x<4;x++){
            g.drawImage(log1[x].pic, log1[x].xpos, log1[x].ypos, log1[x].width, log1[x].height, null);
        }
        if (user.isAlive == true) {
            g.drawImage(user.pic, user.xpos, user.ypos, user.width, user.height, null);
        }
        if (user.isAlive == false) {
            g.drawImage(stitchDeadPic, user.xpos, user.ypos, user.width, user.height, null);
        }
        for(int x=0;x<5;x++) {
            g.drawImage(Car1[x].pic, Car1[x].xpos, Car1[x].ypos, Car1[x].width, Car1[x].height, null);
        }
        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68 ) { // d
            user.right=true;

        }
        if (keyCode == 65) { // a
            user.left = true;
        }

        if (keyCode == 83) { // s
            user.down = true;
        }
        if (keyCode == 87) { // w
            user.up = true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            user.right = false;

        }
        if (keyCode == 65) { // a
            user.left = false;
        }
        if (keyCode == 83) { // s
            user.down = false;
        }
        if (keyCode == 87) { // w
            user.up = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {

        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("StitchWorld");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//class
