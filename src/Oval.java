
// Declaration of class MyOval.
import java.awt.Color;
import java.awt.Graphics;

public class Oval extends BoundedShape
{
   // call default superclass constructor
   public Oval()
   {
     
   } // end MyOval no-argument constructor

   // call superclass constructor passing parameters
   public Oval( int x1, int y1, int x2, int y2,
      Color color, boolean isFilled )
   {
     
   } // end MyOval constructor

   // draw oval
   public void draw( Graphics g )
   {
      g.drawOval(this.getX1(), this.getY1(), (this.getX1() - this.getX2()), (this.getY1() - this.getY2()));
	   
	   
	   
	   
   } // end method draw
} // end class MyOval


