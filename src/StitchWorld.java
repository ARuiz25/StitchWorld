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
public class StitchWorld implements Runnable, KeyListener,MouseListener,MouseMotionListener {

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
    public Image CarPic2;
    public Image logPic;
    public Image stitchPic;
    public Image background;
    public Image stitchDeadPic;
    public Image Homescreen;
    public Image LosingScreen;

    //Declare the character objects
    public Log[] log1;
    public Log[] log2;
    public Cars[] Car1;
    public Cars[] Car2;
    public Stitch user;
    public boolean hitAny = false;
    public boolean GameIsPlaying = false;
    public boolean GameOver = false;
    public Button HomeButton;
    public Button EndButton;
    public int mouseX, mouseY;


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
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);

        //load images
        CarPic1 = Toolkit.getDefaultToolkit().getImage("Car_1.png");
        CarPic2 = Toolkit.getDefaultToolkit().getImage("Car_2.png");
        logPic = Toolkit.getDefaultToolkit().getImage("log.png");
        stitchPic = Toolkit.getDefaultToolkit().getImage("StitchBack4.png");
        background = Toolkit.getDefaultToolkit().getImage("background.png");
        stitchDeadPic = Toolkit.getDefaultToolkit().getImage("StitchDead2.png");
        Homescreen = Toolkit.getDefaultToolkit().getImage("Homescreen.png");
        LosingScreen = Toolkit.getDefaultToolkit().getImage("Losing Screen.png");


        //create (construct) the objects needed for the game
        user = new Stitch(510, 700, 0, 0, stitchPic);
       user.isAlive=true;
        log1 = new Log[5];
        for (int x = 0; x < 5; x = x + 1) {
            log1[x] = new Log(1000, 54 * x + 38, (int) (Math.random() * 5 + 1), 0, logPic);
        }
//        log2 = new Log[2];
//        for (int x = 0; x < 2; x = x + 1) {
//            log2[x] = new Log(0, 54, -(int) (Math.random() * 2 + 1), 0, logPic);
//        }
        Car1 = new Cars[5];
        for (int x = 0; x < 5; x = x + 1) {
            Car1[x] = new Cars(1000, 55 * x + 374, (int) (Math.random() * 5 + 1), 0, CarPic1);
        }
