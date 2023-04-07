import java.awt.*;

public class Button {


        public int xpos;                //the x position
        public int ypos;                //the y position
        public int width;
        public int height;
        public Rectangle rec;
        public boolean isClicked;

        public Button(int Pxpos,int Pypos,int Pwidth,int Pheight){
          xpos = Pxpos;
          ypos = Pypos;
          width = Pwidth;
          height = Pheight;
          rec = new Rectangle(xpos,ypos,width,height);
          isClicked = false;

        }

    }
