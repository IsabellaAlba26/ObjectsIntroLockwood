//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program below
    /** STEP 1: Declare your object and give it a name **/
    public Hero fred;
    public Hero SEC1;
    public Hero SEC2;
    public Hero SEC3;
    public Hero SEC4;
    public Hero SEC5;
    public Hero briefcase;
    public Hero briefcase2;
    public Hero briefcase3;
    public Hero briefcase4;
    public Hero briefcase5;
    public Hero lawyer;

    /**STEP 2: Declare an image for your object**/
    public Image backgroundPic;
    public Image fredPic;
    public Image SECPic;
    public Image briefcasePic;
    public Image lawyerPic;
    public Image losingScreen;
    public Image winningScreen;

    public boolean fredIsIntersectingSEC1;
    public boolean fredIsIntersectingSEC2;
    public boolean fredIsIntersectingSEC3;
    public boolean fredIsIntersectingSEC4;
    public boolean fredIsIntersectingSEC5;
    public boolean lawyerIsIntersectingSEC1;
    public boolean lawyerIsIntersectingSEC2;
    public boolean lawyerIsIntersectingSEC3;
    public boolean lawyerIsIntersectingSEC4;
    public boolean lawyerIsIntersectingSEC5;
    public boolean fredIsIntersectinglawyer;

    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
       GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
       // ex.run();
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        //for each object that has a picture, load in images as well
        /**STEP 3: construct a specific Hero**/
        fred= new Hero(205,222,4,3,120,160,true);
        SEC1 = new Hero(400,500,3,3, 120,120,true);
        SEC2 = new Hero(700,100,3,3, 120,120,true);
        SEC3 = new Hero(1000,100,3,3, 120,120,true);
        SEC4 = new Hero(1000,100,3,3, 120,120,true);
        SEC5 = new Hero(1000,100,3,3, 120,120,true);
        briefcase = new Hero(3,500,0,0,150,250,true);
        briefcase2 = new Hero(1000,370,0,0,150,250, false);
        briefcase3 = new Hero(1000,230,0,0,150,250, false);
        briefcase4 = new Hero(1000,90,0,0,150,250, false);
        briefcase5 = new Hero(1000,-50,0,0,150,250, false);
        lawyer = new Hero(500,350,4,0,100,146,true);


        /**STEP 4: load in the image for your object**/
        backgroundPic=Toolkit.getDefaultToolkit().getImage("Office.jpeg");
        fredPic=Toolkit.getDefaultToolkit().getImage("CatwithSuit.png");
        SECPic=Toolkit.getDefaultToolkit().getImage("SEC Badge.png");
        briefcasePic=Toolkit.getDefaultToolkit().getImage("BriefcaseOfMoney.png");
        lawyerPic=Toolkit.getDefaultToolkit().getImage("lawyer.png");
        losingScreen=Toolkit.getDefaultToolkit().getImage("losing Screen.png");
        winningScreen=Toolkit.getDefaultToolkit().getImage("Winning Screen.png");

        fred.printInfo();

    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions(); //checks for rec intersectiohns
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of your objects below:
        /** STEP 5: draw the image of your object on the screen**/
        g.drawImage(backgroundPic,0,0,WIDTH, HEIGHT,null);
        if(fred.isAlive==true) {
            g.drawImage(fredPic, fred.xpos, fred.ypos, fred.width, fred.height, null);
        }
        if(SEC1.isAlive==true) {
            g.drawImage(SECPic,SEC1.xpos,SEC1.ypos,SEC1.width,SEC1.height, null);
        }
        if(SEC2.isAlive==true) {
            g.drawImage(SECPic,SEC2.xpos,SEC2.ypos,SEC2.width,SEC2.height, null);
        }
        if(SEC3.isAlive==true) {
            g.drawImage(SECPic,SEC3.xpos,SEC3.ypos,SEC3.width,SEC3.height, null);
        }
        if(SEC4.isAlive==true) {
            g.drawImage(SECPic,SEC4.xpos,SEC4.ypos,SEC4.width,SEC4.height, null);
        }
        if(SEC5.isAlive==true) {
            g.drawImage(SECPic,SEC5.xpos,SEC5.ypos,SEC5.width,SEC5.height, null);
        }
        g.drawImage(briefcasePic,briefcase.xpos,briefcase.ypos,briefcase.width,briefcase.height, null);
        g.drawImage(briefcasePic,briefcase2.xpos,briefcase2.ypos,briefcase2.width,briefcase2.height, null);
        g.drawImage(briefcasePic,briefcase3.xpos,briefcase3.ypos,briefcase3.width,briefcase3.height, null);
        g.drawImage(briefcasePic,briefcase4.xpos,briefcase4.ypos,briefcase4.width,briefcase4.height, null);
        g.drawImage(briefcasePic,briefcase5.xpos,briefcase5.ypos,briefcase5.width,briefcase5.height, null);
        g.drawImage(lawyerPic,lawyer.xpos,lawyer.ypos,lawyer.width,lawyer.height, null);

        if(briefcase.isAlive==true && briefcase2.isAlive==true && briefcase3.isAlive==true && briefcase4.isAlive==true && briefcase5.isAlive==true){
            g.drawImage(winningScreen,0,0,WIDTH, HEIGHT,null);
        }

        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    public void moveThings() {
        //call the move() method code from your object class
        fred.bouncingMove();
        SEC1.bouncingMove();
        SEC2.bouncingMove();
        if(SEC1.isAlive==false){
            SEC3.bouncingMove();
        }
        if(SEC2.isAlive==false){
            SEC4.bouncingMove();
        }
        if(SEC3.isAlive==false){
            SEC5.bouncingMove();
        }

        lawyer.wrappingMove();
    }

    public void collisions(){

        /**FRED INTERACTIONS**/
        //Fred and SEC1 collide --> briefcase dissapears
        if(fred.rec.intersects(SEC1.rec) && fredIsIntersectingSEC1==false){
            fredIsIntersectingSEC1=true;
            fred.dx=-(fred.dx);
            fred.dy=-(fred.dx);
       }
       if(fred.rec.intersects(SEC1.rec)==false){
           fredIsIntersectingSEC1=false;
       }

        //Fred and SEC2 collide --> end game screen
        if(fred.rec.intersects(SEC2.rec) && fredIsIntersectingSEC2==false){
            fredIsIntersectingSEC2=true;
            fred.dx=-(fred.dx);
            fred.dy=-(fred.dx);

        }
        if(fred.rec.intersects(SEC3.rec)==false){
            fredIsIntersectingSEC2=false;
        }

        //if fred and SEC3 collide
        if(fred.rec.intersects(SEC3.rec) && fredIsIntersectingSEC3==false){
            fredIsIntersectingSEC3=true;
            fred.dx=-(fred.dx);
            fred.dy=-(fred.dx);

        }
        if(fred.rec.intersects(SEC3.rec)==false){
            fredIsIntersectingSEC3=false;
        }

        //if fred and SEC4 collide
        if(fred.rec.intersects(SEC4.rec) && fredIsIntersectingSEC4==false){
            fredIsIntersectingSEC4=true;
            fred.dx=-(fred.dx);
            fred.dy=-(fred.dx);

        }
        if(fred.rec.intersects(SEC4.rec)==false){
            fredIsIntersectingSEC4=false;
        }

        //Fred and SEC5 collide
        if(fred.rec.intersects(SEC5.rec) && fredIsIntersectingSEC5==false){
            fredIsIntersectingSEC5=true;
            fred.dx=-(fred.dx);
            fred.dy=-(fred.dx);

        }
        if(fred.rec.intersects(SEC5.rec)==false){
            fredIsIntersectingSEC5=false;
        }

        //Fred and lawyer collide --> fred bounces off
        if(fred.rec.intersects(lawyer.rec) && fredIsIntersectinglawyer==false){
            fredIsIntersectinglawyer=true;
            fred.dx=-(fred.dx);
            fred.dy=-(fred.dx);
        }
        if(fred.rec.intersects(lawyer.rec)==false){
            fredIsIntersectinglawyer=false;
        }

        /**LAWYER INTERACTION**/
        //lawyer and SEC1 collide --> briefcase gets added + SEC1 dies  + SEC 3 teleports on screen
        if(lawyer.rec.intersects(SEC1.rec) && lawyerIsIntersectingSEC1==false){
            lawyerIsIntersectingSEC1=true;
            briefcase.isAlive=true;
            briefcase.xpos=3;
            SEC3.xpos=SEC1.xpos;
            SEC1.isAlive=false;
            SEC1.xpos=2000;
            SEC1.dx=0;
            SEC1.dy=0;
        }
        if(lawyer.rec.intersects(SEC1.rec)==false){
            lawyerIsIntersectingSEC1=false;
        }

        //lawyer and SEC2 collide --> briefcase2 gets added + SEC2 dies + SEC 4 teleports on screen
        if(lawyer.rec.intersects(SEC2.rec) && lawyerIsIntersectingSEC2==false){
            lawyerIsIntersectingSEC2=true;
            briefcase2.isAlive=true;
            briefcase2.xpos=3;
            SEC4.xpos=SEC2.xpos;
            SEC2.isAlive=false;
            SEC2.xpos=2000;
            SEC2.dx=0;
            SEC2.dy=0;
        }
        if(lawyer.rec.intersects(SEC2.rec)==false){
            lawyerIsIntersectingSEC2=false;
        }

        //lawyer and SEC3 collide --> briefcase 3 gets added + SEC3 dies + SEC 5 gets added
        if(lawyer.rec.intersects(SEC3.rec) && lawyerIsIntersectingSEC3==false){
            lawyerIsIntersectingSEC3=true;
            briefcase3.isAlive=true;
            briefcase3.xpos=3;
            SEC5.xpos=SEC3.xpos;
            SEC3.isAlive=false;
            SEC3.xpos=2000;
            SEC3.dx=0;
            SEC3.dy=0;
        }
        if(lawyer.rec.intersects(SEC3.rec)==false){
            lawyerIsIntersectingSEC3=false;
        }

        //lawyer and SEC4 collide --> briefcase 4 gets added + SEC4 dies
        if(lawyer.rec.intersects(SEC4.rec) && lawyerIsIntersectingSEC4==false){
            lawyerIsIntersectingSEC4=true;
            briefcase4.isAlive=true;
            briefcase4.xpos=3;
            SEC4.isAlive=false;
            SEC4.xpos=2000;
            SEC4.dx=0;
            SEC4.dy=0;
        }
        if(lawyer.rec.intersects(SEC3.rec)==false){
            lawyerIsIntersectingSEC3=false;
        }

        //lawyer and SEC5 collide --> briefcase 5 gets added (with pause time 5 sec) + SEC5 dies + end screen
        if(lawyer.rec.intersects(SEC5.rec) && lawyerIsIntersectingSEC5==false){
            lawyerIsIntersectingSEC5=true;
            briefcase5.isAlive=true;
            briefcase5.xpos=3;
            SEC5.xpos=2000;
            SEC5.dx=0;
            SEC5.dy=0;
        }
        if(lawyer.rec.intersects(SEC5.rec)==false){
            lawyerIsIntersectingSEC5=false;
        }





    }



    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

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

}

//this makes fred dissapear when they collide
//fred.isAlive=false;
//fred.dx=0;
//fred.dy=0;
//fred.xpos=2000;

//bounces objects off of each other
//fred.dx=-(fred.dx);
//fred.dy=-(fred.dx);
//SEC1.dx=-(SEC1.dx);
//SEC1.dy=-(SEC1.dy);