//        Car2 = new Cars[2];
//        for (int x = 0; x < 2; x = x + 1) {
//            Car2[x] = new Cars(0, 54, -(int) (Math.random() * 2 + 1), 0, CarPic2);
//        }
        HomeButton = new Button(192, 442, 337, 150);
        EndButton = new Button(588,420,350,175);


    } // StitchWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up

    public void StartGame() {
        StitchWorld again = new StitchWorld();
//        for (int x = 0; x < 5; x = x + 1) {
//            log1[x].xpos = 1000;
//            log1[x].ypos = 54 * x + 38;
//            log1[x].dx = (int) (Math.random() * 5 + 1);
//            log1[x].dy = 0;
//        }
//        for (int x = 0; x < 2; x = x + 1) {
//            log2[x].xpos = 0;
//            log2[x].ypos = 54 * x + 100;
//            log2[x].dx = -(int) (Math.random() * 2 + 1);
//            log2[x].dy = 0;
//        }
//        for (int x = 0; x < 5; x = x + 1) {
//            Car1[x].xpos = 1000;
//            Car1[x].ypos = 55 * x + 374;
//            Car1[x].dx = (int) (Math.random() * 5 + 1);
//            Car1[x].dy = 0;
//        }
//        for (int x = 0; x < 2; x = x + 1) {
//            Car2[x].xpos = 0;
//            Car2[x].ypos = 54 * x + 400;
//            Car2[x].dx = -(int) (Math.random() * 2 + 1);
//            Car2[x].dy = 0;
//        }
    }

    public void moveThings() {
        /*for (int x = 0; x < 5; x = x + 1) {
            if (log1[x].StitchOnLog == true){
            user.xpos = log1[x].xpos + 100;
            }
        }*/
        for (int x = 0; x < 5; x = x + 1) {
            log1[x].move();
        }
//        for (int x = 0; x < 2; x = x + 1) {
//            log2[x].move();
//        }
        for (int x = 0; x < 5; x = x + 1) {
            Car1[x].move();
        }
//        for (int x = 0; x < 2; x = x + 1) {
//            Car2[x].move();
//        }
    }
    public void moveStitch() {
        // user.move();
        // if (user.isStuck == false) {
//        for (int x = 0; x < 5; x++) {
//            if (log1[x].StitchOnLog = false) {
                user.move();
//            } else {
//                user.xpos = log1[x].xpos;
//                user.ypos = log1[x].ypos;
//            }
            //}
//        }
    }
    public void collision() {
        System.out.println(user.isStuck);
        for (int x = 0; x < 5; x++) {
           // hitAny=false;
            if (user.rec.intersects(log1[x].rec) && user.right == false && user.left == false && user.up == false && user.down == false) {
               log1[x].StitchOnLog = true;
              //  hitAny=true;
                System.out.println("this is x" +x);
            }
            if(user.rec.intersects(log1[x].rec)==false){
                log1[x].StitchOnLog= false;
            }
        }
//        for (int x = 0; x < 2; x++) {
//            hitAny = false;
//            if (user.rec.intersects(log2[x].rec) && user.right == false && user.left == false && user.up == false && user.down == false) {
//                user.isStuck = true;
//                hitAny = true;
//            }
//        }
//        if(hitAny==false){
//
//                user.isStuck = false;
//
//        }
            for (int x = 0; x < 5; x++) {
                if (user.rec.intersects(Car1[x].rec) && user.isIntersecting == false) {
                    user.isIntersecting = true;
                    user.isAlive = false;
                    GameOver=true;
                }
            }
//            for (int x = 0; x < 2; x++) {
//                if (user.rec.intersects(Car2[x].rec) && user.isIntersecting == false) {
//                user.isIntersecting = true;
//                user.isAlive = false;
//            }
//        }
        }


    public void run() {
        while (true) {
            user.rec = new Rectangle(user.xpos, user.ypos, user.width, user.height);

            moveThings();           //move all the game objects
            render();// paint the graph mouseClicked(MouseEvent e);ics
            collision();
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

//        draw characters to the screen
//
        if (GameIsPlaying == true && GameOver == false) {

            g.drawImage(background,0, 0, 1000, 700, null);
            for (int x=0;x<5;x++){
                g.drawImage(log1[x].pic, log1[x].xpos, log1[x].ypos, log1[x].width, log1[x].height, null);
                g.drawRect(log1[x].xpos+10, log1[x].ypos+23,200, 30);
            }
//            for (int x=0;x<2;x++){
//                g.drawImage(log2[x].pic, log2[x].xpos, log2[x].ypos, log2[x].width, log2[x].height, null);
//            }
            if (user.isAlive == true) {
                g.drawImage(user.pic, user.xpos, user.ypos, user.width, user.height, null);
                g.drawRect(user.xpos, user.ypos, user.width, user.height);
            }
            for(int x=0;x<5;x++) {
                g.drawImage(Car1[x].pic, Car1[x].xpos, Car1[x].ypos, Car1[x].width, Car1[x].height, null);
            }
//            for(int x=0;x<2;x++) {
//                g.drawImage(Car2[x].pic, Car2[x].xpos, Car2[x].ypos, Car2[x].width, Car2[x].height, null);
//            }
           /* if (user.isAlive == false) {
                g.drawImage(stitchDeadPic, user.xpos, user.ypos, user.width, user.height, null);
                g.drawImage(LosingScreen,0,0,1000,700,null);
            }*/

        }
        else if(GameOver == false && GameIsPlaying == false) {
            g.drawImage(Homescreen, 0, 0, 1000, 700, null);
        }
        else if(GameOver == true){
            g.drawImage(LosingScreen, 0, 0, 1000, 700, null);
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
           //user.isStuck = false;
            user.right = true;
            moveStitch();
        }
        if (keyCode == 65) { // a
            //user.isStuck = false;
            user.left = true;
            moveStitch();
        }

        if (keyCode == 83) { // s
           //user.isStuck = false;
            user.down = true;
            moveStitch();
        }
        if (keyCode == 87) { // w
            //user.isStuck = false;
            user.up = true;
            moveStitch();
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

    public void mouseClicked(MouseEvent e) {

        int x, y;
        x = e.getX();
        y = e.getY();

        mouseX = x;
        mouseY = y;
        System.out.println();
        System.out.println("Mouse Clicked at " + x + ", " + y);

        if (HomeButton.rec.contains(x, y)) {
            GameIsPlaying = true;
            GameOver = false;
        }
        if (EndButton.rec.contains(x,y)){
            GameIsPlaying = true;
            GameOver = false;
            StartGame();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        int x, y;
        x = e.getX();
        y = e.getY();

        mouseX = x;
        mouseY = y;
        }
    }
//class
