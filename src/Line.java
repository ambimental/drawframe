
// Declaration of class MyLine.
import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape
{
   // call default superclass constructor
   public Line()
   {
     
   } // end MyLine no-argument constructor

   // call superclass constructor passing parameters
   public Line( int x1, int y1, int x2, int y2, Color color )
   {
      
   } // end MyLine constructor

   // draw line in specified color
   public void draw( Graphics g )
   {
      g.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
   } // end method draw
} // end class MyLine


