import java.awt.*;

public class Hero {

    //variable decleration section
    public String name;
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;

    //constructor method
    public Hero(int pXpos, int pYpos, int pdx, int pdy, int pwidth, int pheight, boolean pisAlive){
        xpos = pXpos;
        ypos = pYpos;
        dx=pdx;
        dy=pdy;
        width=pwidth;
        height=pheight;
        isAlive=pisAlive;
        rec=new Rectangle(xpos, ypos, width, height);
    }

    public void move(){
        ypos = ypos-dy;
        xpos = xpos+dx;
        rec=new Rectangle(xpos, ypos, width, height);
    }

    public void wrappingMove(){
        if(xpos>1000){
            xpos=100;
        }
        if(xpos<0){
            xpos=1000;
        }
        if(ypos>700){
            ypos=200;
        }
        if(ypos<0){
            ypos=0;
        }
        //these move things come after because you need to check for position first then decide where to move to
        ypos = ypos-dy;
        xpos = xpos+dx;
        rec=new Rectangle(xpos, ypos, width, height);
    }

    public void bouncingMove(){
        if(xpos>(1000-width) || xpos<150){
            dx=-dx;
        }
        if(ypos>(700-height) || ypos<0){
            dy=-dy;
        }
        ypos = ypos-dy;
        xpos = xpos+dx;
        rec=new Rectangle(xpos, ypos, width, height);
    }

    public void printInfo(){
        System.out.println("(x,y): (" + xpos + "," + ypos + ")");
        System.out.println("x speed: " + dx);
        System.out.println("y speed: " + dy);
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("is alive: " + isAlive);
    }

}
